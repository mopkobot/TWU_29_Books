package com.thoughtworks.twu.persistence;

import com.google.api.services.books.model.Volumes;
import com.thoughtworks.twu.domain.Book;
import com.thoughtworks.twu.persistence.googleapi.GoogleSearchClient;
import com.thoughtworks.twu.persistence.googleapi.GoogleTranslator;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GoogleBookSourceTest {
    @Test
    public void shouldFindBooks() throws IOException {
        GoogleSearchClient googleSearchClient = mock(GoogleSearchClient.class);
        GoogleTranslator googleTranslator = mock(GoogleTranslator.class);

        Volumes volumes = new Volumes();
        when(googleSearchClient.performSearch("Potter", "title")).thenReturn(volumes);

        List<Book> expectedBooks = new ArrayList<Book>();
        expectedBooks.add(new Book("J.K. Rowling", "Potter", "blah", "blah", "blah", "blah"));
        when(googleTranslator.translate(volumes)).thenReturn(expectedBooks);

        GoogleBookSource googleBookSource = new GoogleBookSource(googleSearchClient, googleTranslator);

        List<Book> books = googleBookSource.search("Potter", "title");
        assertThat(books, equalTo(expectedBooks));
    }
}
