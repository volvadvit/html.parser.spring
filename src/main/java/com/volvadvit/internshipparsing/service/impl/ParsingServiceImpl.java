package com.volvadvit.internshipparsing.service.impl;

import com.volvadvit.internshipparsing.exception.HtmlParseException;
import com.volvadvit.internshipparsing.model.SourceURL;
import com.volvadvit.internshipparsing.model.WordToCount;
import com.volvadvit.internshipparsing.service.ParsingService;
import com.volvadvit.internshipparsing.service.UrlService;
import com.volvadvit.internshipparsing.utils.LogUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vadim Volkov volvadvit@gmail.com
 * @since 10.10.2021
 */

@Service
@AllArgsConstructor
@Slf4j
public class ParsingServiceImpl implements ParsingService {

    private final UrlService urlService;

    private static final String PATTERN = ("[\\[\\]\"()?:$&!.,;{}<>=#_/\t'\r|\n+-]");

    @Override
    public Map<String, Integer> getWordsFromHtml(SourceURL input) {
        String[] words = parseHtml(input.getUrl());

        Map<String, Integer> wordsCount = countDuplicates(words);

        saveWordsToDB(input, wordsCount);

        return wordsCount;
    }

    private void saveWordsToDB(SourceURL input, Map<String, Integer> wordsCount) {
        log.info("Save entities to database");

        new Thread(() -> {
            wordsCount.forEach((key, value) -> {
                input.getWords().add(new WordToCount(key, value));
            });
            urlService.save(input);
            log.info("Source model saved with id: {}", input.getId());
        }).start();
    }

    private Map<String, Integer> countDuplicates(String[] words) {
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
