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
@WebServlet("/favouritesAlbum")
public class FavoriteControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
            return;
        }

        AlbumRepository albumRepository = new AlbumRepository();
        List<Album> albums = albumRepository.getAlbumsFavourites();
        request.setAttribute("albums", albums);
        request.getRequestDispatcher("jsp/favourites.jsp").forward(request, response);

    }
}
