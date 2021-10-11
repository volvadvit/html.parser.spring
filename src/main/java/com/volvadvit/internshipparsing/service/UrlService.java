package com.volvadvit.internshipparsing.service;

import com.volvadvit.internshipparsing.model.UrlDTO;

/**
 * @author Vadim Volkov
 * volvadvit@gmail.com
 */

public interface UrlService {
    String getHtmlByUrl(String url);
    UrlDTO save(UrlDTO model);
}
