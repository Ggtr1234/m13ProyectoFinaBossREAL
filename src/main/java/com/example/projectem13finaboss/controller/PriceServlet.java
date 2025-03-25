package com.example.projectem13finaboss.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

@WebServlet("/price")
public class PriceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        // Establim un valor per al preu
        double price = 1234.5678;
        // Afegim el valor del preu i la data actual a la petició
        request.setAttribute("price", price);
        request.setAttribute("currentDate", new Date());
        // Redirigim la petició a la vista JSP
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/jsp/price.jsp");
        dispatcher.forward(request, response);
    }
}
