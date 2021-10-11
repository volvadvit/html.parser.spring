package com.volvadvit.internshipparsing.constant;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Vadim Volkov
 * volvadvit@gmail.com
 */

@Data
public class ResponseMapper {
    public final Timestamp timestamp;
    public final Integer status;
    public final String message;
    public final Object body;
}