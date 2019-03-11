package sample.controller;

import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * @author: LD
 * @date:
 * @description:
 */
public class LlqController {

    @FXML
    private WebView webs;
    @FXML
    private AnchorPane anch;
    @FXML
    private TextField paths;

    public void init() {
//        地址栏添加回车事件
        paths.setOnKeyReleased(event -> {
            if (KeyCode.ENTER.equals(event.getCode())) {
                tz();
            }
        });
        paths.setText("http://www.baidu.com");
        ObservableList<Stage> stages = FXRobotHelper.getStages();
        Stage stage = stages.get(0);
        Scene scene = stage.getScene();
        anch.setMinHeight(scene.getHeight() - 30);
        WebEngine engine = webs.getEngine();
        engine.load(paths.getText());
//        监听地址改变
        engine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<Worker.State>() {
                    public void changed(ObservableValue ov, Worker.State oldState, Worker.State newState) {
                        if (newState == Worker.State.SUCCEEDED) {
                            paths.setText(engine.getLocation());
                        }
                    }
                });
    }

    @FXML
    private void tz() {
        String text = paths.getText();
        if (text != null && !text.trim().isEmpty()) {
            webs.getEngine().load(text.trim());
        }
    }

    @FXML
    private void resf() {
        webs.getEngine().reload();
    }

    @FXML
    private void fh(){
        WebHistory history = webs.getEngine().getHistory();
        int currentIndex = history.getCurrentIndex();
        if (currentIndex > 0)
            history.go(-1);
    }
}
