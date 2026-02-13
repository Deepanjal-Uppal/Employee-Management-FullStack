package com.EmployeeManagement.Exceptions;

import java.time.LocalDateTime;
import java.util.Map;

public class ErrorDetails {

    private LocalDateTime localDateTime;
    private String message;
    private Map<String, String> errors;

    public ErrorDetails() {
        super();
    }

    public ErrorDetails(LocalDateTime localDateTime, String message, Map<String, String> errors) {
        super();
        this.localDateTime = localDateTime;
        this.message = message;
        this.errors = errors;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
