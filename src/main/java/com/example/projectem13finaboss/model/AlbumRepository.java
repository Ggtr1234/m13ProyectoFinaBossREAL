package com.example.projectem13finaboss.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumRepository {

    public List<Album> getAllAlbums() {
        List<Album> albums = new ArrayList<>();
        String sql = "SELECT a.AlbumId, a.Title, ar.ArtistId, ar.Name " +
                "FROM Album a JOIN Artist ar ON a.ArtistId = ar.ArtistId";

        try (Connection con = Conexio.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int albumId = rs.getInt("AlbumId");
                String title = rs.getString("Title");
                int artistId = rs.getInt("ArtistId");
                String artistName = rs.getString("Name");

                Artist artist = new Artist(artistId, artistName);
                albums.add(new Album(albumId, artist, title));
            }

        } catch (SQLException e) {
            System.err.println("Error al cargar álbumes: " + e.getMessage());
        }

        System.out.println("Álbumes cargados: " + albums.size());
        return albums;
    }

    public List<Album> getAlbumsFavourites() {
        List<Album> albums = new ArrayList<>();
        String sql = "SELECT a.AlbumId, a.Title, ar.ArtistId, ar.Name " +
                "FROM Album a " +
                "JOIN Favourites f ON a.AlbumId = f.favourite_album " +
                "JOIN Artist ar ON a.ArtistId = ar.ArtistId";

        try (Connection con = Conexio.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int albumId = rs.getInt("AlbumId");
                String title = rs.getString("Title");
                int artistId = rs.getInt("ArtistId");
                String artistName = rs.getString("Name");

                Artist artist = new Artist(artistId, artistName);
                albums.add(new Album(albumId, artist, title));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener favoritos: " + e.getMessage());
        }

        return albums;
    }

    public void addFavoriteAlbum(Album album, int userId) {
        String sql = "INSERT INTO Favourites (user_id, favourite_album) VALUES (?, ?)";

        try (Connection con = Conexio.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setInt(2, album.getAlbumId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al añadir álbum a favoritos: " + e.getMessage(), e);
        }
    }

    public void deleteFavoriteAlbum(Album album, User user) {
        String sql = "DELETE FROM Favourites WHERE user_id = ? AND favourite_album = ?";

        try (Connection con = Conexio.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, user.getId());
            pstmt.setInt(2, album.getAlbumId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al eliminar favorito: " + e.getMessage());
        }
    }

    public void deleteAlbum(Album album) {
        String sql = "DELETE FROM Album WHERE AlbumId = ?";

        try (Connection con = Conexio.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, album.getAlbumId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al eliminar álbum: " + e.getMessage());
        }
    }

}
