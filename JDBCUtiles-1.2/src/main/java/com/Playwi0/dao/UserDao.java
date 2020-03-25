package com.Playwi0.dao;

import com.Playwi0.pojo.User;

import java.util.List;

public interface UserDao {

    int addUser(User user) ;
    int update(User user) ;
    int delete(User user) ;
    User getUser(int Id) ;
    User findUser(String name, int age) ;
    List<User> selectUser(int age) ;
}
