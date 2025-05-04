package com.example.projectem13finaboss.controller;

import com.example.projectem13finaboss.model.Album;
import com.example.projectem13finaboss.model.AlbumRepository;
import com.example.projectem13finaboss.model.UsersRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet("/favouritesAlbum")
public class FavoriteControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
            return;
        }
        String lang = request.getParameter("lang");
        if (lang == null || lang.isEmpty()) {
            lang = (String) session.getAttribute("lang");
            if (lang == null || lang.isEmpty()) {
                lang = "en"; // idioma final por defecto
            }
        } else {
            session.setAttribute("lang", lang); // actualizar si el usuario lo cambió
        }
        Locale locale = new Locale(lang);
        ResourceBundle labels = ResourceBundle.getBundle("i18n.messages", locale);
        request.setAttribute("labels", labels);

        AlbumRepository albumRepository = new AlbumRepository();
        List<Album> albums = albumRepository.getAlbumsFavourites();
        request.setAttribute("albums", albums);
        request.getRequestDispatcher("jsp/favourites.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
