package com.example.projectem13finaboss.controller;

import com.example.projectem13finaboss.model.Album;
import com.example.projectem13finaboss.model.AlbumRepository;
import com.example.projectem13finaboss.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/deletealbum")
public class DeleteAlbumServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
            return;
        }

        int albumId = Integer.parseInt(request.getParameter("albumId"));

        AlbumRepository albumRepo = new AlbumRepository();
        albumRepo.deleteAlbum(new Album(albumId, null, ""));
        response.sendRedirect("albumlists");

    }
}
