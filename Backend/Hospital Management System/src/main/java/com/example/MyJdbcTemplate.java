package com.example;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class MyJdbcTemplate {
    private JdbcTemplate jdbcTemplate;
    public MyJdbcTemplate(){
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:ORCL");
        dataSource.setUsername("c##hospital_management_db");
        dataSource.setPassword("hasandb");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        jdbcTemplate=new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
