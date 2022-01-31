package com.example;

import java.util.List;

public class UserDao {
    private MyJdbcTemplate myJdbcTemplate=new MyJdbcTemplate();
    public int createUser(User user){
        String sql="insert into users(id,password,user_type_id) values(?,?,?)";
        return myJdbcTemplate.getJdbcTemplate().update(sql,user.getId(),user.getPassword(),user.getUsertype());
    }

    public User getUserById(String userId) {
        String SQL = "SELECT * FROM users WHERE id = ?";
        return myJdbcTemplate.getJdbcTemplate().queryForObject(SQL, new UserRowMapper(), userId);
    }

    public List<User> fetchAllEmployees() {
        String SQL = "SELECT * FROM users";
        return myJdbcTemplate.getJdbcTemplate().query(SQL, new UserRowMapper());
    }

    public int updateEmployeeEmailById(String password, String userId) {
        String SQL = "UPDATE users set email = ? WHERE id = ?";
        int update = myJdbcTemplate.getJdbcTemplate().update(SQL, password,userId);
        if(update == 1) {
            System.out.println("User email is updated for ID = "+userId);
        }
        return update;
    }

    public int updateUser(User user){
        String SQL = "UPDATE users set password=? WHERE id = ?";
        int update = myJdbcTemplate.getJdbcTemplate().update(SQL,user.getPassword(),user.getId());
        if(update == 1) {
            System.out.println("User email is updated for ID = "+user.getId());
        }
        return update;
    }
    public int deleteEmployeeById(String userId) {
        String SQL = "DELETE FROM users WHERE id = ?";
        int update = myJdbcTemplate.getJdbcTemplate().update(SQL,userId);
        if(update == 1) {
            System.out.println("User is deleted with ID = "+userId);
        }
        return update;
    }
}
