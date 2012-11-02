package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.controller.SearchController;
import com.thoughtworks.twu.domain.Book;
import com.thoughtworks.twu.service.SearchService;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SearchControllerTest {

    @Test
    public void shouldRenderSearchPage() throws Exception {

        SearchService searchService = mock(SearchService.class);
        SearchController searchController = new SearchController(searchService);
        ModelAndView searchModelANdView = searchController.searchPage();

        String actualViewName = searchModelANdView.getViewName();

        assertThat(actualViewName, is("/search_book"));
    }

    @Test
    public void shouldRetrieveResultFromBookSearch() throws Exception {


        SearchService searchService = mock(SearchService.class);
        List expectedBooks = new ArrayList<Book>();
        when(searchService.findBooks()).thenReturn(expectedBooks);

        SearchController searchController = new SearchController(searchService);
        List<Book> actual = (List<Book>) searchController.resultsPage("Potter", "title").getModel().get("book");

        assertThat(actual, notNullValue());
        assertThat(actual, is(expectedBooks));

    }


}