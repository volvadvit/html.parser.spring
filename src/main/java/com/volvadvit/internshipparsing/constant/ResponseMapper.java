package com.volvadvit.internshipparsing.constant;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Vadim Volkov volvadvit@gmail.com
 * @since 10.10.2021
 */

@Data
public class ResponseMapper {
    public final Timestamp timestamp;
    public final Integer status;
    public final String message;
    public final Object body;
}