package com.example.projectem13finaboss.controller;

import com.example.projectem13finaboss.model.Album;
import com.example.projectem13finaboss.model.AlbumRepository;
import com.example.projectem13finaboss.model.Artist;
import com.example.projectem13finaboss.model.ArtistRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
@WebServlet("/artistlist")
public class ArtistListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
            return;
        }

        ArtistRepository artistRepository = new ArtistRepository();
        List<Artist> artists = artistRepository.getAllArtists();
        request.setAttribute("artists", artists);
        request.getRequestDispatcher("jsp/artists.jsp").forward(request, response);
    }
}
