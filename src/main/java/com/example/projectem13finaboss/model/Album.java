package com.example.projectem13finaboss.model;

public class Album {
    private int albumId;
    private String title;
    private Artist artist;

    public Album() {
    }

    public Album(int albumId, Artist id, String title) {
        this.albumId = albumId;
        this.artist = id;
        this.title = title;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Album{" +
                "albumId=" + albumId +
                ", title='" + title + '\'' +
                ", artist=" + artist +
                "}\n";
    }
}
