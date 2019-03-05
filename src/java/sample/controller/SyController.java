package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

/**
 * @author: LD
 * @date:
 * @description:
 */
public class SyController {
    @FXML
    private HBox datas;

    @FXML
    private void getZm() {
        File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
        String desktopPath = desktopDir.getAbsolutePath();

        File file = new File(desktopPath);
        File[] tempList = file.listFiles();

        datas.getChildren().clear();
        if (tempList != null && tempList.length > 0) {
            VBox vBox = new VBox();
            for (int i = 0; i < tempList.length; i++) {
                //                是否是文件
                if (tempList[i].isFile()) {
                    Label label = new Label(tempList[i].getName());
                    vBox.getChildren().add(label);
                }
//                是否是文件夹
                if (tempList[i].isDirectory()) {
                    Label label = new Label(tempList[i].getName());
                    vBox.getChildren().add(label);
                }
                if (i % 30 == 0 && i != 0) {
                    datas.getChildren().add(vBox);
                    vBox = new VBox();
                }
                if (i == (tempList.length - 1) && i % 30 != 0) {
                    datas.getChildren().add(vBox);
                    vBox = new VBox();
                }
            }
        }
    }
}
