package com.Playwi0.dao;

import com.Playwi0.pojo.User;

import java.sql.SQLException;

public interface UserDao {

    /**
     *
     * @param user
     * @return
     * @throws SQLException
     *
     * 问题：
     *      SQLException 直接在接口处填加容易暴露该接口是JDBC的实现
     *      同时 SQLException 是编译时异常，并没有对业务运行时异常捕获
     */
//    int addUser(User user) throws SQLException;
//    int update(User user) throws SQLException;
//    int delete(User user) throws SQLException;
//    User getUser(int Id) throws SQLException;
//    User findUser(String name, int age) throws SQLException;

    int addUser(User user) ;
    int update(User user) ;
    int delete(User user) ;
    User getUser(int Id) ;
    User findUser(String name, int age) ;

}
