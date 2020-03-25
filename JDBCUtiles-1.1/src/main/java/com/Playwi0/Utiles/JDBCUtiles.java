package com.Playwi0.Utiles;


import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtiles {

    private static Properties JDBCproperties  = null;

    static {

        try {

            InputStream JDBCIn = JDBCUtiles.class.getClassLoader().getResourceAsStream("JDBC.properties");

            JDBCproperties = new Properties();

            JDBCproperties.load(JDBCIn);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            // 加载驱动
            Class.forName(JDBCproperties.getProperty("driver"));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    // 返回connection
    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
                JDBCproperties.getProperty("url"),
                JDBCproperties.getProperty("user"),
                JDBCproperties.getProperty("password"));
    }

    // 释放连接
    public static void free(ResultSet rs, Statement sm, Connection conn) {

            try {

                if (rs != null) rs.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }finally {
                try {

                    if (sm != null) sm.close();

                } catch (SQLException e) {

                    e.printStackTrace();

                }finally {
                    try {

                        if (conn != null) conn.close();

                    } catch (SQLException e) {

                        e.printStackTrace();

                    }
                }
            }
    }
}
