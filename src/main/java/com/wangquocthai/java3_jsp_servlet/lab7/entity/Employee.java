package com.wangquocthai.java3_jsp_servlet.lab7.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private String id;
    private String password;
    private String fullname;
    private String photo;
    private boolean gender;
    private Date birthday;
    private Double salary;
    private String departmentID;
}
