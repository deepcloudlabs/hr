package com.example.hr.boundary;

import com.example.hr.exception.BaseException;

public class ErrorMessage {
    private final String message;
    private final String debug;
    private final String i18n;

    public ErrorMessage(BaseException e) {
        this.message = e.getMessage();
        this.debug = e.getDebugId();
        this.i18n = e.getI18nId();
    }

    public ErrorMessage(String message, String i18n, String debug) {
        this.message = message;
        this.i18n = i18n;
        this.debug = debug;
    }

    public String getMessage() {
        return message;
    }

    public String getDebug() {
        return debug;
    }

    public String getI18n() {
        return i18n;
    }
}
