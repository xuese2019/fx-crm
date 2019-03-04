package sample.controller;

import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.db.utils.OpSqliteDB;
import sample.utils.AccountUtils;

import java.sql.ResultSet;

public class SampleController {

    private OpSqliteDB db = new OpSqliteDB();

    @FXML
    private TextField account;
    @FXML
    private TextField password;
    @FXML
    private Label errorText;
    @FXML
    private Button loginButton;

    /**
     * 关闭程序
     */
    @FXML
    private void closeLogin() {
        Platform.exit();
    }

    /**
     * 登陆
     */
    @FXML
    private void login() {
        errorText.setText("");
        loginButton.setVisible(true);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                logind2();
            }
        });
    }

    /**
     * 登陆验证
     */
    private void logind2() {
        String accountText = account.getText();
        String passwordText = password.getText();
        loginButton.setDisable(true);
        if (accountText.equals("admin") && passwordText.equals("admin")) {
            loginButton.setDisable(true);
//            跳转
            toHome();
        } else {
            ResultSet query = db.query("select * from account_table where account = '" + accountText + "' and password = '" + passwordText + "'");
            if (query != null) {
                try {
                    if (query.next()) {
                        toHome();
                    } else {
                        loginButton.setDisable(false);
                        errorText.setText("账号或密码错误");
                    }
                    query.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                loginButton.setDisable(false);
                errorText.setText("账号或密码错误");
            }
        }
    }

    /**
     * 跳转至首页
     */
    private void toHome() {
        try {
            //        获取stage
            AccountUtils.ACC = account.getText();
            ObservableList<Stage> stages = FXRobotHelper.getStages();
            Stage stage = stages.get(0);
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/home.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("../css/home.css").toExternalForm());
            stage.setScene(scene);
            //            窗口最大化
            stage.setMaximized(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
