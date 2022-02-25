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

    public boolean addUser(String id,String password,String usertype) {
        boolean succeed=false;
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
                succeed=true;
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
        return succeed;
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

    public boolean insertPatient(Patient patient){
        boolean patient_insert_succeed=false;
        UserDao2 userDao2=new UserDao2();
        boolean user_insert_succeed=userDao2.addUser(patient.getId(), patient.getPassword(), "2");
        if(!user_insert_succeed){
            return false;
        }
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();

            String insertQuery = String.format(
                    "insert into patients(id,first_name,last_name,address,gender,phone_no,email,date_of_birth) values ('%s', '%s', '%s'," +
                            " '%s', '%s', '%s', '%s', '%s')"
                    , patient.getId(),patient.getFirst_name(),patient.getLast_name(),patient.getAddress(),
                    patient.getGender(),patient.getPhone_no(),patient.getEmail(),
                    patient.getDate_of_birth());
            oc.updateDB(insertQuery);
            patient_insert_succeed=true;
        }
        catch (Exception e) {
            System.out.println("Exception in addPatient: " + e);
        }
        finally {
            try {
                oc.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return patient_insert_succeed;
    }

}
