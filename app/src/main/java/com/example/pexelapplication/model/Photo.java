package com.example.pexelapplication.model;

import com.squareup.moshi.Json;

public class Photo {

    private long id;
    private int width;
    private int height;
    private String url;
    private String photographer;
    @Json(name = "photographer_url")
    private String photographerUrl;
    @Json(name = "photographer_id")
    private String photographerId;
    @Json(name = "avg_color")
    private String avgColor;
    private Src src;
    private boolean liked;

    public Photo(long id, int width, int height, String url, String photographer,
                 String photographer_url, String photographer_id, String avg_color, Src src, boolean liked) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.url = url;
        this.photographer = photographer;
        this.photographerUrl = photographer_url;
        this.photographerId = photographer_id;
        this.avgColor = avg_color;
        this.src = src;
        this.liked = liked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhotographer() {
        return photographer;
    }

    public void setPhotographer(String photographer) {
        this.photographer = photographer;
    }

    public String getPhotographerUrl() {
        return photographerUrl;
    }

    public void setPhotographerUrl(String photographerUrl) {
        this.photographerUrl = photographerUrl;
    }

    public String getPhotographerId() {
        return photographerId;
    }

    public void setPhotographerId(String photographerId) {
        this.photographerId = photographerId;
    }

    public String getAvgColor() {
        return avgColor;
    }

    public void setAvgColor(String avgColor) {
        this.avgColor = avgColor;
    }

    public Src getSrc() {
        return src;
    }

    public void setSrc(Src src) {
        this.src = src;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", width=" + width +
                ", height=" + height +
                ", url='" + url + '\'' +
                ", photographer='" + photographer + '\'' +
                ", photographerUrl='" + photographerUrl + '\'' +
                ", photographerId='" + photographerId + '\'' +
                ", avgColor='" + avgColor + '\'' +
                ", src=" + src.toString() +
                ", liked=" + liked +
                '}';
    }
}
