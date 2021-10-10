package com.volvadvit.internshipparsing.service;

import com.volvadvit.internshipparsing.model.SourceURL;

/**
 * @author Vadim Volkov volvadvit@gmail.com
 * @since 10.10.2021
 */

public interface UrlService {
    SourceURL validate(SourceURL model);
    SourceURL save(SourceURL model);
}
