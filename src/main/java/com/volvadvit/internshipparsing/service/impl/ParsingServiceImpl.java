package com.volvadvit.internshipparsing.service.impl;

import com.volvadvit.internshipparsing.exception.HtmlParseException;
import com.volvadvit.internshipparsing.model.UrlDTO;
import com.volvadvit.internshipparsing.model.WordToCount;
import com.volvadvit.internshipparsing.service.ParsingService;
import com.volvadvit.internshipparsing.service.UrlService;
import com.volvadvit.internshipparsing.utils.LogUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Vadim Volkov
 * volvadvit@gmail.com
 */

@Service
@AllArgsConstructor
@Slf4j
public class ParsingServiceImpl implements ParsingService {

    private final UrlService urlService;

    private static final String PATTERN = ("[\\[\\]\"()?:$&!.,;{}<>=#_/\t'\r|\n+-]");

    @Override
    public Map<String, Integer> getWordsFromHtml(UrlDTO input) {
        String[] words = parseHtml(input.getUrl());
        Map<String, Integer> wordsCount = getWordsToCountMap(words);
        saveModelsToDB(input, wordsCount);

        return wordsCount;
    }

    @Override
    public void saveModelsToDB(UrlDTO input, Map<String, Integer> wordsCount) {
        log.info("Save entities to database");

        new Thread(() -> {
            wordsCount.forEach((key, value) -> {
                input.getWords().add(new WordToCount(key, value));
            });
            urlService.save(input);
            log.info("Source model saved with id: {}", input.getId());
        }).start();
    }

    @Override
    public Map<String, Integer> getWordsToCountMap(String[] words) {
        log.info("Counting duplicates and making map from results words");

        Map<String, Integer> wordsCount = new HashMap<>();
        for(String str : words){
            if (wordsCount.containsKey(str)) {
                wordsCount.put(str,wordsCount.get(str) + 1);
            }
            else {
                wordsCount.put(str, 1);
            }
        }

        log.info("Sorting Map<String, Integer> by Value and take last 10 elemets");
        wordsCount = wordsCount
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .skip(wordsCount.size() * 75L / 100L)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        return wordsCount;
    }

    private String[] parseHtml(String url) {
        String[] words;
        try {
            String resultHtml = urlService.getHtmlByUrl(url);
            log.info("Get html for parsing");

            String parseResult = resultHtml.replaceAll(PATTERN, " ").replaceAll("\\s+", ".");
            words = parseResult.split("\\.");
        } catch (Exception e) {
            LogUtils.info("HTML Parse failed. " + e.getMessage());
            throw new HtmlParseException(e.getMessage());
        }
        return words;
    }
}
