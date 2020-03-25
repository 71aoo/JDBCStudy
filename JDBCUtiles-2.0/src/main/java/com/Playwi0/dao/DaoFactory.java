package com.Playwi0.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DaoFactory {

    private static UserDao userDaoImpl = null;

    private static DaoFactory daoFactory = new DaoFactory();

    public DaoFactory(){

        try {
            InputStream in = DaoFactory.class.getClassLoader().getResourceAsStream("DaoConfig.properties");

            Properties properties = new Properties();

            properties.load(in);

            String userDaoClass = properties.getProperty("userDao");

            Class userDao = Class.forName(userDaoClass);

            userDaoImpl = (UserDao) userDao.newInstance();

        } catch (IOException | IllegalAccessException | InstantiationException |ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DaoFactory getInstance(){
        return daoFactory;
    }

    public static UserDao getUserDaoImpl(){
        return userDaoImpl;
    }

}
