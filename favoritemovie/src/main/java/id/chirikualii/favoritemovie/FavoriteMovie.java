package id.chirikualii.favoritemovie;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

public class FavoriteMovie implements Parcelable {

    private String idMovie;
    private Integer id;
    private String originalTitle;
    private String overview;
    private String releaseDate;
    private String urlImagePoster;
    private String urlImageBackdrop;
    private String voteAverage;

    public FavoriteMovie (Cursor cursor){
        this.id = DbContract.getColumnInt(cursor,DbContract.FavoriteColumns._ID);
        this.idMovie = DbContract.getColumnString(cursor,DbContract.FavoriteColumns.FAVORITE_COLUMN_ID_MOVIE);
        this.originalTitle = DbContract.getColumnString(cursor,DbContract.FavoriteColumns.FAVORITE_COLUMN_ORIGINAL_TITLE);
        this.overview = DbContract.getColumnString(cursor,DbContract.FavoriteColumns.FAVORITE_COLUMN_OVERVIEW);
        this.releaseDate = DbContract.getColumnString(cursor,DbContract.FavoriteColumns.FAVORITE_COLUMN_RELEASE_DATE);
        this.urlImagePoster = DbContract.getColumnString(cursor,DbContract.FavoriteColumns.FAVORITE_COLUMN_POSTER_PATH);
        this.urlImageBackdrop = DbContract.getColumnString(cursor,DbContract.FavoriteColumns.FAVORITE_COLUMN_BACKDROP_PATH);
        this.voteAverage = DbContract.getColumnString(cursor,DbContract.FavoriteColumns.FAVORITE_COLUMN_VOTE_AVERAGE);
    }

    public String getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(String idMovie) {
        this.idMovie = idMovie;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
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

    public FavoriteMovie() {
    }

    protected FavoriteMovie(Parcel in) {
        this.idMovie = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.originalTitle = in.readString();
        this.overview = in.readString();
        this.releaseDate = in.readString();
        this.urlImagePoster = in.readString();
        this.urlImageBackdrop = in.readString();
        this.voteAverage = in.readString();
    }

    public static final Parcelable.Creator<FavoriteMovie> CREATOR = new Parcelable.Creator<FavoriteMovie>() {
        @Override
        public FavoriteMovie createFromParcel(Parcel source) {
            return new FavoriteMovie(source);
        }

        @Override
        public FavoriteMovie[] newArray(int size) {
            return new FavoriteMovie[size];
        }
    };
}
