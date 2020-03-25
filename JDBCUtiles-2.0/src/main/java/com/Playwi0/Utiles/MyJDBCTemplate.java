package com.Playwi0.Utiles;

import com.Playwi0.Mapper.RowMapper;
import com.Playwi0.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyJDBCTemplate {

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
    public List<Object> query(String sql, Object[] args, RowMapper rowMapper){

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Object> objList = new ArrayList<Object>();

        try {

            conn = JDBCUtiles.getConnection();

            ps = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {

                ps.setObject(i+1, args[i]);

            }

            rs = ps.executeQuery();

            while (rs.next()){

                Object obj = rowMapper.mapRow(rs);

                objList.add(obj);
            }

            return objList;

        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        } finally {

            JDBCUtiles.free(rs,ps,conn);
        }
    }

}
