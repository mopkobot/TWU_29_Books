package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.service.UserService;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserControllerTest {
    private UserService userService;
    private UserController userController;

    @Test
    public void shouldRedirectToWelcomePageIfUserIsRegistered() {
        userService = createUserServiceForRegisteredUser();
        userController = new UserController(userService);

        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
        httpServletRequest.setRemoteUser("foo");
        String redirect = userController.redirect(httpServletRequest);

        assertThat(redirect, is("/welcome"));
    }

    @Test
    public void shouldRedirectToCreateUserPageIfUserIsUnRegistered() {
        userService = createUserServiceForUnRegisteredUser();
        userController = new UserController(userService);
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.setRemoteUser("foo");
        String redirect = userController.redirect(mockHttpServletRequest);

        assertThat(redirect, is("/create-user-profile"));
    }

    private UserService createUserServiceForRegisteredUser() {
        UserService service = mock(UserService.class);
        when(service.isUserExisted("foo")).thenReturn(true);
        return service;
    }

    private UserService createUserServiceForUnRegisteredUser() {
        UserService service = mock(UserService.class);
        when(service.isUserExisted("foo")).thenReturn(false);
        return service;
    }
}
