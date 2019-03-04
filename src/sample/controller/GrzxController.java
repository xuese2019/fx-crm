package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.db.utils.OpSqliteDB;


/**
 * @author: LD
 * @date:
 * @description:
 */
public class GrzxController {

    private OpSqliteDB db = new OpSqliteDB();

    @FXML
    private TextField password;
    @FXML
    private TextField userName;
    @FXML
    private Label pwderror;
    @FXML
    private Label nameerror;

    @FXML
    private void updatePWD() {
        db.update("update account_table set password = " + password.getText());
        pwderror.setText("修改成功");
    }

    @FXML
    private void updateName() {
        db.update("update account_table set name = " + userName.getText());
        nameerror.setText("修改成功");
    }
}
