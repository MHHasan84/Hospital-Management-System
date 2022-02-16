package com.example;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TestDao {
    public List<Test> getAllTest(){
        List<Test> testList=new ArrayList<>();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = "select * from test";
            ResultSet rs = oc.searchDB(query);
            while (rs.next()){
                Test test=new Test();
                test.setId(rs.getString("id"));
                test.setTest_name(rs.getString("test_name"));
                test.setRoom_no(rs.getString("room_no"));
                test.setTechnitian_id(rs.getString("technitian_id"));
                test.setCost(rs.getInt("cost"));
                test.setStatus(rs.getString("status"));
                testList.add(test);
            }
        } catch (Exception e) {
            System.out.println("Exception in getAllTest: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return testList;
    }

    public Test getTest(String id){
        Test test=new Test();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from test where id='%s'",id);
            ResultSet rs = oc.searchDB(query);
            if (rs.next()) {
                test.setId(rs.getString("id"));
                test.setTest_name(rs.getString("test_name"));
                test.setRoom_no(rs.getString("room_no"));
                test.setTechnitian_id(rs.getString("technitian_id"));
                test.setCost(rs.getInt("cost"));
                test.setStatus(rs.getString("status"));
            }
            else {
                System.out.println("No test in this id");
            }
        } catch (Exception e) {
            System.out.println("Exception in getTest: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return test;
    }

    public void insertTest(Test test){
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String insertQuery=String.format(
                    "insert into test(id,test_name,room_no,technitian_id,cost,status)" +
                            " values('%s','%s','%s','%s',%d,'%s')",test.getId(),test.getTest_name(),
                    test.getRoom_no(),test.getTechnitian_id(),test.getCost(),test.getStatus()
            );
            oc.updateDB(insertQuery);
        }
        catch (Exception e) {
            System.out.println("Exception in addTest: " + e);
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

    public void updateTest(String id,Test test){
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String updateQuery=String.format(
                    "update test set test_name='%s',room_no='%s',technitian_id='%s'," +
                            "cost=%d,status='%s' where id='%s'",test.getTest_name(),
                    test.getRoom_no(),test.getTechnitian_id(),test.getCost(),test.getStatus(),
                    test.getId()
            );
            oc.updateDB(updateQuery);
        }
        catch (Exception e) {
            System.out.println("Exception in updateTest: " + e);
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
