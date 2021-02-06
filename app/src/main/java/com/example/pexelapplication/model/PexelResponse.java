package com.example.pexelapplication.model;

import com.squareup.moshi.Json;

import java.util.List;

public class PexelResponse {
    private int page;
    @Json(name = "per_page")
    private int perPage;
    private List<Photo> photos;
    @Json(name = "total_results")
    private long totalResults;
    @Json(name = "next_page")
    private String nextPage;

    public PexelResponse(int page, int perPage, List<Photo> photo, long totalResults, String nextPage) {
        this.page = page;
        this.perPage = perPage;
        this.photos = photo;
        this.totalResults = totalResults;
        this.nextPage = nextPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(long totalResults) {
        this.totalResults = totalResults;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    @Override
    public String toString() {
        return "PexelResponse{" +
                "page=" + page +
                ", perPage=" + perPage +
                ", photo=" + photos.toString() +
                ", totalResults=" + totalResults +
                ", nextPage='" + nextPage + '\'' +
                '}';
    }
}
