package com.example;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user=new User();
        user.setId(rs.getString("id"));
        user.setPassword(rs.getString("password"));
        user.setUsertype(rs.getString("user_type_id"));
        return user;
    }
}

