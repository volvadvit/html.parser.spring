package com.volvadvit.internshipparsing.component;

import com.volvadvit.internshipparsing.constant.ResponseMapper;
import com.volvadvit.internshipparsing.exception.HtmlParseException;
import com.volvadvit.internshipparsing.utils.LogUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.MalformedURLException;
import java.sql.Timestamp;

/**
 * @author Vadim Volkov volvadvit@gmail.com
 * @since 10.10.2021
 */

@ControllerAdvice
public class ExceptionHandlers {

    @ResponseBody
    @ExceptionHandler(HtmlParseException.class)
    ResponseEntity<ResponseMapper> htmlParserError(HtmlParseException e) {
        return ResponseEntity.internalServerError().body(
                new ResponseMapper(
                        new Timestamp(System.currentTimeMillis()),
                        HttpStatus.BAD_REQUEST.value(),
                        "Error. Try later or change url. " + e.getMessage(),
                        e.getLocalizedMessage())
        );
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
}

