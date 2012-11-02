package com.thoughtworks.twu.domain;

import com.thoughtworks.twu.domain.Book;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

//Ensures book domain satisfy client's requirement
public class BookTest {

    private Book book;

    @Before
    public void setUp(){
        book = new Book("author", "title", "image-src", "description", "isbn10", "isbn13");
    }

    @Test
    public void shouldHaveAuthor() {
        assertThat(book.getAuthor(), is("author"));
        assertThat(book.getTitle(), is("title"));
        assertThat(book.getImage(), is("image-src"));
        assertThat(book.getDescription(), is("description"));
        assertThat(book.getISBN10(), is("isbn10"));
        assertThat(book.getISBN13(), is("isbn13"));
    }
}
