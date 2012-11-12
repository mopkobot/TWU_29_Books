package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.Book;
import com.thoughtworks.twu.service.BookService;
import org.springframework.web.servlet.ModelAndView;

public class AddBookController {
    public AddBookController(BookService bookService) {

    }

    public ModelAndView addBook(Book expectedBook) {
        return null;
    }
}
