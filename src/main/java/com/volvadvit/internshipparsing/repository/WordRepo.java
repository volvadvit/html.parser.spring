package com.volvadvit.internshipparsing.repository;

import com.volvadvit.internshipparsing.model.WordToCount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Vadim Volkov volvadvit@gmail.com
 * @since 10.10.2021
 */

@Repository
public interface WordRepo extends CrudRepository<WordToCount, Long> {
}
