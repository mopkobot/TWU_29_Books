package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.User;
import com.thoughtworks.twu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("user")
public class WantToReadBookController {
    public static final String NOTIFICAION_MESSAGE = "Book was successfully added to want to read list";

    @Autowired
    private UserService userService;

    @Autowired
    public WantToReadBookController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/add-book-to-want", method = RequestMethod.POST)
    @ResponseBody
    public String markBookAsWantToRead(@RequestParam("bookId") int bookId,
                                             @ModelAttribute("user") User user) {

        if (userService.isMarkedAsWantToRead(user.getCasname(), bookId)) {
            return "already saved";
        }
        userService.markBookAsWantToRead(bookId, user.getCasname());
        return "saved";
    }
}
