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

public class LoginControllerTest {
    private UserService userService;
    private LoginController loginController;
    private User user = new User("foo", "foobar");

    @Test
    public void shouldRedirectToWelcomePageIfUserIsRegistered() {
        userService = createUserServiceForRegisteredUser();
        loginController = new LoginController(userService);

        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
        httpServletRequest.setRemoteUser("foo");
        ModelAndView redirect = loginController.redirect(httpServletRequest);

        User actualUser = (User) redirect.getModel().get("user");
        assertThat(actualUser.getName(), is(user.getName()));
    }

    @Test
    public void shouldRedirectToCreateUserPageIfUserIsUnRegistered() {
        userService = createUserServiceForUnRegisteredUser();
        loginController = new LoginController(userService);
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.setRemoteUser("foo");
        ModelAndView redirect = loginController.redirect(mockHttpServletRequest);

        assertThat(redirect.getViewName(), is("create-user-profile"));
    }

    @Test
    public void shouldSaveUnregisteredUser() {
        userService = createUserServiceForUnRegisteredUser();
        loginController = new LoginController(userService);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("username", "foo");
        ModelAndView ret = loginController.saveUser(request);

        User expectUser = (User)ret.getModelMap().get("user");
        assertThat(expectUser.getName(), is("foo"));
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
