package com.volvadvit.internshipparsing.service.impl;

import com.volvadvit.internshipparsing.service.ParsingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

/**
 * @author Vadim Volkov
 * volvadvit@gmail.com
 */

@SpringBootTest
class ParsingServiceImplTest {

    @Autowired
    ParsingService parsingService;

    @Test
    void getWordsToCountMap() {
        String[] unsortedWords = {"three", "one", "three", "two", "three", "two"};
        Map<String, Integer> sortedTwentyFivePercentMap = Map.of("three", 3);
        Map<String, Integer> result = parsingService.getWordsToCountMap(unsortedWords);
        Assertions.assertEquals(sortedTwentyFivePercentMap, result);
    }
}