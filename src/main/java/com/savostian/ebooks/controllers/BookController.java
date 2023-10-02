package com.savostian.ebooks.controllers;

import com.savostian.ebooks.database.entity.Book;
import com.savostian.ebooks.database.entity.OperationsOnBooks;
import com.savostian.ebooks.database.repos.BookRepos;
import com.savostian.ebooks.database.repos.OperationsOnBooksRepos;
import com.savostian.ebooks.utils.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookRepos bookRepository;
    @Autowired
    private OperationsOnBooksRepos operationsOnBooksRepos;
    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String index(Model model) {
        List<Book> books = (List<Book>) bookRepository.findAll();
        model.addAttribute("books", books);
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        List<Book> books = bookRepository.findByTitleContainingOrAuthorContainingOrGenreContaining(keyword, keyword, keyword);
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/books")
    public String sort(@RequestParam("sortBy") String sortBy, Model model) {
        List<Book> books = bookService.getSortedBooks(sortBy, "asc" );
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/book/")
    public String getBookById(@RequestParam("bookId")  Integer bookId, Model model) {
        Book book = bookRepository.findBookById(bookId);
        List<OperationsOnBooks> operations= operationsOnBooksRepos.findOperationsOnBooksByBookId(book.getId());
        model.addAttribute("book", book);
        model.addAttribute("operations", operations);
        return "BookPage";
    }

    @PostMapping("/book/save")
    String createNewOperation(@ModelAttribute("book") Book book) {
        bookRepository.save(book);
        return "redirect:/";
    }

}