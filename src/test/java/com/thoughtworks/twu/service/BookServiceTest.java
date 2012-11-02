package com.thoughtworks.twu.service;

import com.thoughtworks.twu.domain.Book;
import com.thoughtworks.twu.persistence.BookMapper;
import com.thoughtworks.twu.service.BookService;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    private final String title = "title";
    private final String author = "author";
    private final String image = "http://ecx.images-amazon.com/images/I/51MU5VilKpL._BO2,204,203,200_PIsitb-sticker-arrow-click,TopRight,35,-76_AA300_SH20_OU01_.jpg";
    private final String description = "this is a book about magic, I love it very much!!";
    private final String ISBN10 = "0156027321";
    private final String ISBN13 = "978-0156027328";
    private Book book = new Book(author, title, image, description, ISBN10, ISBN13);
    private BookMapper mockBookMapper = mock(BookMapper.class);
    private BookService bookService = new BookService(mockBookMapper);

    @Test
    public void shouldGetBookInfoFromDB(){
        when(mockBookMapper.getBookByTitle(title)).thenReturn(book);
        Book result = bookService.getBookByTitle(title);
        assertThat(result, equalTo(book));
    }

    @Test
    public void shouldInsertBookToDB(){
        bookService.insertBook(book);
        verify(mockBookMapper).insertBook(book);
    }
}
