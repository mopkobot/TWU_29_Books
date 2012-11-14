package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.User;
import com.thoughtworks.twu.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Controller;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@Controller
public class WantToReadBookControllerTest {
    private UserService userService;

    private WantToReadBookController controller;
    private User user;

    @Before
    public void setUp() {
        userService = mock(UserService.class);
        controller = new WantToReadBookController(userService);
        user = new User("test.twu", "test");
    }

    @Test
    public void shouldSuccessfullyAddBookToWantToReadList() {
        String response = controller.markBookAsWantToRead(1, user);

        assertThat(response, is("saved"));
    }

    @Test
    public void shouldNotAddBookToWantTOReadListWhenTheBookIsAlreadyAdded() {
        when(userService.isMarkedAsWantToRead(user.getCasname(),1)).thenReturn(false);
        controller.markBookAsWantToRead(1, user);

        when(userService.isMarkedAsWantToRead(user.getCasname(),1)).thenReturn(true);
        String response = controller.markBookAsWantToRead(1, user);

        verify(userService, times(1)).markBookAsWantToRead(1, user.getCasname());
        assertThat(response, is("already saved"));
    }
}
