package com.sap.intern.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class LogOutController {

    @Autowired
    LoginController controller;

    @RequestMapping(value = "/logout")
    public HttpSession logOut(HttpServletRequest request) throws ServletException {

        final HttpSession session = request.getSession(false);

        request.logout();
        session.invalidate();
        return session;
    }
}
