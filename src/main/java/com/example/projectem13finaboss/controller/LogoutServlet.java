package com.example.projectem13finaboss.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Invalida la sesión actual
        HttpSession session = request.getSession(false);
        if (session != null) {
            // Elimina los atributos de la sesión
            session.removeAttribute("user");
            session.removeAttribute("userId");
            session.removeAttribute("lang");

            // Invalida la sesión (termina la sesión)
            session.invalidate();
        }

        // Elimina las cookies
        Cookie langCookie = new Cookie("lang", null);
        langCookie.setMaxAge(0); // Elimina la cookie
        langCookie.setPath("/"); // Asegúrate de usar el mismo path que el que usaste para crearla
        response.addCookie(langCookie);

        Cookie tokenCookie = new Cookie("token", null);
        tokenCookie.setMaxAge(0); // Elimina la cookie
        tokenCookie.setPath("/"); // Asegúrate de usar el mismo path que el que usaste para crearla
        response.addCookie(tokenCookie);

        // Redirige al login
        response.sendRedirect("login.html");
    }
}

