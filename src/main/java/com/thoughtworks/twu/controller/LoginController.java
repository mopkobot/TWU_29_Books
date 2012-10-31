package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @RequestMapping(value = "/create-user-profile", method = RequestMethod.GET)
    public ModelAndView welcome(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("create-user-profile");
        String username = request.getRemoteUser();
        modelAndView.addObject("user", new User(username));
        return modelAndView;
    }
}
