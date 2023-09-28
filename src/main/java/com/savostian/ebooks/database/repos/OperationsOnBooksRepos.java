package com.savostian.ebooks.database.repos;

import com.savostian.ebooks.database.entity.OperationsOnBooks;
import org.springframework.data.repository.CrudRepository;

public interface OperationsOnBooksRepos extends CrudRepository<OperationsOnBooks, Integer> {

}
