package com.savostian.ebooks.database.repos;

import com.savostian.ebooks.database.entity.Book;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepos extends CrudRepository<Book, Integer> {
     List<Book> findByTitleContainingOrAuthorContainingOrGenreContaining(String keyword, String keyword1, String keyword2);
     List<Book> findAll(Sort sort);
     Book findBookById(Integer id);
     Book save(Book book);
     //Book up(Book book);
     //Book deleteBookById(Integer id);
}
