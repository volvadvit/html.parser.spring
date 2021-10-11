package com.volvadvit.internshipparsing.service.impl;

import com.volvadvit.internshipparsing.service.UrlService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Vadim Volkov
 * volvadvit@gmail.com
 */

@SpringBootTest
class UrlServiceImplTest {

    @Autowired
    private UrlService urlService;

    @Test
    public void getHtmlByUrlWithException() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            urlService.getHtmlByUrl("ottps://www.simbirsoft.com/");
        });
    }

    @Test
    public void getHtmlByUrlEquals() {
        int result = urlService.getHtmlByUrl("https://www.simbirsoft.com/").length();
        Assertions.assertEquals(95577, result);
    }
}