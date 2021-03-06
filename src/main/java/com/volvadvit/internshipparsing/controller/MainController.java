package com.volvadvit.internshipparsing.controller;

import com.volvadvit.internshipparsing.constant.ResponseMapper;
import com.volvadvit.internshipparsing.model.UrlDTO;
import com.volvadvit.internshipparsing.service.ParsingService;
import com.volvadvit.internshipparsing.service.UrlService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;

/**
 * @author Vadim Volkov
 * volvadvit@gmail.com
 */

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class MainController {

    private final ParsingService parsingService;
    private final UrlService urlService;

    @GetMapping
    public String greeting() {
        return "greeting";
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ResponseMapper> getWordsCount(UrlDTO input) {
        return ResponseEntity.ok().body(
                new ResponseMapper(
                        new Timestamp(System.currentTimeMillis()),
                        HttpStatus.OK.value(),
                        "words count from url: " + input.getUrl(),
                        parsingService.getWordsFromHtml(input))
        );
    }
}


