package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.Book;
import com.thoughtworks.twu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

//Understands that response to url "viewbook" and send commands to its associated view
@Controller
public class BookViewController {
    public static final String COULD_NOT_FIND_BOOK = "Could not find book";
    public static final String RECOMMENDED_SUCCESFULLY = "Book was recommended successfully";
    private BookService bookService;

    @Autowired
    public BookViewController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/viewbook", method = RequestMethod.GET)
    public ModelAndView viewBook(@RequestParam(value = "booktitle", defaultValue = "") String title) {
        ModelAndView modelAndView = new ModelAndView("viewbook");
        Book book = bookService.getBookByTitle(title);
        if (book == null){
            return modelAndView.addObject("bookNotFound", COULD_NOT_FIND_BOOK);
        }

        return modelAndView.addObject("book", book);
    }

    @RequestMapping(value = "/recommend", method = RequestMethod.POST)
    public ModelAndView recommend(@RequestParam(value = "bookId", defaultValue = "") String bookId) {
        Book book = bookService.getBookByID(Integer.parseInt(bookId));
        ModelAndView view;
        if (book == null) {
            view = new ModelAndView(new RedirectView("/viewbook?booktitle=", true));
            return view;
        }
        bookService.updateRecommendCount(book);
        view = new ModelAndView(new RedirectView("/viewbook?booktitle="+ book.getTitle(), true));
        view.addObject("notification", RECOMMENDED_SUCCESFULLY);
        return view;
    }
}
