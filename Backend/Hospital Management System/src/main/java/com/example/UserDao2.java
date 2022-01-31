package com.example;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao2 {
    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = "select * from users";
            ResultSet rs = oc.searchDB(query);
            while (rs.next()) {
                User user=new User();
                user.setId(rs.getString("id"));
                user.setPassword(rs.getString("password"));
                user.setUsertype(rs.getString("user_type_id"));
                userList.add(user);
            }
        } catch (Exception e) {
            System.out.println("Exception in listUsers: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userList;
    }

    public void listUsers() {
        List<User> userList=getUsers();
        for(User user:userList){
            System.out.print(user.getId());
            System.out.print(user.getPassword());
            System.out.println();
        }
    }
}
