package com.volvadvit.internshipparsing.component;

import com.volvadvit.internshipparsing.constant.ResponseMapper;
import com.volvadvit.internshipparsing.exception.HtmlParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;

/**
 * @author Vadim Volkov volvadvit@gmail.com
 * @since 10.10.2021
 */

@ControllerAdvice
public class HtmlParseExceptionHandler {

    @ResponseBody
    @ExceptionHandler(HtmlParseException.class)
    ResponseEntity<ResponseMapper> htmlParserError(HtmlParseException e) {
        return ResponseEntity.internalServerError().body(
                new ResponseMapper(
                        new Timestamp(System.currentTimeMillis()),
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "HTML parsing failed< try later. " + e.getMessage(),
                        e.getLocalizedMessage())
        );
    }
}
