package com.thoughtworks.twu.controller;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;

public class LoginControllerTest {
    @Test
    public void shouldSeeUserNameAfterLogin() {
        LoginController loginController = new LoginController();

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteUser("test");
        ModelAndView actualModelAndView = loginController.welcome(request);

        assertEquals(actualModelAndView.getModelMap().get("username"), "test");
    }
}
