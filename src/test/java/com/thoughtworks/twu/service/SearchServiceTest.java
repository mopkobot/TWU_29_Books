package com.thoughtworks.twu.service;

import com.thoughtworks.twu.domain.Book;
import com.thoughtworks.twu.persistence.GoogleBookSource;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SearchServiceTest {
    @Test
    public void shouldFindBooksByTitle() throws IOException {
        List<Book> expectedBooks = new ArrayList<Book>();
        expectedBooks.add(new Book("J.K. Rowling", "Potter", "blah", "blah", "blah", "blah"));

        GoogleBookSource googleBookSource = mock(GoogleBookSource.class);
        when(googleBookSource.search("Potter", "title")).thenReturn(expectedBooks);

        SearchService searchService = new SearchService(googleBookSource);
        List<Book> results = searchService.findBooks("Potter", "title");

        assertThat(results, equalTo(expectedBooks));
    }
}
