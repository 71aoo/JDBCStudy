package com.Playwi0.Utiles;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtiles {

    private static MyDatasource datasource = new MyDatasource();

    // 返回连接
    public static Connection getConnection() throws SQLException {

        return datasource.getConnection();
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

    // 返回连接池
    public static MyDatasource getDatasource() {
        return datasource;
    }
}
