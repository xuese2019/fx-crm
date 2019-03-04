package sample.controller;

import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * @author: LD
 * @date:
 * @description:
 */
public class HomeController {

    /**
     * 关闭程序
     */
    @FXML
    private void closeLogin() {
//        Platform.exit();
        ObservableList<Stage> stages = FXRobotHelper.getStages();
        stages.get(0).close();
    }

    /**
     * 最小化程序
     */
    @FXML
    private void setMin() {
        ObservableList<Stage> stages = FXRobotHelper.getStages();
        Stage stage = stages.get(0);
        stage.setIconified(true);
    }
}
