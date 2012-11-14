package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.Book;
import com.thoughtworks.twu.service.BookService;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.swing.*;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BookViewControllerTest {

    @Test
    public void shouldPassBookToBookViewPageWhenTitleIsValid() throws IOException {
        Book expectedBook = getBook();

        String id = "100";

        ModelAndView modelAndView = bookPageModelAndView(id, true);
        Book bookFromModelAndView = (Book) modelAndView.getModel().get("book");

        assertThat(bookFromModelAndView, equalTo(expectedBook));
    }

    @Test
    public void shouldPassNotFoundMessageWhenTitleIsInvalid() throws Exception {
        String invalidID = "1000";
        String expectedMessage = "Could not find book";
        ModelAndView modelAndView = bookPageModelAndView(invalidID, false);

        String messageFromModelAndView = (String) modelAndView.getModel().get("bookNotFound");
        assertThat(messageFromModelAndView, is(expectedMessage));
    }

    @Test
    @Ignore //We have to find a way to deal with empty or other charactors
    public void shouldPassNotFoundMessageWhenTitleIsEmpty() throws Exception {
        String emptyID = "   ";
        String expectedMessage = "Could not find book";
        ModelAndView modelAndView = bookPageModelAndView(emptyID, false);

        String messageFromModelAndView = (String) modelAndView.getModel().get("bookNotFound");
        assertThat(messageFromModelAndView, is(expectedMessage));
    }

    @Test
    public void shouldHaveEmptyNotificationWhenUserViewsBookForFirstTime(){
        BookService bookService = mock(BookService.class);
        Book book = getBook();
        when(bookService.getBookByTitle(book.getTitle())).thenReturn(book);
        BookViewController bookViewController = new BookViewController(bookService);
        ModelAndView viewBook = bookViewController.viewBook(book.getTitle(), "");
        String actualNotification = (String) viewBook.getModel().get("notification");

        assertThat(actualNotification, is(""));
    }

    @Test
    public void shouldPassANotificationToTheViewThatBookWasRecommended() {
        BookService bookService = mock(BookService.class);
        Book book = getBook();
        when(bookService.getBookByID(0)).thenReturn(book);
        BookViewController bookViewController = new BookViewController(bookService);
        RedirectView viewBook = bookViewController.recommend(0);
        String actualNotification = viewBook.getUrl();

        assertThat(actualNotification, is("/viewbook?booktitle="+book.getTitle()+"&notification="+BookViewController.RECOMMENDED_SUCCESFULLY));
    }

    @Test
    public void shouldVerifyThatBookServiceReturnsBook() {
        Book book = getBook();
        BookService bookService = mock(BookService.class);
        when(bookService.getBookByID(3)).thenReturn(book);
        BookViewController bookViewController = new BookViewController(bookService);
        bookViewController.recommend(3);

        verify(bookService).getBookByID(3);
    }

    @Test
    public void shouldVerifyThatBookServiceUpdatesCount() {
        Book book = getBook();
        BookService bookService = mock(BookService.class);
        when(bookService.getBookByID(3)).thenReturn(book);

        BookViewController bookViewController = new BookViewController(bookService);
        bookViewController.recommend(3);

        verify(bookService).updateRecommendCount(book);
    }

    private ModelAndView bookPageModelAndView(String ID, boolean isValidID) throws IOException {
        BookService bookService = mock(BookService.class);
        Book book = (isValidID) ? getBook() : null;

        when(bookService.getBookByID(Integer.parseInt(ID))).thenReturn(book);

        BookViewController bookViewController = new BookViewController(bookService);
        return bookViewController.viewBook(ID,"");
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
