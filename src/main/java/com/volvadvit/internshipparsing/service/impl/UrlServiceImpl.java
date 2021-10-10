package com.volvadvit.internshipparsing.service.impl;

import com.volvadvit.internshipparsing.model.SourceURL;
import com.volvadvit.internshipparsing.repository.SourceUrlRepo;
import com.volvadvit.internshipparsing.service.UrlService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author Vadim Volkov volvadvit@gmail.com
 * @since 10.10.2021
 */

@Service
@Slf4j
@AllArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final SourceUrlRepo urlRepo;

    @Override
    public SourceURL save(SourceURL model) {
        log.info("Trying to save urlModel");

        if (model != null) {
            return urlRepo.save(model);
        } else {
            //TODO save log to file
            throw new IllegalArgumentException("SourceUrl object is invalid");
        }
    }

    @Override
    public SourceURL validate(SourceURL model) {
        log.info("Trying to verify url: {}", model.getUrl());

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<SourceURL>> violations = validator.validate(model);

        if (!violations.isEmpty()) {
            //TODO save log to file
            throw new ConstraintViolationException(violations);
        } else {
            return urlRepo.save(model);
        }
    }
}
