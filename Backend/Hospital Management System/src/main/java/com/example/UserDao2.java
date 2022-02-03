package com.example;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao2 {
    public User getUser(String id){
        User user=new User();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from users where id='%s'",id);
            ResultSet rs = oc.searchDB(query);
            if (rs.next()) {
                user.setId(rs.getString("id"));
                user.setPassword(rs.getString("password"));
                user.setUsertype(rs.getString("user_type_id"));
            }
            else {
                System.out.println("No user in this id");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Exception in getUser: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }
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

    public void addUser(String id,String password,String usertype) {
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from users where id = '%s'", id);
            ResultSet rs = oc.searchDB(query);
            if (rs.next()) {
                System.out.println("User with this Id already exists");
            }
            else {
                String insertQuery = String.format(
                        "insert into users(id, password, user_type_id) values ('%s', '%s', '%s')", id, password, usertype);
                oc.updateDB(insertQuery);
            }
        }
        catch (Exception e) {
            System.out.println("Exception in addUser: " + e);
        }
        finally {
            try {
                oc.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void updateUser(String id,String password) {
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from users where id = '%s'", id);
            ResultSet rs = oc.searchDB(query);
            if (rs.next()) {
                String updateQuery=String.format("update users set password='%s' where id='%s'",password,id);
                oc.updateDB(updateQuery);
            }
            else {
                System.out.println("there is no users in this id");
            }
        }
        catch (Exception e) {
            System.out.println("Exception in updateUser: " + e);
        }
        finally {
            try {
                oc.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteUser(String id) {
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from users where id = '%s'", id);
            ResultSet rs = oc.searchDB(query);
            if (rs.next()) {
                String deleteQuery=String.format("delete from users where id='%s'",id);
                oc.updateDB(deleteQuery);
            }
            else {
                System.out.println("there is no users in this id");
            }
        }
        catch (Exception e) {
            System.out.println("Exception in deleteUser: " + e);
        }
        finally {
            try {
                oc.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
