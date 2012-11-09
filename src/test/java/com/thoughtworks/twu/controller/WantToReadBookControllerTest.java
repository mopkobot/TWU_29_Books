package com.thoughtworks.twu.controller;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WantToReadBookControllerTest {

    @Test
    public void shouldSuccessfullyAddBookToWantToReadList() {
        WantToReadBookController controller = new WantToReadBookController();
        ModelAndView modelAndView = controller.markBookAsWantToRead(1);
        Map<String, Object> model = modelAndView.getModel();
        assertThat((String) model.get("notification"), is("Book was successfully added to want to read list"));
    }

}
