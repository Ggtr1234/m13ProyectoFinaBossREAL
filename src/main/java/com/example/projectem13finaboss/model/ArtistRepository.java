package com.example.projectem13finaboss.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistRepository {

    public List<Artist> getAllArtists() {
        List<Artist> artists = new ArrayList<>();
        String sql = "SELECT * FROM Artist";

        try (Connection con = Conexio.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int artistId = rs.getInt("ArtistId");
                String name = rs.getString("Name");
                artists.add(new Artist(artistId, name));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener artistas: " + e.getMessage());
        }

        System.out.println("Artistas cargados: " + artists.size());
        return artists;
    }

    public void deleteArtist(Artist artist) {
        String sql = "DELETE FROM Artist WHERE ArtistId = ?";

        try (Connection con = Conexio.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, artist.getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al eliminar artista: " + e.getMessage());
        }
    }
}
