package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.Book;
import com.thoughtworks.twu.domain.User;
import com.thoughtworks.twu.service.BookService;
import com.thoughtworks.twu.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.view.RedirectView;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@Controller
public class WantToReadBookControllerTest {
    public static final String ERROR_MESSAGE = "You have already added the book!";
    public static final String NOTIFICATION_MESSAGE = "Book was successfully added to want to read list";
    private UserService userService;

    private WantToReadBookController controller;
    private User user;
    private BookService bookService;

    @Before
    public void setUp() {
        userService = mock(UserService.class);
        bookService = createBookService();
        controller = new WantToReadBookController(userService);
        user = new User("test.twu", "test");
    }

    @Test
    public void shouldSuccessfullyAddBookToWantToReadList() {
        controller.markBookAsWantToRead(1, user);
        verify(userService).markBookAsWantToRead(1, user.getCasname());
    }

    @Test
    public void
    shouldRedirectToRightBookWithCorrectNotificationWhenBookIsPutInList() {
        when(userService.isMarkedAsWantToRead(user.getCasname(),
                1)).thenReturn(false);
        RedirectView redirectView = controller.markBookAsWantToRead(1, user);
        String actualUrl = redirectView.getUrl();
        String expected = "/viewbook?bookId=1&notification=" + NOTIFICATION_MESSAGE;
        assertThat(actualUrl, is(expected));
    }

    @Test
    public void
    shouldRedirectToRightBookWithCorrectNotificationWhenBookIsNotPutInList() {
        when(userService.isMarkedAsWantToRead(user.getCasname(),
                1)).thenReturn(true);
        RedirectView redirectView = controller.markBookAsWantToRead(1, user);
        String actualUrl = redirectView.getUrl();
        String expected = "/viewbook?bookId=1&notification=" + ERROR_MESSAGE;
        assertThat(actualUrl, is(expected));
    }

    @Test
    public void shouldNotAddBookToWantTOReadListWhenTheBookIsAlreadyAdded() {
        when(userService.isMarkedAsWantToRead(user.getCasname(), 1)).thenReturn(false);
        controller.markBookAsWantToRead(1, user);

        when(userService.isMarkedAsWantToRead(user.getCasname(), 1)).thenReturn(true);
        controller.markBookAsWantToRead(1, user);

        verify(userService, times(1)).markBookAsWantToRead(1, user.getCasname());
    }

    @Test
    public void shouldReturnValidResponseWhenBookNotAlreadySaved(){
        when(userService.isMarkedAsWantToRead(user.getCasname(), 1)).thenReturn(false);
        String actual = controller.checkIfBookIsPresent(1,user);
        assertThat(actual, is("saved"));
    }

    @Test
    public void shouldReturnValidResponseWhenBookAlreadySaved(){
        when(userService.isMarkedAsWantToRead(user.getCasname(),
                1)).thenReturn(true);
        String actual = controller.checkIfBookIsPresent(1,user);
        assertThat(actual, is("already saved"));
    }

    private BookService createBookService() {
        BookService bookService = mock(BookService.class);
        when(bookService.getBookByID(1)).thenReturn(new Book("author",
                "title", "", "", "", ""));
        return bookService;
    }
}
