package com.example.projectem13finaboss.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArtistRepository {
    public List<Artist> getAllArtists() {
        Connection con = Conexio.getConnection();
        Statement stmt = null;
        List<Artist> artists = new ArrayList<>();
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Artist;");
            while (rs.next()) {
                int artistId = rs.getInt("ArtistId");
                String name = rs.getString("Name");
                artists.add(new Artist(artistId, name));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Artistas cargados: " + artists.size());
        return artists;

    }
}
