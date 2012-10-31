package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.Book;
import com.thoughtworks.twu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//Understands that response to url "viewbook" and send commands to its associated view
@Controller
public class BookViewController {
    private BookService bookService;

    @Autowired
    public BookViewController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/viewbook", method = RequestMethod.GET)
    public ModelAndView viewBook(@RequestParam(value = "booktitle", defaultValue = "") String title) {
        ModelAndView modelAndView = new ModelAndView("viewbook");
        Book book = bookService.getBookByTitle(title);
        if (book != null){
            return modelAndView.addObject("book", book);
        }
        return modelAndView.addObject("bookNotFound", "Could not find book");
    }
}
