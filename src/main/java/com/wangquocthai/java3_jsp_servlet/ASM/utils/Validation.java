package com.wangquocthai.java3_jsp_servlet.ASM.utils;

import java.util.HashMap;
import java.util.Map;

public class Validation {
    private Map<String, String> errors = new HashMap<>();

    public void required(String field, String value, String message) {
        if (value == null || value.trim().isEmpty()) {
            errors.put(field, message);
        }
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
