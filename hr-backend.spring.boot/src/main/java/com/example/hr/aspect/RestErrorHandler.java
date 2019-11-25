package com.example.hr.aspect;

import com.example.hr.boundary.ErrorMessage;
import com.example.hr.exception.BaseException;
import com.example.hr.exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestErrorHandler {

    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleBaseException(BaseException e) {
        return new ErrorMessage(e);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleBaseException(EmployeeNotFoundException e) {
        return new ErrorMessage(e);
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleNullPointer(RuntimeException ex) {
        String errorMessage = Arrays.stream(ex.getSuppressed()).map(Throwable::getMessage).collect(Collectors.joining(","));
        if (ex.getMessage() != null) errorMessage = ex.getMessage().concat(",").concat(errorMessage);
        return new ErrorMessage(errorMessage, errorMessage, "6f24ad96-37fe-4205-b4bf-7fa8201a22a9");
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleValidationException(ConstraintViolationException ex) {
        final Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        final String message = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("|"));
        return new ErrorMessage(message, message, "88634cf3-6295-4f43-9293-acbdb0ade3e8");
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        final String message = ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining("|"));
        return new ErrorMessage(message, message, "95b49f5c-5b2e-43de-8967-fed414a96dc0");
    }

}
