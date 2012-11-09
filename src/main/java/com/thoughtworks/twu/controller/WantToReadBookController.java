package com.thoughtworks.twu.controller;

import org.springframework.web.servlet.ModelAndView;

public class WantToReadBookController {
    public ModelAndView markBookAsWantToRead(int bookId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("notification","Book was successfully added to want to read list");
        return modelAndView;
    }
}
