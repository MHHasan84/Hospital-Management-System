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
    @Test
    public void testAddNew(){
        String id="d2";
        String password="d2pass";
        String usertype="1";
        userDao2.addUser(id,password,usertype);
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
        userDao2.listUsers();
    }

    @Test
    public void testGet(){
        String id="gg";
        System.out.println(doctorDao.getDoctor(id).getDate_of_birth());
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
