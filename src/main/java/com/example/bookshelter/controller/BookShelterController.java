package com.example.bookshelter.controller;

import com.example.bookshelter.entity.Book;
import com.example.bookshelter.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/")
public class BookShelterController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public String allBooks(@RequestParam(required = false, defaultValue = "") String filterByName, Model model) {
        Iterable<Book> books;

        if (filterByName != null && !filterByName.isEmpty()) {
            books = bookRepository.findBookByBookName(filterByName);
        } else {
            books = bookRepository.findAll();
        }
        model.addAttribute("books", books);
        model.addAttribute("filterByName", filterByName);

        return "books";
    }

    @PostMapping("/books")
    public String addBooks(@RequestParam String bookName, String dateOfBook, Map<String, Object> model) {
        if (bookName.length() > 10) {
            model.put("message", "Long name");
            model.put("books", bookRepository.findAll());
            model.put("filterByName", "");
        } else {
            Iterable<Book> bookFromDb = bookRepository.findBookByBookName(bookName);


            if (bookFromDb.iterator().hasNext()) {
                model.put("message", "Book already exists!");
                model.put("books", bookFromDb);
                model.put("filterByName", bookFromDb);
            } else {
                Book book = new Book(bookName, LocalDate.parse(dateOfBook));
                bookRepository.save(book);

                model.put("books", bookRepository.findAll());
                model.put("filterByName", "");
            }
        }

        return "books";
    }
}
