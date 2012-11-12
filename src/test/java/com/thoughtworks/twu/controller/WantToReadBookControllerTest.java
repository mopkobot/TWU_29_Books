package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.User;
import com.thoughtworks.twu.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@Controller
public class WantToReadBookControllerTest {
    @Autowired
    private UserService userService;

    private WantToReadBookController controller;

    @Before
    public void setUp() {
        controller = new WantToReadBookController(userService);
    }

    @Test
    public void shouldSuccessfullyAddBookToWantToReadList() {
        User user = new User("test.twu", "test");
        ModelAndView modelAndView = controller.markBookAsWantToRead(1, user);
        Map<String, Object> model = modelAndView.getModelMap();

        assertThat((String) model.get("notification"), is("Book was successfully added to want to read list"));
    }

}
