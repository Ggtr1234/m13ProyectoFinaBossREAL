package com.example.projectem13finaboss.controller;


import com.example.projectem13finaboss.model.AlbumRepository;
import com.example.projectem13finaboss.model.User;
import com.example.projectem13finaboss.model.Album;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteFavoriteServlet")
public class DeleteFavoriteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int albumId = Integer.parseInt(request.getParameter("albumId"));
        int userId = Integer.parseInt(request.getParameter("userId"));

        AlbumRepository albumRepo = new AlbumRepository();
        albumRepo.deleteFavoriteAlbum(new Album(albumId, null, ""), new User(userId));

        response.setStatus(HttpServletResponse.SC_OK); // Responde con estado 200 (OK)
    }
}

