package com.example.projectem13finaboss.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.projectem13finaboss.controller.LoggedUserServlet.log;

public class UsersRepository {
    public boolean verifyUser(String username, String password) {
        Connection con = Conexio.getConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Users WHERE username = '" + username + "'");
            while (rs.next()) {
                String usernameDB = rs.getString("username");
                String passwordDB = rs.getString("password");
                if (username.equals(usernameDB) && password.equals(passwordDB)) {
                    return true;
                }else {
                    return false;
                }
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully");
        return false;
    }

    public String getToken(String username) {
        Connection con = Conexio.getConnection();
        Statement stmt = null;
        try{
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Users WHERE username = '" + username + "'");
            while (rs.next()) {
                int id = rs.getInt("user_id");
                String usernameDB = rs.getString("username");
                String passwordDB = rs.getString("password");
                String tokenDB = rs.getString("user_token");
                User usuario = new User(id, usernameDB, tokenDB,passwordDB);
                return usuario.getToken();
            }


            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public int getUserIdByToken(String token) {
        Connection con = Conexio.getConnection();
        String query = "SELECT user_id FROM Users WHERE user_token = ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, token);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("user_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
