package id.chirikualii.catalogmovieuiux.presentasion.model;

import android.os.Parcelable;

import java.io.Serializable;

public class Movie implements Serializable {


    public Movie(String originalTitle, String overview, String releaseDate, String urlImagePoster, String urlImageBackdrop ,Double voteAverage) {
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.urlImagePoster = urlImagePoster;
        this.urlImageBackdrop = urlImageBackdrop;
        this.voteAverage = voteAverage;
    }

    private String originalTitle;
    private String overview;
    private String releaseDate;
    private String urlImagePoster;
    private String urlImageBackdrop;
    private Double voteAverage;

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }



    public String getUrlImagePoster() {
        return urlImagePoster;
    }

    public void setUrlImagePoster(String urlImagePoster) {
        this.urlImagePoster = urlImagePoster;
    }

    public String getUrlImageBackdrop() {
        return urlImageBackdrop;
    }

    public void setUrlImageBackdrop(String urlImageBackdrop) {
        this.urlImageBackdrop = urlImageBackdrop;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
