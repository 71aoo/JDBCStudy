package com.Playwi0.dao;

import com.Playwi0.exception.DaoException;
import com.Playwi0.pojo.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoJDBCImpl extends AbstractDao implements UserDao {

    public int addUser(User user) {

        String sql = "INSERT INTO User(name,gender,age)VALUES(?,?,?)";

        Object[] args = {user.getName(), user.getGender(), user.getAge()};

        return super.update(sql, args);
    }

    public int update(User user) {

        String sql = "update user set name=?,gender=?,age=? where id=?";

        Object[] args = {user.getName(),user.getGender(),user.getAge(),user.getId()};

        return super.update(sql,args);
    }

    public int delete(User user) {

        String sql = "delete from user where id=?";

        Object[] args = {user.getId()};

        return super.update(sql,args);
    }

    public User getUser(int Id) {

        String sql = "select * from user where id=?";

        Object[] args = {Id};

        Object user = super.query(sql, args);

        return (User)user;
    }

    public User findUser(String name, int age) {

        String sql = "select * from user where name=? and age=?";

        Object[] args = {name,age};

        Object user = super.query(sql, args);

        return (User)user;
    }

    protected Object rowMapper(ResultSet rs) {

        User user = null;

        try {
            user = new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("gender"),
                    rs.getInt("age"));

        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        }

        return user;
    }
}
