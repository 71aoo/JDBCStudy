package com.Playwi0.Mapper;

import com.Playwi0.exception.DaoException;
import com.Playwi0.pojo.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper {

    public Object mapRow(ResultSet rs) {

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
