package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.service.UserService;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginControllerTest {
    private UserService userService;
    private LoginController loginController;

    @Test
    public void shouldRedirectToWelcomePageIfUserIsRegistered() {
        userService = createUserServiceForRegisteredUser();
        loginController = new LoginController(userService);

        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
        httpServletRequest.setRemoteUser("foo");

        String redirect = loginController.redirect(httpServletRequest);
        assertThat(redirect, is("/welcome"));
    }

    @Test
    public void shouldRedirectToCreateUserPageIfUserIsUnRegistered() {
        userService = createUserServiceForUnRegisteredUser();
        loginController = new LoginController(userService);
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.setRemoteUser("foo");

        String redirect = loginController.redirect(mockHttpServletRequest);
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
