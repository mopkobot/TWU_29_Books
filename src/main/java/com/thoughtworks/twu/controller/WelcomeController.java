package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class WelcomeController {

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ModelAndView welcome(HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = new ModelAndView("welcome");
        String username = request.getRemoteUser();
        modelAndView.addObject("user", new User("casname", username));
        return modelAndView;
    }
}
