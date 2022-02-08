package com.example.model;

import java.util.Date;
import java.util.List;

public class Appointment {
    private String id;
    private String doctor_id;
    private String patient_id;
    private String appointment_date;
    private String staus;
    private Prescription prescription;
    private List<Test> suggested_test;
    private List<Operation> suggested_operations;
    private List<TestResult> testResultList;
    private List<OperationResult> operationResultList;

}
