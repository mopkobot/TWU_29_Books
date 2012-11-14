package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.Book;
import com.thoughtworks.twu.service.BookService;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class AddBookControllerTest {
    private BookService bookService;
    private AddBookController addBookController;


    @Before
    public void setUp() {
        bookService = mock(BookService.class);
        addBookController = new AddBookController(bookService);
    }


    @Test
    public void shouldAddBookToDBIfNotInSystem(){
        Book expected = getBook();
        when(bookService.isBookInDB(expected)).thenReturn(false);
        addBookController.addBook( expected.getAuthor(),expected.getTitle(),expected.getImage(),expected.getDescription(),expected.getISBN10(),expected.getISBN13());
        verify(bookService).insertBook(expected);
    }

    @Test
    public void shouldNotAddBookToDBIfItIsInSystem(){
        Book expected = getBook();
        when(bookService.isBookInDB(expected)).thenReturn(true);
        addBookController.addBook( expected.getAuthor(),expected.getTitle(),expected.getImage(),expected.getDescription(),expected.getISBN10(),expected.getISBN13());
        verify(bookService,times(0)).insertBook(expected);
    }

    @Test
    public  void  shouldRetrieveBookByTitleFromDB(){
        Book expected = getBook();
        addBookController.addBook( expected.getAuthor(),expected.getTitle(),expected.getImage(),expected.getDescription(),expected.getISBN10(),expected.getISBN13());
        verify(bookService).getBookByTitle(expected.getTitle());
    }

    private Book getBook() {
        String title = "A Present Book";
        String author = "author";
        String image = "http://ecx.images-amazon.com/images/I/51MU5VilKpL._BO2,204,203,200_PIsitb-sticker-arrow-click,TopRight,35,-76_AA300_SH20_OU01_.jpg";
        String description = "This is a book about life, I love it very much!!";
        String ISBN10 = "0156027321";
        String ISBN13 = "978-0156027328";
        return new Book(author, title, image, description, ISBN10, ISBN13);
    }

}
