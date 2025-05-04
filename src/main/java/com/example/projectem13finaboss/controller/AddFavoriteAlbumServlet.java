package com.example.projectem13finaboss.controller;

import com.example.projectem13finaboss.model.Album;
import com.example.projectem13finaboss.model.AlbumRepository;
import com.example.projectem13finaboss.model.User;
import com.example.projectem13finaboss.model.UsersRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/addFavoriteAlbum")
public class AddFavoriteAlbumServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String albumIdStr = request.getParameter("albumId");
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }

        try {
            int albumId = Integer.parseInt(albumIdStr);
            AlbumRepository albumRepository = new AlbumRepository();

            List<Album> allAlbums = albumRepository.getAllAlbums();
            Album selectedAlbum = null;
            for (Album album : allAlbums) {
                if (album.getAlbumId() == albumId) {
                    selectedAlbum = album;
                    break;
                }
            }
            UsersRepository usersRepository = new UsersRepository();
                Cookie[] cookies = request.getCookies();
                String userToken = null;
                if (cookies != null) {
                    for (Cookie c : cookies) {
                        if ("token".equals(c.getName())) {
                            userToken = c.getValue();
                            break;
                        }
                    }
                }

            if (selectedAlbum != null) {
                albumRepository.addFavoriteAlbum(selectedAlbum, usersRepository.getUserIdByToken(userToken) );
                System.out.println("Album " + albumId + " añadido a favoritos del usuario " + userToken);
            } else {
                System.err.println("Album no encontrado: ID = " + albumId);
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Álbum no encontrado");
                return;
            }

            response.sendRedirect("albumlists");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de álbum no válido");
        }
    }
}
