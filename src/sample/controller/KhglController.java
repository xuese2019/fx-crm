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
import sample.db.dao.KhglDao;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;


/**
 * @author: LD
 * @date:
 * @description:
 */
public class KhglController {

    private KhglDao dao = new KhglDao();

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
    private Label pageNow;

    @FXML
    private void add() {
        error.setText("");
        String text = name.getText();
        String text1 = dh.getText();
        String text2 = dz.getText();
        if (text.trim().isEmpty() || text1.trim().isEmpty() || text2.trim().isEmpty()) {
            error.setText("名称，电话，地址都不能为空");
        } else {
            List<Map<Integer, String>> list = dao.query("select * from kh_table where name = '" + text + "'");
            if (list != null && list.size() > 0) {
                error.setText("客户名称重复");
            } else {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format1 = format.format(System.currentTimeMillis());
                dao.update("insert into kh_table (name,dh,dz,time) values ('" + text + "','" + text1 + "','" + text2 + "','" + format1 + "')");
                error.setText("保存成功");
                name.setText("");
                dh.setText("");
                dz.setText("");
                query(0);
            }
        }
    }

    @FXML
    private void page() {
        query(0);
        pageNow.setText("1");
    }

    @FXML
    private void pageDow() {
//        String text = pageNow.getText();
//        int i = Integer.parseInt(text);
//        if (i == 1){
//            pageNow.setText(i + "");
//            query(0);
//        }else{
//
//        }
//        pageNow.setText(i + "");
//        query(i-1);
    }

    @FXML
    private void pageUp() {
//        String text = pageNow.getText();
//        int i = Integer.parseInt(text);
//        i = i - 1;
//        if (i < 0)
//            i = 0;
//        query(0);
//        pageNow.setText((i == 0 ? 1 : i) + "");
    }

    private void query(int p) {
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
        labe5.setPrefWidth(200);
        labe5.setPrefHeight(30);
        labe5.setText("时间");
        labe5.setAlignment(Pos.CENTER);
        box.getChildren().add(labe5);

        Label labe6 = new Label();
        labe6.setPrefWidth(100);
        labe6.setPrefHeight(30);
        labe6.setText("操作");
        labe6.setAlignment(Pos.CENTER);
        box.getChildren().add(labe6);

        children.add(box);

        HBox box2 = new HBox();
        box2.setPrefWidth(Region.USE_COMPUTED_SIZE);
        box2.setPrefHeight(1);
        box2.setStyle("-fx-border-color: red;");
        children.add(box2);

//        List<Map<Integer, String>> list1 = dao.query("select * from kh_table limit 20 offset " + p);
        String s = "";
        if (!name.getText().trim().isEmpty()) {
            s += " and name like '%" + name.getText() + "%' ";
        }
        if (!dh.getText().trim().isEmpty()) {
            s += " and dh like '%" + dh.getText() + "%' ";
        }
        if (!dz.getText().trim().isEmpty()) {
            s += " and dz like '%" + dz.getText() + "%' ";
        }
        List<Map<Integer, String>> list1 = dao.query("select * from kh_table where 1 = 1 " + s + " limit 30");

        data(children, list1);
    }

    private void data(ObservableList<Node> children, List<Map<Integer, String>> list1) {
        if (list1 != null && list1.size() > 0) {
            for (int i = 0; i < list1.size(); i++) {
                HBox box = new HBox();
                box.setPrefWidth(Region.USE_COMPUTED_SIZE);
                box.setPrefHeight(30);

                Label label = new Label();
                label.setPrefWidth(30);
                label.setPrefHeight(30);
                label.setText((i + 1) + "");
                label.setAlignment(Pos.CENTER);
                box.getChildren().add(label);

                Label labe2 = new Label();
                labe2.setPrefWidth(50);
                labe2.setPrefHeight(30);
                labe2.setText(list1.get(i).get(1));
                labe2.setAlignment(Pos.CENTER);
                box.getChildren().add(labe2);

                Label labe3 = new Label();
                labe3.setPrefWidth(100);
                labe3.setPrefHeight(30);
                labe3.setText(list1.get(i).get(2));
                labe3.setAlignment(Pos.CENTER);
                box.getChildren().add(labe3);

                Label labe4 = new Label();
                labe4.setPrefWidth(200);
                labe4.setPrefHeight(30);
                labe4.setText(list1.get(i).get(3));
                labe4.setAlignment(Pos.CENTER);
                box.getChildren().add(labe4);

                Label labe5 = new Label();
                labe5.setPrefWidth(200);
                labe5.setPrefHeight(30);
                labe5.setText(list1.get(i).get(4));
                labe5.setAlignment(Pos.CENTER);
                box.getChildren().add(labe5);

                Label labe6 = new Label();
                labe6.setPrefWidth(100);
                labe6.setPrefHeight(30);
                labe6.setText("删除");
                labe6.setAlignment(Pos.CENTER);
                String s = list1.get(i).get(1);
                labe6.setOnMouseClicked(event -> {
                    delete(s);
                });
                box.getChildren().add(labe6);

                children.add(box);

                HBox box2 = new HBox();
                box2.setPrefWidth(Region.USE_COMPUTED_SIZE);
                box2.setPrefHeight(1);
                box2.setStyle("-fx-border-color: red;");
                children.add(box2);
            }
        }
    }

    private void delete(String name) {
        dao.update("delete from kh_table where name = '" + name + "'");
        query(0);
    }
}
