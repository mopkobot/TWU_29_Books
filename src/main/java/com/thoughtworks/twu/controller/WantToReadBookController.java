package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.User;
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
    public WantToReadBookController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/add-book-to-want", method = RequestMethod.POST)
    public RedirectView markBookAsWantToRead(@RequestParam(value = "bookId",
            defaultValue = "") int bookId,
                                             @ModelAttribute("user") User user) {
        if (userService.isMarkedAsWantToRead(user.getCasname(),
                bookId)) {
            return new RedirectView("/viewbook?bookId="+ bookId +
                    "&notification=" + ERROR_MESSAGE, true);
        }
        userService.markBookAsWantToRead(bookId,
                user.getCasname());
        return new RedirectView("/viewbook?bookId="+ bookId +
                "&notification=" + RECOMMENDED_SUCCESFULLY, true);
    }


    @RequestMapping(value = "/check-book", method = RequestMethod.POST)
    @ResponseBody
    public String checkIfBookIsPresent(@RequestParam(value="bookId") int
                                                   bookId,
                                             @ModelAttribute("user") User user) {
        if (userService.isMarkedAsWantToRead(user.getCasname(), bookId)) {
            return "already saved";
        }
        return "saved";

    }
}
