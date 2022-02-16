package com.example;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OperationDao {
    public List<Operation> getAllOperation(){
        List<Operation> operationList=new ArrayList<>();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = "select * from operation";
            ResultSet rs = oc.searchDB(query);
            while (rs.next()){
                Operation operation=new Operation();
                operation.setId(rs.getString("id"));
                operation.setOperation_name(rs.getString("operation_name"));
                operation.setRoom_no(rs.getString("room_no"));
                operation.setCost(rs.getInt("cost"));
                operation.setStatus(rs.getString("status"));
                operationList.add(operation);
            }
        } catch (Exception e) {
            System.out.println("Exception in getAllOperation: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return operationList;
    }

    public Operation getOperation(String id){
        Operation operation=new Operation();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from operation where id='%s'",id);
            ResultSet rs = oc.searchDB(query);
            if (rs.next()) {
                operation.setId(rs.getString("id"));
                operation.setOperation_name(rs.getString("operation_name"));
                operation.setRoom_no(rs.getString("room_no"));
                operation.setCost(rs.getInt("cost"));
                operation.setStatus(rs.getString("status"));
            }
            else {
                System.out.println("No operation in this id");
            }
        } catch (Exception e) {
            System.out.println("Exception in getOperation: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return operation;
    }

    public void insertOperation(Operation operation){
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String insertQuery=String.format(
                    "insert into operation(id,operation_name,room_no,cost,status)" +
                            " values('%s','%s','%s',%d,'%s')",operation.getId(),operation.getOperation_name(),
                    operation.getRoom_no(),operation.getCost(),operation.getStatus()
            );
            oc.updateDB(insertQuery);
        }
        catch (Exception e) {
            System.out.println("Exception in addOperation: " + e);
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

    public void updateOperation(String id,Operation operation){
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String updateQuery=String.format(
                    "update operation set operation_name='%s',room_no='%s'," +
                            "cost=%d,status='%s' where id='%s'",operation.getOperation_name(),
                    operation.getRoom_no(),operation.getCost(),operation.getStatus(),
                    operation.getId()
            );
            oc.updateDB(updateQuery);
        }
        catch (Exception e) {
            System.out.println("Exception in updateOperation: " + e);
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
