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
    private static final String Class_Name = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:" + path();

    // 创建Sqlite数据库连接
    public static Connection createConnection() throws SQLException, ClassNotFoundException {
        Class.forName(Class_Name);
        return DriverManager.getConnection(DB_URL);
    }

    private static String path() {
        URL url = OpSqliteDB.class.getResource("/");
        String s = url.toString().substring(6) + "sample/db/sqlite/crm.db";
        System.out.println(s);
        return s;
    }

}
