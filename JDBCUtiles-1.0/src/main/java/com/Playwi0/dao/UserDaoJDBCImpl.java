package com.Playwi0.dao;

import com.Playwi0.Utils.JDBCUtiles;
import com.Playwi0.exception.DaoException;
import com.Playwi0.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoJDBCImpl implements UserDao {

    // 添加User
    public int addUser(User user) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conn = JDBCUtiles.getConnection();

            String sql = "INSERT INTO User(name,gender,age)VALUES(?,?,?)";

            ps = conn.prepareStatement(sql);

            ps.setString(1,user.getName());
            ps.setString(2,user.getGender());
            ps.setInt(3,user.getAge());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        } finally {

            JDBCUtiles.free(rs,ps,conn);
        }


    }

    public int update(User user) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conn = JDBCUtiles.getConnection();

            String sql = "update user set name=?,gender=?,age=? where id=?";

            ps = conn.prepareStatement(sql);

            ps.setString(1,user.getName());
            ps.setString(2,user.getGender());
            ps.setInt(3,user.getAge());
            ps.setInt(4,user.getId());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        } finally {

            JDBCUtiles.free(rs,ps,conn);
        }

    }

    public int delete(User user) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conn = JDBCUtiles.getConnection();

            String sql = "delete from user where id=?";

            ps = conn.prepareStatement(sql);

            ps.setInt(1,user.getId());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        } finally {

            JDBCUtiles.free(rs,ps,conn);
        }
    }

    public User getUser(int Id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;

        try {

            conn = JDBCUtiles.getConnection();

            String sql = "select * from user where id=?";

            ps = conn.prepareStatement(sql);

            ps.setInt(1,Id);

            rs = ps.executeQuery();

            while (rs.next()){

                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setGender(rs.getString("gender"));
                user.setAge(rs.getInt("age"));
            }

            return user;

        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        } finally {

            JDBCUtiles.free(rs,ps,conn);
        }

    }

    public User findUser(String name, int age) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;

        try {

            conn = JDBCUtiles.getConnection();

            String sql = "select * from user where name=? and age=?";

            ps = conn.prepareStatement(sql);

            ps.setString(1,name);
            ps.setInt(2,age);

            rs = ps.executeQuery();

            while (rs.next()){

                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setGender(rs.getString("gender"));
                user.setAge(rs.getInt("age"));
            }

            return user;

        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        } finally {

            JDBCUtiles.free(rs,ps,conn);
        }

    }
}
