package com.wangquocthai.java3_jsp_servlet.lab7.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    private String id;
    private String name;
    private String description;
}
