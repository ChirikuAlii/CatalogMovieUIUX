package id.chirikualii.catalogmovieuiux.presentasion.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Movie implements Parcelable {


    public Movie(String originalTitle, String overview, String releaseDate, String urlImagePoster, String urlImageBackdrop ,String voteAverage,String idMovie) {
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.urlImagePoster = urlImagePoster;
        this.urlImageBackdrop = urlImageBackdrop;
        this.voteAverage = voteAverage;
        this.idMovie = idMovie;
    }

    public Movie(){

    }
    private Integer isFavorite = 0;
    private String idMovie;
    private Integer id;
    private String originalTitle;
    private String overview;
    private String releaseDate;
    private String urlImagePoster;
    private String urlImageBackdrop;
    private String voteAverage;

    public Integer getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Integer favorite) {
        isFavorite = favorite;
    }

    public String  getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(String idMovie) {
        this.idMovie = idMovie;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idMovie);
        dest.writeValue(this.id);
        dest.writeString(this.originalTitle);
        dest.writeString(this.overview);
        dest.writeString(this.releaseDate);
        dest.writeString(this.urlImagePoster);
        dest.writeString(this.urlImageBackdrop);
        dest.writeString(this.voteAverage);
    }

    protected Movie(Parcel in) {
        this.idMovie = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.originalTitle = in.readString();
        this.overview = in.readString();
        this.releaseDate = in.readString();
        this.urlImagePoster = in.readString();
        this.urlImageBackdrop = in.readString();
        this.voteAverage = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
