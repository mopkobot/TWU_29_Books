package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.Book;
import com.thoughtworks.twu.domain.User;
import com.thoughtworks.twu.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WelcomeControllerTest {
    private User user;
    private UserService userService;
    private WelcomeController welcomeController;

    @Before
    public void setUp() {
        user = new User("test.twu", "test");
        userService = mock(UserService.class);
        welcomeController = new WelcomeController(userService);
    }

    @Test
    public void shouldAddListOfWantToReadBooksToModelForTheLoggedInUser() throws IOException {
        List<Book> expectedBooks = new ArrayList<>();
        expectedBooks.add(getBook());

        when(userService.getBooksFromWantToReadList(user.getCasname())).thenReturn(expectedBooks);

        ModelAndView actualModelAndView = welcomeController.welcome(user);

        List<Book> actualBooks = (List<Book>) actualModelAndView.getModel().get("books");
        assertThat(actualBooks, is(expectedBooks));
        verify(userService).getBooksFromWantToReadList(user.getCasname());
    }


    @Test
    public void shouldSendBookNotFoundMessageIfNoBooksInWantToReadList() {
        List<Book> emptyBooksList = new ArrayList<>();

        when(userService.getBooksFromWantToReadList(user.getCasname())).thenReturn(emptyBooksList);

        ModelAndView actualModelAndView = welcomeController.welcome(user);

        String bookNotFoundMessage = (String) actualModelAndView.getModel().get("bookNotFound");

        assertThat(bookNotFoundMessage, is("Sorry! You haven't added any book to your reading list"));
        verify(userService).getBooksFromWantToReadList(user.getCasname());
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
