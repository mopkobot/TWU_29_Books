package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.User;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WelcomeControllerTest {
    @Test
    public void shouldSeeUserNameAfterLogin() throws IOException {
        WelcomeController welcomeController = new WelcomeController();

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteUser("test");
        ModelAndView actualModelAndView = welcomeController.welcome(request);

        User user = (User)actualModelAndView.getModel().get("user");

        assertThat(user.getName(), is("test"));
    }

}
