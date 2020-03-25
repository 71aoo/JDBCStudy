package com.Playwi0.dao;

import com.Playwi0.Utiles.JDBCUtiles;
import com.Playwi0.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 简化代码
public abstract class AbstractDao {

    /**
     *
     * @param sql 执行预编译语句
     * @param args sql语句参数
     * @return
     *
     * 增删改
     */
    public int update(String sql, Object...args){

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conn = JDBCUtiles.getConnection();

            ps = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {

                ps.setObject(i+1, args[i]);

            }
            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        } finally {

            JDBCUtiles.free(rs,ps,conn);
        }
    }

    /**
     *
     * @param sql 执行预编译语句
     * @param args sql语句参数
     * @return
     *
     * 查询
     */
    public Object query(String sql, Object...args){

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conn = JDBCUtiles.getConnection();

            ps = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {

                ps.setObject(i+1, args[i]);

            }

            rs = ps.executeQuery();

            Object obj = null;

            while (rs.next()){

                obj = rowMapper(rs);
            }

            return obj;

        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        } finally {

            JDBCUtiles.free(rs,ps,conn);
        }
    }

    // 结果集映射
    abstract protected Object rowMapper(ResultSet rs);
}
