package com.savostian.ebooks.utils;

import com.savostian.ebooks.database.entity.Book;
import com.savostian.ebooks.database.repos.BookRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepos bookRepository;

    public List<Book> getSortedBooks(String sortBy, String sortOrder) {
        // Використовуйте bookRepository для отримання списку книг та сортування їх
        // sortBy - поле для сортування, sortOrder - порядок сортування (asc або desc)
        Sort sort = Sort.by(sortOrder.equals("asc") ? Sort.Order.asc(sortBy) : Sort.Order.desc(sortBy));
        return bookRepository.findAll(sort);
    }
}
