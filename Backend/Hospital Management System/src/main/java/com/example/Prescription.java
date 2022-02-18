package com.example;

import java.util.List;

public class Prescription {
    private int prescription_id;
    private String problem;
    private String medicine;
    private String operation;
    private String others;
    private List<String> test;

    public int getPrescription_id() {
        return prescription_id;
    }

    public void setPrescription_id(int prescription_id) {
        this.prescription_id = prescription_id;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public List<String> getTest() {
        return test;
    }

    public void setTest(List<String> test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "prescription_id=" + prescription_id +
                ", problem='" + problem + '\'' +
                ", medicine='" + medicine + '\'' +
                ", operation='" + operation + '\'' +
                ", others='" + others + '\'' +
                ", test=" + test +
                '}';
    }
}
