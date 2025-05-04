package com.example.projectem13finaboss.controller;

import com.example.projectem13finaboss.model.UsersRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet("/loggedUser")
public class LoggedUserServlet extends HttpServlet {
    public static final Logger log = LoggerFactory.getLogger(LoggedUserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }

        // Obtener el idioma desde parámetros o cookies
        String lang = request.getParameter("lang");

        // Si no se pasa un parámetro, se busca en las cookies
        if (lang == null || lang.isEmpty()) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if ("lang".equals(c.getName())) {
                        lang = c.getValue();
                        break;
                    }
                }
            }
        }

        // NUEVO: actualizar en BD si hay userId y lang no es null
        if (lang != null && session.getAttribute("userId") != null) {
            int userId = (Integer) session.getAttribute("userId");
            UsersRepository usersRepository = new UsersRepository();
            usersRepository.updatePreferredLanguage(userId, lang);  // Se actualiza el idioma en la BD
        }

        // agregar tambien ensesiom
        session.setAttribute("lang", lang);

        // Establecer la localización para la interfaz
        Locale locale = new Locale(lang);
        ResourceBundle labels = ResourceBundle.getBundle("i18n.home", locale);
        request.setAttribute("labels", labels);

        // Mantener el idioma como atributo para los enlaces/formularios
        request.setAttribute("lang", lang);

        // Redirigir a la vista de loggedUser
        request.getRequestDispatcher("jsp/loggedUser.jsp").forward(request, response);
    }


}
