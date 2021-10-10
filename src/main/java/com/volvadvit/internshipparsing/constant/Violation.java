package com.volvadvit.internshipparsing.constant;

import lombok.Data;

/**
 * @author Vadim Volkov volvadvit@gmail.com
 * @since 10.10.2021
 */

@Data
public class Violation {

    private final String fieldName;
    private final String message;
}
