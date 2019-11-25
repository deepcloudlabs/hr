package com.example.hr.exception;

public class EmployeeNotFoundException extends BaseException {
    private String identity;

    public EmployeeNotFoundException(String message, String debugId, String i18nId, String identity) {
        super(message, debugId, i18nId);
        this.identity = identity;
    }

    public String getIdentity() {
        return identity;
    }
}
