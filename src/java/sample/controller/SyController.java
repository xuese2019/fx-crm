package sample.controller;

import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    private static String p = null;

    @FXML
    private HBox datas;
    @FXML
    private Label mac;

    @FXML
    private void getZm() {
        File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
        String desktopPath = desktopDir.getAbsolutePath();
        p = desktopPath;
        mac.setText("当前地址:" + desktopPath);
        findFileByDisk(desktopPath);
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
            button.setOnMouseClicked(event -> {

            });
//            label.setText("盘符:" + fsv.getSystemDisplayName(fs[i]) + " "
//                    + "总大小:" + (fs[i].getTotalSpace() / 1024 / 1024 / 1024 ) + "G "
//                    + "剩余:" + (fs[i].getFreeSpace() / 1024 / 1024 / 1024 )+"G");
            button.setPrefWidth(50);
            button.setPrefHeight(30);
            hBox.getChildren().add(button);
        }
    }

    private void findFileByDisk(String character) {
        mac.setText("当前地址:" + character);
        datas.getChildren().clear();
        File file = new File(character);
        File[] tempList = file.listFiles();
//        滚动面板
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.getStyleClass().add("edge-to-edge");
        ObservableList<Stage> stages = FXRobotHelper.getStages();
        Stage stage = stages.get(0);
        Scene scene = stage.getScene();
        scrollPane.setPrefWidth(scene.getWidth() - 150);
        scrollPane.setMinViewportHeight(scene.getHeight() - 55);
//        隐藏底部滚动条
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        HBox hBox = new HBox();
        hBox.setStyle("-fx-background-color:#003bce;");
        hBox.setPrefWidth(scene.getWidth() - 165);
        hBox.setMinHeight(scrollPane.getMinViewportHeight());
        scrollPane.setContent(hBox);
        datas.getChildren().add(scrollPane);
        if (tempList != null && tempList.length > 0) {
            VBox vBoxa = new VBox();
            vBoxa.setPrefHeight(Region.USE_COMPUTED_SIZE);
            VBox vBoxb = new VBox();
            vBoxb.setPrefHeight(Region.USE_COMPUTED_SIZE);
            VBox vBoxc = new VBox();
            vBoxc.setPrefHeight(Region.USE_COMPUTED_SIZE);
            vBoxc.setPrefWidth(100);
            if (!p.equals(character)) {
                Button button = new Button();
                button.setText("返回");
                button.setOnMouseClicked(event -> {
                    int i = character.lastIndexOf("\\");
                    String substring = character.substring(0, i);
                    findFileByDisk(substring);
                });
                vBoxc.getChildren().add(button);
            }
            hBox.getChildren().add(vBoxc);
            hBox.getChildren().add(vBoxa);
            hBox.getChildren().add(vBoxb);
            for (int i = 0; i < tempList.length; i++) {
                HBox pane = new HBox();
                pane.setPrefHeight(30);
                pane.setPrefWidth(300);
                ImageView imageView = new ImageView();
                imageView.setFitWidth(30);
                imageView.setFitHeight(30);
                if (tempList[i].isFile()) {
//                    image = new Image("/img/wjj.jpg");
                    vBoxb.getChildren().add(pane);
                }
//                是否是文件夹
                if (tempList[i].isDirectory()) {
                    Image image = new Image(getClass().getResource("/img/wjj.jpg").toString());
                    imageView.setImage(image);
                    pane.getChildren().add(imageView);
                    String path = tempList[i].getPath();
                    pane.setOnMouseClicked(event -> {
                        findFileByDisk(path);
                    });
                    vBoxa.getChildren().add(pane);
                }
                Label label = new Label(tempList[i].getName());
                label.setPrefHeight(30);
                pane.getChildren().add(label);
            }
        }
    }
}
