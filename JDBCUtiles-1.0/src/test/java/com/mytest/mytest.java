package com.mytest;

import com.Playwi0.Utils.JDBCUtiles;
import com.Playwi0.dao.UserDaoJDBCImpl;
import com.Playwi0.pojo.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class mytest {


    public void one() {

        try {
            Connection connection = JDBCUtiles.getConnection();

            String sql = "select * from User";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                System.out.println(resultSet.getString("name"));

                System.out.println(resultSet.getString("gender"));

                System.out.println(resultSet.getInt("age"));
            }

            JDBCUtiles.free(resultSet,preparedStatement,connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void two() {

        try {
            Connection connection = JDBCUtiles.getConnection();

            String sql = "select name from user where id = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1,4);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){

                System.out.println(resultSet.getString("name"));

//                System.out.println(resultSet.getString("gender"));
//
//                System.out.println(resultSet.getInt("age"));
            }

            JDBCUtiles.free(resultSet,ps,connection);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void three(){

        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        User user = userDaoJDBC.getUser(2);
        System.out.println(user);
    }
}

