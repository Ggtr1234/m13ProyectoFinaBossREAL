package com.example.projectem13finaboss.controller;

import com.example.projectem13finaboss.model.Artist;
import com.example.projectem13finaboss.model.ArtistRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet("/artistlist")
public class ArtistListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
            return;
        }

        // Obtener idioma desde parámetro o sesión
        String lang = request.getParameter("lang");
        if (lang == null || lang.isEmpty()) {
            lang = (String) session.getAttribute("lang");
            if (lang == null || lang.isEmpty()) {
                lang = "en"; // valor por defecto
            }
        } else {
            session.setAttribute("lang", lang); // actualiza idioma si se recibe
        }

        Locale locale = new Locale(lang);
        ResourceBundle labels = ResourceBundle.getBundle("i18n.messages", locale);
        request.setAttribute("labels", labels);

        ArtistRepository artistRepository = new ArtistRepository();
        List<Artist> artists = artistRepository.getAllArtists();
        request.setAttribute("artists", artists);
        request.getRequestDispatcher("jsp/artists.jsp").forward(request, response);
    }
}
