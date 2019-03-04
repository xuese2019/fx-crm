package sample.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import sample.db.utils.OpSqliteDB;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * @author: LD
 * @date:
 * @description:
 */
public class KhglController {

    OpSqliteDB db = new OpSqliteDB();

    @FXML
    private TextField name;
    @FXML
    private TextField dh;
    @FXML
    private TextField dz;
    @FXML
    private Label error;
    @FXML
    private VBox tables;

    @FXML
    private void add() {
        error.setText("");
        String text = name.getText();
        String text1 = dh.getText();
        String text2 = dz.getText();
        if (text.trim().isEmpty() || text1.trim().isEmpty() || text2.trim().isEmpty()) {
            error.setText("名称，电话，地址都不能为空");
        } else {
            ResultSet query = db.query("select * from kh_table where name = '" + text + "'");
            try {
                if (query != null && query.next()) {
                    error.setText("客户名称重复");
                } else {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String format1 = format.format(System.currentTimeMillis());
                    db.update("insert into kh_table (name,dh,dz,time) values ('" + text + "','" + text1 + "','" + text2 + "','" + format1 + "')");
                    error.setText("保存成功");
                }
            } catch (Exception e) {
                error.setText("失败");
            }
        }
    }

    @FXML
    private void query() {
        tables.getChildren().clear();
        ObservableList<Node> children = tables.getChildren();

        HBox box = new HBox();
        box.setPrefWidth(Region.USE_COMPUTED_SIZE);
        box.setPrefHeight(30);

        Label label = new Label();
        label.setPrefWidth(30);
        label.setPrefHeight(30);
        label.setText("序号");
        label.setAlignment(Pos.CENTER);
        box.getChildren().add(label);

        Label labe2 = new Label();
        labe2.setPrefWidth(50);
        labe2.setPrefHeight(30);
        labe2.setText("名称");
        labe2.setAlignment(Pos.CENTER);
        box.getChildren().add(labe2);

        Label labe3 = new Label();
        labe3.setPrefWidth(100);
        labe3.setPrefHeight(30);
        labe3.setText("电话");
        labe3.setAlignment(Pos.CENTER);
        box.getChildren().add(labe3);

        Label labe4 = new Label();
        labe4.setPrefWidth(200);
        labe4.setPrefHeight(30);
        labe4.setText("地址");
        labe4.setAlignment(Pos.CENTER);
        box.getChildren().add(labe4);

        Label labe5 = new Label();
        labe5.setPrefWidth(100);
        labe5.setPrefHeight(30);
        labe5.setText("时间");
        labe5.setAlignment(Pos.CENTER);
        box.getChildren().add(labe5);

        children.add(box);

        HBox box2 = new HBox();
        box2.setPrefWidth(Region.USE_COMPUTED_SIZE);
        box2.setPrefHeight(1);
        box2.setStyle("-fx-border-color: red;");
        children.add(box2);

        ResultSet query = db.query("select * from kh_table");
        List<List<String>> list = new ArrayList<>(0);
        try {
            if (query != null) {
                while (query.next()) {
                    System.out.println(query.getString(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        data(children, null);
    }

    private void data(ObservableList<Node> children, List<List<String>> list) {
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                HBox box = new HBox();
                box.setPrefWidth(Region.USE_COMPUTED_SIZE);
                box.setPrefHeight(30);

                Label label = new Label();
                label.setPrefWidth(30);
                label.setPrefHeight(30);
                label.setText(list.get(i).get(0));
                label.setAlignment(Pos.CENTER);
                box.getChildren().add(label);

                Label labe2 = new Label();
                labe2.setPrefWidth(50);
                labe2.setPrefHeight(30);
                labe2.setText(list.get(i).get(1));
                labe2.setAlignment(Pos.CENTER);
                box.getChildren().add(labe2);

                Label labe3 = new Label();
                labe3.setPrefWidth(100);
                labe3.setPrefHeight(30);
                labe3.setText(list.get(i).get(2));
                labe3.setAlignment(Pos.CENTER);
                box.getChildren().add(labe3);

                Label labe4 = new Label();
                labe4.setPrefWidth(200);
                labe4.setPrefHeight(30);
                labe4.setText(list.get(i).get(3));
                labe4.setAlignment(Pos.CENTER);
                box.getChildren().add(labe4);

                Label labe5 = new Label();
                labe5.setPrefWidth(100);
                labe5.setPrefHeight(30);
                labe5.setText(list.get(i).get(4));
                labe5.setAlignment(Pos.CENTER);
                box.getChildren().add(labe5);

                children.add(box);

                HBox box2 = new HBox();
                box2.setPrefWidth(Region.USE_COMPUTED_SIZE);
                box2.setPrefHeight(1);
                box2.setStyle("-fx-border-color: red;");
                children.add(box2);
            }
        }
    }
}
