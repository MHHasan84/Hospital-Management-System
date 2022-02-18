package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UsersTest {
    private UserDao2 userDao2=new UserDao2();
    private DoctorDao doctorDao=new DoctorDao();
    private PatientDao patientDao=new PatientDao();
    private ReceptionistDao receptionistDao =new ReceptionistDao();
    private AdminDao adminDao=new AdminDao();
    private TestDao testDao=new TestDao();
    private TechnicianDao technicianDao=new TechnicianDao();
    @Test
    public void testAddNew(){
        EmployeeSchedule employeeSchedule=new EmployeeSchedule();
        employeeSchedule.setDay("Saturday");
        employeeSchedule.setEmployee_id("t1");
        technicianDao.insertEmployeeSchedule(employeeSchedule);
    }

    @Test
    public void testUpdate(){
        Technician technician=new Technician();
        technician.setId("techniciantest");
        technician.setSalary(15000);
    }

    @Test
    public void testGetAll(){
        System.out.println(technicianDao.getAllSchedule("t1"));
    }

    @Test
    public void testGet(){
        System.out.println(technicianDao.getTechnician("t1"));
    }
    @Test
    public void testDelete(){
        Appointment appointment=new Appointment();
        System.out.println(appointment.getPrescription_id());

    }

}
