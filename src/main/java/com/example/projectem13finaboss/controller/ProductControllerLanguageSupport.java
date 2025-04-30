package com.example.projectem13finaboss.controller;

import com.example.projectem13finaboss.model.Product;
import com.example.projectem13finaboss.model.ProductRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet("/products-lang-support")
public class ProductControllerLanguageSupport extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String lang = request.getParameter("lang");
        if (lang == null) {
            lang = "en"; // Idioma per defecte
            System.out.println("Es null");
            session.setAttribute("lang", lang);
        }else {
            session.setAttribute("lang", lang);
        }

        Locale locale = new Locale(lang);
        ResourceBundle labels = ResourceBundle.getBundle("i18n.messages", locale);

        request.setAttribute("labels", labels);

        List<Product> products = new ProductRepository().getProducts();
        request.setAttribute("products", products);

        request.getRequestDispatcher("jsp/products-lang-support.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
