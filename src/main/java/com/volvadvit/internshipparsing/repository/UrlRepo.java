package com.volvadvit.internshipparsing.repository;

import com.volvadvit.internshipparsing.model.UrlDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Vadim Volkov
 * volvadvit@gmail.com
 */

@Repository
public interface UrlRepo extends CrudRepository<UrlDTO, Long> {
}
