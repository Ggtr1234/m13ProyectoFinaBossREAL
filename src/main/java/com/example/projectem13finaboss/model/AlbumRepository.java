package com.example.projectem13finaboss.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumRepository {

    public List<Album> getAllAlbums(){
        Connection con = Conexio.getConnection();
        Statement stmt = null;
        Statement stmt2 = null;
        List<Album> albums = new ArrayList<>();
        try {
            stmt = con.createStatement();
            stmt2 = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Album;");
            while (rs.next()) {
                int albumId = rs.getInt("AlbumId");
                String title = rs.getString("Title");
                int artistId = rs.getInt("ArtistId");
                ResultSet rs2 = stmt2.executeQuery("SELECT * FROM Artist WHERE ArtistId = " + artistId);
                rs2.next();
                String nomArtist = rs2.getString("Name");
                albums.add(new Album(albumId, new Artist(artistId,nomArtist), title));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Albums cargados: " + albums.size());
        return albums;

    }

    public List<Album> getAlbumsFavourites(){
        Connection con = Conexio.getConnection();
        Statement stmt = null;
        Statement stmt3 = null;
        Statement stmt2 = null;
        List<Album> albums = new ArrayList<>();
        try{
            stmt = con.createStatement();
            String sql = "SELECT a.AlbumId, a.Title, a.ArtistId, ar.Name " +
                    "FROM Album a " +
                    "JOIN Favourites f ON a.AlbumId = f.favourite_album " +
                    "JOIN Artist ar ON a.ArtistId = ar.ArtistId";

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int albumId = rs.getInt("AlbumId");
                String title = rs.getString("Title");
                int artistId = rs.getInt("ArtistId");
                String artistName = rs.getString("Name");
                Artist artist = new Artist(artistId, artistName);
                albums.add(new Album(albumId, artist, title));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return albums;
    }


}
