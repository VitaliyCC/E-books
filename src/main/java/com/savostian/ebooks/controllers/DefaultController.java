package com.savostian.ebooks.controllers;

import com.savostian.ebooks.database.entity.Book;
import com.savostian.ebooks.database.repos.BookRepos;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class DefaultController{

    private BookRepos bookRepos;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Book> getAllUsers() {
        // This returns a JSON or XML with the users
        return bookRepos.findAll();
    }
}
