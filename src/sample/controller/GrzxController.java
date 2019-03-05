package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.db.dao.AccountDao;


/**
 * @author: LD
 * @date:
 * @description:
 */
public class GrzxController {

    private AccountDao dao = new AccountDao();

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
        dao.update("update account_table set password = " + password.getText());
        pwderror.setText("修改成功");
    }

    @FXML
    private void updateName() {
        dao.update("update account_table set name = " + userName.getText());
        nameerror.setText("修改成功");
    }
}
