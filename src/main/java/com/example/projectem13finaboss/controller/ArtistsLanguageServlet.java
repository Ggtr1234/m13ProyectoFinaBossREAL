package com.example.projectem13finaboss.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/artists-lang-support")
public class ArtistsLanguageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lang = request.getParameter("lang");

        HttpSession session = request.getSession(false);
        if (session != null && lang != null && !lang.isEmpty()) {
            session.setAttribute("lang", lang);
        }

        if (lang == null || lang.isEmpty()) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if ("lang".equals(c.getName())) {
                        c.setValue(lang);
                        break;
                    }
                }
            }
        }

        // Redirigir a la lista de artistas
        response.sendRedirect("artistlist");
    }
}
