package com.volvadvit.internshipparsing.repository;

import com.volvadvit.internshipparsing.model.WordToCount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Vadim Volkov
 * volvadvit@gmail.com
 */

@Repository
public interface WordRepo extends CrudRepository<WordToCount, Long> {
}
