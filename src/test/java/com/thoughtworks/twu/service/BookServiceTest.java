package com.thoughtworks.twu.service;

import com.thoughtworks.twu.domain.Book;
import com.thoughtworks.twu.persistence.BookMapper;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    private final String title = "title";
    private final String author = "author";
    private final String image = "http://ecx.images-amazon.com/images/I/51MU5VilKpL._BO2,204,203,200_PIsitb-sticker-arrow-click,TopRight,35,-76_AA300_SH20_OU01_.jpg";
    private final String description = "this is a book about magic, I love it very much!!";
    private final String ISBN10 = "0156027321";
    private final String ISBN13 = "978-0156027328";

    private Book book;
    private BookMapper mockBookMapper;
    private BookService bookService;

    private final int id = 1;

    @Before
    public void setUp(){
        book = new Book(author, title, image, description, ISBN10, ISBN13);
        mockBookMapper = mock(BookMapper.class);
        bookService = new BookService(mockBookMapper);
    }

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

    @Test
    public void shouldReturnTrueIfBookExistedInDB(){
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(book);
        when(mockBookMapper.getBooksByTitle(title)).thenReturn(books);
        assertThat(bookService.isBookFromResultsListInDB(book), is(true));
    }

    @Test
    public void shouldReturnFalseIfBookIsNotExistedInDB(){
        Book anotherBook = new Book("Summer", title, image, description, ISBN10, ISBN13);
        assertThat(bookService.isBookFromResultsListInDB(anotherBook), is(false));
    }

    @Test
    public void shouldGetBookById() {
        when(mockBookMapper.getBookById(id)).thenReturn(book);
        Book result = bookService.getBookByID(id);

        assertThat(result, equalTo(book));
        verify(mockBookMapper).getBookById(id);
    }

    @Test
    public void shouldUpdateRecommendCountByOne() {
        int recommendCount = bookService.updateRecommendCount(book);

        assertThat(recommendCount, equalTo(1));
        verify(mockBookMapper).updateRecommendCount(book);
    }

    @Test
    public void shouldReturnBookWithIDWhenTheBookIsInDB(){
        ArrayList<Book> bookList = new ArrayList<Book>();
        Book result = book;
        result.setId(2);
        bookList.add(result);

        when(mockBookMapper.getBooksByTitle(book.getTitle())).thenReturn(bookList);

        book = bookService.updateBook(book);
        assertThat(book.getId(),is(2));
    }
}
