package com.example.projectem13finaboss.controller;

import com.example.projectem13finaboss.model.UsersRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsersRepository usersRepository = new UsersRepository();
        if (usersRepository.verifyUser(username, password)) {
            Cookie token = new Cookie( "token", usersRepository.getToken(username));
            token.setMaxAge(60 * 60 * 24);
            token.setPath("/");
            response.addCookie(token);
            HttpSession session = request.getSession();
            session.setAttribute("user", username);
            response.sendRedirect("loggedUser"); // Redirige a la URL '/loggedUser'
        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body><h2>Credenciales incorrectas. Intenta nuevamente.</h2></body></html>");
        }
    }
}
