package com.thoughtworks.twu.controller;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LibraryControllerTest {

    @Test
    public void shouldRenderSearchPage() throws Exception {
        LibraryController libraryController = new LibraryController();
        ModelAndView libraryModelAndView = libraryController.searchPage();

        String actualViewName = libraryModelAndView.getViewName();

        assertThat(actualViewName, is("/search_book"));
    }

    @Test
    public void shouldRetrieveResultFromBookSearch() throws Exception {
        LibraryController libraryController = new LibraryController();
        ModelAndView libraryModelAndView = libraryController.searchResults();

    }

}