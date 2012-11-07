package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.Book;
import com.thoughtworks.twu.service.SearchService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SearchControllerTest {

    private SearchService searchService;
    private SearchController searchController;

    @Before
    public void setUp(){
        searchService = mock(SearchService.class);
        searchController = new SearchController(searchService);
    }

    @Test
    public void shouldRenderSearchPage() throws Exception {
        SearchController searchController = new SearchController(searchService);
        ModelAndView searchModelANdView = searchController.searchPage();

        String actualViewName = searchModelANdView.getViewName();

        assertThat(actualViewName, is("searchBook"));
    }

    @Test
    public void shouldRetrieveResultFromBookSearch() throws Exception {
        List expectedBooks = new ArrayList<Book>();
        expectedBooks.add(new Book("J.K. Rowling", "Potter", "blah", "blah", "blah", "blah"));
        when(searchService.findBooks("Potter", "title")).thenReturn(expectedBooks);

        ModelAndView modelAndView = searchController.resultsPage("Potter", "title");
        List<Book> actual = (List<Book>) modelAndView.getModel().get("books");

        assertThat(actual, is(expectedBooks));
    }

    @Test
    public void shouldNotRetrieveResultFromBookSearchWhenTitleIsEmpty() throws Exception {
        ModelAndView modelAndView = searchController.resultsPage("", "title");
        List<Book> actual = (List<Book>) modelAndView.getModel().get("book");

        assertThat(actual,equalTo(null));
    }

}