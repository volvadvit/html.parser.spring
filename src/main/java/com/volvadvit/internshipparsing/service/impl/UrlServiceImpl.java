package com.volvadvit.internshipparsing.service.impl;

import com.volvadvit.internshipparsing.model.UrlDTO;
import com.volvadvit.internshipparsing.repository.UrlRepo;
import com.volvadvit.internshipparsing.service.UrlService;
import com.volvadvit.internshipparsing.utils.LogUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Vadim Volkov
 * volvadvit@gmail.com
 */

@Service
@Slf4j
@AllArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final UrlRepo urlRepo;

    @Override
    public UrlDTO save(UrlDTO model) {
        log.info("Trying to save urlModel");

        if (model != null) {
            return urlRepo.save(model);
        } else {
            LogUtils.info("Failed to save entity. SourceUrl object is invalid");
            throw new IllegalArgumentException("SourceUrl object is invalid");
        }
    }

    @Override
    public String getHtmlByUrl(String input) {
        HttpURLConnection conn = null;
        StringBuilder stringBuilder = new StringBuilder();
        String result;

        try {
            URL url = new URL(input);
            conn = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            result = stringBuilder.toString();

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
}
