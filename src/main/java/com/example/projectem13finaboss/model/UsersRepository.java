package com.example.projectem13finaboss.model;

import java.sql.*;

public class UsersRepository {

    public boolean verifyUser(String username, String password) {
        String sql = "SELECT password FROM Users WHERE username = ?";
        try (Connection con = Conexio.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String passwordDB = rs.getString("password");
                    return password.equals(passwordDB);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al verificar usuario: " + e.getMessage());
        }

        return false;
    }

    public String getToken(String username) {
        String sql = "SELECT user_token FROM Users WHERE username = ?";
        try (Connection con = Conexio.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("user_token");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener token: " + e.getMessage(), e);
        }
        return null;
    }

    public int getUserIdByToken(String token) {
        String sql = "SELECT user_id FROM Users WHERE user_token = ?";
        try (Connection con = Conexio.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, token);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("user_id");
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener ID por token: " + e.getMessage());
        }

        return -1;
    }

    public String getPreferedLanguage(int userId) {
        String sql = "SELECT lang FROM Users WHERE user_id = ?";
        try (Connection con = Conexio.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("lang");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener idioma: " + e.getMessage(), e);
        }

        return null;
    }

    public void updatePreferredLanguage(int userId, String lang) {
        String sql = "UPDATE Users SET lang = ? WHERE user_id = ?";
        try (Connection con = Conexio.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, lang);
            stmt.setInt(2, userId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar idioma: " + e.getMessage());
        }
    }

    public User getUserByToken(String token) {
        String sql = "SELECT * FROM Users WHERE user_token = ?";
        try (Connection con = Conexio.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, token);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("user_id");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String tokenDB = rs.getString("user_token");
                    return new User(id, username, tokenDB, password);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener usuario por token: " + e.getMessage(), e);
        }

        return null;
    }
}
