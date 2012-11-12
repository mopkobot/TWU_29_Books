package com.thoughtworks.twu.persistence.googleapi;

import com.google.api.services.books.Books;
import com.google.api.services.books.model.Volumes;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GoogleSearchClientTest {

    private GoogleSearchClient googleSearchClient;
    private Books books;

    @Before
    public void setUp() {
        books = mock(Books.class);
        googleSearchClient = new GoogleSearchClient(books);
    }

    @Test
    public void shouldBuildCorrectQueryWhenSearchByTitle() {
        String expectedQuery = "intitle:happy";
        String resultQuery = googleSearchClient.buildQuery("happy", "title");
        assertThat(resultQuery, equalTo(expectedQuery));
    }

    @Test
    public void shouldBuildCorrectQueryWhenSearchByAuthor() {
        String expectedQuery = "inauthor:Jack";
        String resultQuery = googleSearchClient.buildQuery("Jack", "author");
        assertThat(resultQuery, equalTo(expectedQuery));
    }

    @Test
    public void shouldBuildCorrectQueryWhenSearchByISBN() {
        String expectedQuery = "isbn:1234567890";
        String resultQuery = googleSearchClient.buildQuery("1234567890", "isbn");
        assertThat(resultQuery, equalTo(expectedQuery));
    }

    @Test
    public void shouldExecuteGoogleAPI() throws IOException {
        Books.Volumes volumes = mock(Books.Volumes.class);
        when(books.volumes()).thenReturn(volumes);

        Books.Volumes.List list = mock(Books.Volumes.List.class);
        when(volumes.list("isbn:1234567890")).thenReturn(list);

        when(list.setMaxResults((long) 20)).thenReturn(list);
        when(list.setOrderBy("RELEVANCE")).thenReturn(list);
        Volumes expectedVolumes = new Volumes();
        when(list.execute()).thenReturn(expectedVolumes);

        Volumes result = googleSearchClient.performSearch("1234567890", "isbn");
        assertThat(result, is(expectedVolumes));
    }

    //TODO: move to functional testing
    @Test
    public void shouldReturnOnlyTwentyResults() throws IOException {
        GoogleSearchClient searchClient = new GoogleSearchClient();
        int searchResult  = searchClient.performSearch("Happy", "title").getItems().size();

        assertThat(searchResult, is(20));
    }
}
