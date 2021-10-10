package com.volvadvit.internshipparsing.repository;

import com.volvadvit.internshipparsing.model.SourceURL;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Vadim Volkov volvadvit@gmail.com
 * @since 10.10.2021
 */
public interface SourceUrlRepo extends CrudRepository<SourceURL, Long> {
}
