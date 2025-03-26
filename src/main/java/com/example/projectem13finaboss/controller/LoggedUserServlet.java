package com.example.projectem13finaboss.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;

@WebServlet("/loggedUser")
public class LoggedUserServlet extends HttpServlet {
    public static final Logger log = LoggerFactory.getLogger(LoggedUserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Cookie[] cookies = request.getCookies();
        log.info(cookies.toString());
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        response.sendRedirect("jsp/loggedUser.jsp");
    }


}
