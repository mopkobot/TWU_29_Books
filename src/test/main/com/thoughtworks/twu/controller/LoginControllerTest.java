package main.com.thoughtworks.twu.controller;

import com.thoughtworks.twu.controller.LoginController;
import com.thoughtworks.twu.domain.User;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LoginControllerTest {
    @Test
    public void shouldSeeUserNameAfterLogin() {
        LoginController loginController = new LoginController();

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteUser("test");
        ModelAndView actualModelAndView = loginController.welcome(request);

        User user = (User)actualModelAndView.getModel().get("user");

        assertThat(user.getName(), is("test"));
    }

}
