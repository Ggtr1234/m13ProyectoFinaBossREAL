package com.example.projectem13finaboss.controller;

import com.example.projectem13finaboss.model.Album;
import com.example.projectem13finaboss.model.AlbumRepository;
import com.example.projectem13finaboss.model.Product;
import com.example.projectem13finaboss.model.ProductRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/products")
public class ProductControllerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlbumRepository albumRepository = new AlbumRepository();
        List<Album> albums = albumRepository.getAllAlbums();
        request.setAttribute("products", albums);
        request.getRequestDispatcher("WEB-INF/views/products.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        ProductRepository productRepository = new ProductRepository();
        if (action.equals("add")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            int price = Integer.parseInt(request.getParameter("price"));
            productRepository.addProduct(id, name, price);
        } else if (action.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            productRepository.removeProduct(id);
        }
        response.sendRedirect("products");
    }

}
