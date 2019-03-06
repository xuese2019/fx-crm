package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import javax.swing.filechooser.FileSystemView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;

/**
 * @author: LD
 * @date:
 * @description:
 */
public class SyController {
    @FXML
    private HBox datas;
    @FXML
    private Label mac;

    @FXML
    private void getZm() {
        File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
        String desktopPath = desktopDir.getAbsolutePath();

        mac.setText("桌面地址:" + desktopPath);

        File file = new File(desktopPath);
        File[] tempList = file.listFiles();

        datas.getChildren().clear();
        if (tempList != null && tempList.length > 0) {
            VBox vBox = new VBox();
            for (int i = 0; i < tempList.length; i++) {
                HBox pane = new HBox();
                pane.setPrefHeight(30);
                pane.setPrefWidth(300);
                pane.setPrefHeight(Region.USE_COMPUTED_SIZE);
                ImageView imageView = new ImageView();
                Image image = null;
                if (tempList[i].isFile()) {
//                    image = new Image("/img/wjj.jpg");
                }
                if (tempList[i].isDirectory()) {
                    image = new Image(getClass().getResource("/img/wjj.jpg").toString());
                }
                imageView.setImage(image);
                imageView.setFitWidth(30);
                imageView.setFitHeight(30);
                pane.getChildren().add(imageView);
                Label label = new Label(tempList[i].getName());
                label.setPrefHeight(30);
                pane.getChildren().add(label);
                vBox.getChildren().add(pane);
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

    @FXML
    private void getMac() {
        String sn = getSerialNumber("C");
        mac.setText(sn);
    }

    private static String getSerialNumber(String drive) {
        String result = "";
        try {
            File file = File.createTempFile("damn", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);
            String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
                    + "Set colDrives = objFSO.Drives\n"
                    + "Set objDrive = colDrives.item(\""
                    + drive
                    + "\")\n"
                    + "Wscript.Echo objDrive.SerialNumber"; // see note
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec(
                    "cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;

            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.trim();
    }

    @FXML
    private void getPC() {
        datas.getChildren().clear();
        VBox vBox = new VBox();
        datas.getChildren().add(vBox);
        vBox.prefWidth(HBox.USE_COMPUTED_SIZE);
        vBox.prefHeight(VBox.USE_COMPUTED_SIZE);
        HBox hBox = new HBox();
        hBox.prefWidth(HBox.USE_COMPUTED_SIZE);
        hBox.prefHeight(30);
        vBox.getChildren().add(hBox);
        // 当前文件系统类
        FileSystemView fsv = FileSystemView.getFileSystemView();
        // 列出所有windows 磁盘
        File[] fs = File.listRoots();
        // 显示磁盘卷标
        for (int i = 0; i < fs.length; i++) {
            Button button = new Button();
            button.setStyle("-fx-background-color: #004900;");
            button.setText(fs[i].toString());
//            label.setText("盘符:" + fsv.getSystemDisplayName(fs[i]) + " "
//                    + "总大小:" + (fs[i].getTotalSpace() / 1024 / 1024 / 1024 ) + "G "
//                    + "剩余:" + (fs[i].getFreeSpace() / 1024 / 1024 / 1024 )+"G");
            button.setPrefWidth(50);
            button.setPrefHeight(30);
            hBox.getChildren().add(button);
        }
    }
}
