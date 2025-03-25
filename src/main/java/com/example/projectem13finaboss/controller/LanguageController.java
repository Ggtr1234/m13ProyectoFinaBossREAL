package com.example.projectem13finaboss.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/change-language")
public class LanguageController {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtenir el paràmetre de l'idioma
        String language = request.getParameter("lang");

        if (language != null && !language.isEmpty()) {
            // Guardar l'idioma en la sessió
            HttpSession session = request.getSession();
            session.setAttribute("selectedLanguage", language);
        }

        // Redirigir a la pàgina de productes amb suport multiidioma
        response.sendRedirect("productslang-support.jsp");
    }
}
