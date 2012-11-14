package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.User;
import com.thoughtworks.twu.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@Controller
public class WantToReadBookControllerTest {
    public static final String ERROR_MESSAGE = "You have already added the book!";
    public static final String NOTIFICATION_MESSAGE = "Book was successfully added to want to read list";
    private UserService userService;

    private WantToReadBookController controller;
    private User user;

    @Before
    public void setUp() {
        userService = mock(UserService.class);
        controller = new WantToReadBookController(userService, null);
        user = new User("test.twu", "test");
    }

    @Test
    public void shouldSuccessfullyAddBookToWantToReadList() {
        ModelAndView modelAndView = controller.markBookAsWantToRead(1, user);

        String actual = (String) modelAndView.getModel().get("notification");
        assertThat(actual, is(NOTIFICATION_MESSAGE));
    }

    @Test
    public void shouldNotAddBookToWantTOReadListWhenTheBookIsAlreadyAdded() {
        when(userService.isMarkedAsWantToRead(user.getCasname(), 1)).thenReturn(false);
        controller.markBookAsWantToRead(1, user);

        when(userService.isMarkedAsWantToRead(user.getCasname(), 1)).thenReturn(true);
        ModelAndView modelAndView = controller.markBookAsWantToRead(1, user);

        verify(userService, times(1)).markBookAsWantToRead(1, user.getCasname());
        String actual = (String) modelAndView.getModel().get("notification");
        assertThat(actual, is(ERROR_MESSAGE));
    }
}
