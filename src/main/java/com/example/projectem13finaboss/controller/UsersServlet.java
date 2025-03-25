package com.example.projectem13finaboss.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        // Creem una llista d'usuaris
        List<String> users = Arrays.asList("Alice", "Bob",
                "Charlie", "Diana");
        // Pasem la llista d'usuaris com a attribute de la sol·licitud
        request.setAttribute("userList", users);
        // Redirigim a la pàgina JSP per mostrar la llista
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/jsp/userList.jsp");
        dispatcher.forward(request, response);
    }
}

