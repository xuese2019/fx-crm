package sample.db.utils;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author: LD
 * @date:
 * @description:
 */
public class OpSqliteDB {

    // 创建Sqlite数据库连接
    public static Connection createConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        URL url = OpSqliteDB.class.getResource("/");
        return DriverManager.getConnection("jdbc:sqlite:" + path());
    }

    public static String path() {
        File directory = new File("crm.db");//设定为当前文件夹
        try {
            System.out.println(directory.getCanonicalPath());//获取标准的路径
            System.out.println(directory.getAbsolutePath());//获取绝对路径
            return directory.getCanonicalPath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
//        System.out.println(System.getProperty("user.dir"));
    }

}
