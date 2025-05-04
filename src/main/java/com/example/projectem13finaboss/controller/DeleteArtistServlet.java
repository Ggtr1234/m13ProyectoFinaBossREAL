package com.example.projectem13finaboss.controller;
import com.example.projectem13finaboss.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/deleteartist")
public class DeleteArtistServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
            return;
        }

        int artistId = Integer.parseInt(request.getParameter("ArtistId"));

        ArtistRepository artistRepo = new ArtistRepository();
        artistRepo.deleteArtist(new Artist(artistId,""));
        response.sendRedirect("artistlist");

    }
}