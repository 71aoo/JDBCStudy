package com.Playwi0.dao;

import com.Playwi0.Mapper.UserMapper;
import com.Playwi0.Utiles.MyJDBCTemplate;
import com.Playwi0.pojo.User;

import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private MyJDBCTemplate myJDBCTemplate = new MyJDBCTemplate();

    public int addUser(User user) {

        String sql = "INSERT INTO User(name,gender,age)VALUES(?,?,?)";

        Object[] args = {user.getName(), user.getGender(), user.getAge()};

        return myJDBCTemplate.update(sql, args);
    }

    public int update(User user) {

        String sql = "update user set name=?,gender=?,age=? where id=?";

        Object[] args = {user.getName(),user.getGender(),user.getAge(),user.getId()};

        return myJDBCTemplate.update(sql,args);
    }

    public int delete(User user) {

        String sql = "delete from user where id=?";

        Object[] args = {user.getId()};

        return myJDBCTemplate.update(sql,args);
    }

    public User getUser(int Id) {

        String sql = "select * from user where id=?";

        Object[] args = {Id};

        UserMapper userMapper = new UserMapper();

        List<Object> objectList = myJDBCTemplate.query(sql, args, userMapper);

        return (User)objectList.get(0);
    }

    public User findUser(String name, int age) {

        String sql = "select * from user where name=? and age=?";

        Object[] args = {name,age};

        UserMapper userMapper = new UserMapper();

        List<Object> objectList = myJDBCTemplate.query(sql, args, userMapper);

        return (User) objectList.get(0);
    }

    public List selectUser(int age) {

        String sql = "select * from user where age=?";

        Object[] args = {age};

        UserMapper userMapper = new UserMapper();

        List<Object> objectList = myJDBCTemplate.query(sql, args, userMapper);

        return objectList;
    }

}
