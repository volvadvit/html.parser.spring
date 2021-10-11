package com.volvadvit.internshipparsing.service;

import com.volvadvit.internshipparsing.model.UrlDTO;

import java.util.Map;

/**
 * @author Vadim Volkov
 * volvadvit@gmail.com
 */

public interface ParsingService {
    Map<String, Integer> getWordsFromHtml(UrlDTO url);
    void saveModelsToDB(UrlDTO input, Map<String, Integer> wordsCount);
    Map<String, Integer> getWordsToCountMap(String[] words);
}
