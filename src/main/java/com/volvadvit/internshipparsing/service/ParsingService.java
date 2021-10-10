package com.volvadvit.internshipparsing.service;

import com.volvadvit.internshipparsing.model.SourceURL;

import java.net.URL;
import java.util.Map;

/**
 * @author Vadim Volkov volvadvit@gmail.com
 * @since 10.10.2021
 */

public interface ParsingService {
    Map<String, Integer> getWordsFromHtml(SourceURL url);
}
