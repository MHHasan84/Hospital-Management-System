package com.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UsersTest {
    private UserDao2 userDao2=new UserDao2();
    private DoctorDao doctorDao=new DoctorDao();
    private PatientDao patientDao=new PatientDao();
    private TechnitianDao technitianDao=new TechnitianDao();
    private ReceiptionistDao receiptionistDao=new ReceiptionistDao();
    private AdminDao adminDao=new AdminDao();
    private TestDao testDao=new TestDao();
    @Test
    public void testAddNew(){
        com.example.Test test=new com.example.Test();
        test.setId("a");
        test.setStatus("ab");
        testDao.updateTest("a",test);
    }

    @Test
    public void testUpdate(){
        String id="D1";
        String password="d1pass";
        String usertype="1";
        userDao2.updateUser(id,password);
    }

    @Test
    public void testGetAll(){
        System.out.println(testDao.getAllTest());
    }

    @Test
    public void testGet(){
        System.out.println(testDao.getTest("1"));
    }
    @Test
    public void testDelete(){
        DoctorSchedule doctorSchedule=new DoctorSchedule();
        doctorSchedule.setDoctor_id("d1");
        doctorSchedule.setSchedule_date("date");
        doctorSchedule.setStart_time("start_time");
        doctorSchedule.setEnd_time("end_time");
        doctorSchedule.setPlace("place");

    }

}
