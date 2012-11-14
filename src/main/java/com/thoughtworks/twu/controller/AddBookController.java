package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.Book;
import com.thoughtworks.twu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AddBookController {

    private BookService bookService;

    @Autowired
    public AddBookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/add_book", method = RequestMethod.POST)
    public RedirectView addBook(@RequestParam(value = "author", defaultValue = "") String author,
                                @RequestParam(value = "title", defaultValue = "") String title,
                                @RequestParam(value = "image", defaultValue = "") String image,
                                @RequestParam(value = "description", defaultValue = "") String description,
                                @RequestParam(value = "ISBN10", defaultValue = "") String ISBN10,
                                @RequestParam(value = "ISBN13", defaultValue = "") String ISBN13) {
        Book book = new Book(author, title, image, description, ISBN10, ISBN13);
        if (!bookService.isBookFromResultsListInDB(book)) {
            bookService.insertBook(book);
        }
        return new RedirectView("/viewbook?bookId=" + bookService.updateBook(book).getId(),true);
    }
}
