package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.Book;
import com.thoughtworks.twu.domain.User;
import com.thoughtworks.twu.service.BookService;
import com.thoughtworks.twu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@SessionAttributes("user")
public class WantToReadBookController {
    public static final String RECOMMENDED_SUCCESFULLY = "Book was successfully added to want to read list";
    public static final String ERROR_MESSAGE = "You have already added the book!";

    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    @Autowired
    public WantToReadBookController(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @RequestMapping(value = "/add-book-to-want", method = RequestMethod.POST)
    public RedirectView markBookAsWantToRead(@RequestParam("bookId") int
                                                         bookId,
                                             @ModelAttribute("user") User user) {
        Book book = bookService.getBookByID(bookId);
        if (userService.isMarkedAsWantToRead(user.getCasname(), bookId)) {
            return new RedirectView("/viewbook?booktitle="+ book.getTitle() +
                    "&notification=" + ERROR_MESSAGE, true);
        }
        userService.markBookAsWantToRead(bookId, user.getCasname());
        return new RedirectView("/viewbook?booktitle="+ book.getTitle() + "&notification=" + RECOMMENDED_SUCCESFULLY, true);
    }


    @RequestMapping(value = "/check-book", method = RequestMethod.POST)
    @ResponseBody
    public String checkIfBookIsPresent(@RequestParam("bookId") int bookId,
                                             @ModelAttribute("user") User user) {
        if (userService.isMarkedAsWantToRead(user.getCasname(), bookId)) {
            return "already saved";
        }
        return "saved";

    }
}
