package com.savostian.ebooks.controllers;

import com.savostian.ebooks.database.entity.OperationsOnBooks;
import com.savostian.ebooks.database.repos.OperationsOnBooksRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Date;
import java.time.LocalDate;

@Controller
public class OperationsOnBooksController {
    @Autowired
    private OperationsOnBooksRepos operationsOnBooksRepos;

    @PostMapping("/operations/save")
    String createNewOperation(@ModelAttribute("operation") OperationsOnBooks operationsOnBooks) {
        operationsOnBooks.setIssueDate(Date.valueOf(LocalDate.now()));

        operationsOnBooksRepos.save(operationsOnBooks);
        return "redirect:/";
    }
}
