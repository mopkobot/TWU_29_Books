package com.thoughtworks.twu.controller;


import com.thoughtworks.twu.domain.Book;
import com.thoughtworks.twu.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
public class SearchController {
    public static final String BOOKS_NOT_FOUND = "No books were found " +
            "matching your search criteria. Please try again with a new search criteria.";

    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(value = "/search_book", method = RequestMethod.GET)
    public ModelAndView searchPage() {
        return new ModelAndView("searchBook");
    }


    @RequestMapping(value = "/search_book", method = RequestMethod.POST)
    public ModelAndView resultsPage(@RequestParam(value = "searchValue", defaultValue = "") String searchValue, @RequestParam(value = "searchType", defaultValue = "") String searchType) throws IOException {
        ModelAndView modelAndView = new ModelAndView("searchBook");
        if (!searchValue.isEmpty()) {
            List<Book> books = searchService.findBooks(searchValue, searchType);
            if(books.size() == 0){
                modelAndView.addObject("error", BOOKS_NOT_FOUND);
            }
            modelAndView.addObject("books", books);
            modelAndView.addObject("searchValue", searchValue);
        }
        return modelAndView;
    }

}