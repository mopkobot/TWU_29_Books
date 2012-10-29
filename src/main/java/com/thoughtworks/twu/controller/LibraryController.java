package com.thoughtworks.twu.controller;


import org.springframework.web.servlet.ModelAndView;

public class LibraryController {

    public ModelAndView searchPage() {
        return new ModelAndView("/search_book");
    }

    public ModelAndView searchResults() {
        return new ModelAndView("");

    }
}