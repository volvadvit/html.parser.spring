package com.volvadvit.internshipparsing.service;

import com.volvadvit.internshipparsing.model.SourceURL;

import java.net.URL;

/**
 * @author Vadim Volkov volvadvit@gmail.com
 * @since 10.10.2021
 */

public interface UrlService {
    String getHtmlByUrl(String url);
    SourceURL save(SourceURL model);
}
