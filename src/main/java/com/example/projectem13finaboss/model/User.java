package com.example.projectem13finaboss.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int id;
    private String username;
    private String password;
    private String token;

    public User(int id, String password, String token, String username) {
        this.id = id;
        this.password = password;
        this.token = token;
        this.username = username;
    }

    public User(int id) {
        encontrarUser(id);
    }

    private void encontrarUser(int id) {
        Connection con = Conexio.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM Users WHERE user_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                this.id = rs.getInt("user_id");
                this.username = rs.getString("username");
                this.password = rs.getString("password");
                this.token = rs.getString("user_token");
            } else {
                throw new RuntimeException("Usuario no encontrado con ID: " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener usuario: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
