package sample.db.utils;

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
        Path db = new Path();
        return DriverManager.getConnection("jdbc:sqlite:"+db.path()+"\\crm.db");
    }

}
