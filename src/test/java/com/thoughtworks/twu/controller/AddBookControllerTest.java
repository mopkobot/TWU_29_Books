package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.Book;
import com.thoughtworks.twu.service.BookService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddBookControllerTest {
    private BookService bookService;
    private AddBookController addBookController;


    @Before
    public void setUp() {
        bookService = mock(BookService.class);
        addBookController = new AddBookController(bookService);
    }

    @Test
    @Ignore
    public void shouldAddBook() {
        Book expectedBook = getBook();
        ModelAndView modelAndView = addBookController.addBook(expectedBook);
        int bookID = 1;//how to retrieve the book id form the db???
        Book actualBook = (Book)modelAndView.getModel().get("bookId");
        assertThat(expectedBook,is(actualBook));


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
