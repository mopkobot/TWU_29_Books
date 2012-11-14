package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.User;
import com.thoughtworks.twu.service.BookService;
import com.thoughtworks.twu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("user")
public class WantToReadBookController {
    public static final String NOTIFICAION_MESSAGE = "Book was successfully added to want to read list";
    public static final String ERROR_MESSAGE = "You have already added the book!";

    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    @Autowired
    public WantToReadBookController(UserService userService, BookService bookService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/add-book-to-want", method = RequestMethod.POST)
    public ModelAndView markBookAsWantToRead(@RequestParam("bookId") int bookId,
                                             @ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView("viewbook");
        if (userService.isMarkedAsWantToRead(user.getCasname(), bookId)) {
            modelAndView.addObject("notification",ERROR_MESSAGE);
            return modelAndView;
        }
        userService.markBookAsWantToRead(bookId, user.getCasname());
        modelAndView.addObject("notification",NOTIFICAION_MESSAGE);
        return modelAndView;


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
