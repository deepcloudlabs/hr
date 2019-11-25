package com.example.hr.exception;

public class BaseException extends RuntimeException {
    private String debugId;
    private String i18nId;

    public BaseException(String message, String debugId, String i18nId) {
        super(message);
        this.debugId = debugId;
        this.i18nId = i18nId;
    }

    public String getDebugId() {
        return debugId;
    }

    public String getI18nId() {
        return i18nId;
    }
}
