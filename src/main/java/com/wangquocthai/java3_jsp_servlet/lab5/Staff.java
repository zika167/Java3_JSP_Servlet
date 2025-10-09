package com.wangquocthai.java3_jsp_servlet.lab5;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Staff {
    private String fullname;
    private Date birthday;
    private boolean gender;
    private String[] hobbies;
    private String country;
    private Double salary;


}
