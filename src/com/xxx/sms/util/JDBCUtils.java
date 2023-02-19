package com.xxx.sms.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import static java.lang.Class.forName;

/**
 * @author Adopat
 * @description 数据库连接类
 * @date 2023-02-19 15:41
 */
public class JDBCUtils {
    // 数据库账号
    private static String user;
    // 数据库密码
    private static String password;
    // 连接数据库url
    private static String url;
    // 数据库驱动
    private static String driver;

    static {
        // 静态代码块
        try {
            // 1.加载配置文件
            Properties pro = new Properties();
            pro.load(JDBCUtils.class.getResourceAsStream("/com/xxx/sms/file/jdbc.properties"));
            System.out.println(pro);
            // 2.获取配置文件中连接数据库的信息
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");
            // 3.创建数据库连接驱动
            Class.forName(driver);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 4.获取连接对象
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // 5.释放资源
    public static void close(PreparedStatement ps, Connection conn) {
        close(null, ps, conn);
    }

    //  6. 释放资源(重载)
    public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
        if (null != rs) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (null != ps) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (null != conn) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
