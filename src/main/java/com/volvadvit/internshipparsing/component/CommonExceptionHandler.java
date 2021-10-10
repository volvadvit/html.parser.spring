package com.volvadvit.internshipparsing.component;

import com.volvadvit.internshipparsing.constant.ResponseMapper;
import com.volvadvit.internshipparsing.constant.Violation;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vadim Volkov volvadvit@gmail.com
 * @since 10.10.2021
 */

@ControllerAdvice
public class CommonExceptionHandler {

    @ResponseBody
    @ExceptionHandler(PropertyValueException.class)
    ResponseEntity<ResponseMapper> propertyValueInvalid(PropertyValueException e) {
        return ResponseEntity.badRequest().body(
                new ResponseMapper(
                        new Timestamp(System.currentTimeMillis()),
                        HttpStatus.BAD_REQUEST.value(),
                        "property: " + e.getPropertyName() + ". " + e.getMessage(), e.getCause()));
    }

    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<ResponseMapper> illegalArgumentError(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(
                new ResponseMapper(
                        new Timestamp(System.currentTimeMillis()),
                        HttpStatus.BAD_REQUEST.value(),
                        e.getMessage(), e.getCause()));
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<ResponseMapper> onMethodArgumentNotValidException(
            ConstraintViolationException e) {
        List<Violation> errorList = new ArrayList<>();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            errorList.add(
                    new Violation(violation.getPropertyPath().toString(), violation.getMessage())
            );
        }
        return ResponseEntity.badRequest().body(
                new ResponseMapper(
                        new Timestamp(System.currentTimeMillis()),
                        HttpStatus.BAD_REQUEST.value(),
                        "Invalid input parameters. Details in body", errorList));
    }
}

