package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.User;
import com.thoughtworks.twu.service.UserService;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserControllerTest {
    private UserService userService;
    private UserController userController;
    private User user = new User("foo", "foobar");

    @Test
    public void shouldRedirectToWelcomePageIfUserIsRegistered() {
        userService = createUserServiceForRegisteredUser();
        userController = new UserController(userService);

        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
        httpServletRequest.setRemoteUser("foo");
        ModelAndView redirect = userController.redirect(httpServletRequest);

        User actualUser = (User) redirect.getModel().get("user");
        assertThat(actualUser.getName(), is(user.getName()));
    }

    @Test
    public void shouldRedirectToCreateUserPageIfUserIsUnRegistered() {
        userService = createUserServiceForUnRegisteredUser();
        userController = new UserController(userService);
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.setRemoteUser("foo");
        ModelAndView redirect = userController.redirect(mockHttpServletRequest);

        assertThat(redirect.getViewName(), is("create-user-profile"));
    }

    private UserService createUserServiceForRegisteredUser() {
        UserService service = mock(UserService.class);
        when(service.isUserExisted("foo")).thenReturn(true);
        when(service.getUserByCasname("foo")).thenReturn(user);
        return service;
    }

    private UserService createUserServiceForUnRegisteredUser() {
        UserService service = mock(UserService.class);
        when(service.isUserExisted("foo")).thenReturn(false);
        return service;
    }
}
