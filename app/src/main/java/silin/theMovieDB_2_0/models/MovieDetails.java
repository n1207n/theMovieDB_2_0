package silin.theMovieDB_2_0.models;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

/**
 * Created on 12/25/15: theMovieDB_2_0 by @n1207n
 */
@AutoValue
public abstract class MovieDetails implements Parcelable {

    private static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
    private static final String IMDB_URL = "http://www.imdb.com/title/";

    public abstract int id();

    public abstract int vote_count();

    public abstract String poster_path();

    public abstract String overview();

    public abstract String release_date();

    public abstract String original_title();

    public abstract String original_language();

    public abstract String title();

    public abstract String backdrop_path();

    public abstract String homepage();

    public abstract String imdb_id();

    public abstract String status();

    public abstract String tagline();

    public abstract double popularity();

    public abstract double vote_average();

    public abstract boolean adult();

    public abstract boolean video();

    public abstract boolean budget();

    public abstract boolean revenue();

    public abstract boolean runtime();

    public abstract List<Genre> genres();

    // Derived properties
    private String posterPathWidth185;
    private String posterPathWidth342;
    private String posterPathWidth500;
    private String posterPathWidthOriginal;
    private String backDropPathWidth185;
    private String backDropPathWidth342;
    private String backDropPathWidth500;
    private String backDropPathWidthOriginal;
    private String imdbPath;

    static MovieDetails create(int id, int vote_count, String poster_path, String overview,
                               String release_date, String original_title, String original_language,
                               String title, String backdrop_path, String homepage, String imdb_id,
                               String status, String tagline, double popularity, double vote_average,
                               boolean adult, boolean video, boolean budget, boolean revenue,
                               boolean runtime, List<Genre> genres,
                               String posterPathWidth185, String posterPathWidth342,
                               String posterPathWidth500, String posterPathWidthOriginal,
                               String backDropPathWidth185, String backDropPathWidth342,
                               String backDropPathWidth500, String backDropPathWidthOriginal,
                               String imdbPath) {
        MovieDetails movieDetails = new AutoValue_MovieDetails(
                id, vote_count, poster_path, overview, release_date,
                original_title, original_language, title, backdrop_path, homepage, imdb_id,
                status, tagline, popularity, vote_average, adult, video, budget, revenue,
                runtime, genres
        );

        // Derived properties
        movieDetails.posterPathWidth185 = posterPathWidth185;
        movieDetails.posterPathWidth342 = posterPathWidth342;
        movieDetails.posterPathWidth500 = posterPathWidth500;
        movieDetails.posterPathWidthOriginal = posterPathWidthOriginal;
        movieDetails.backDropPathWidth185 = backDropPathWidth185;
        movieDetails.backDropPathWidth342 = backDropPathWidth342;
        movieDetails.backDropPathWidth500 = backDropPathWidth500;
        movieDetails.backDropPathWidthOriginal = backDropPathWidthOriginal;
        movieDetails.imdbPath = imdbPath;

        return movieDetails;
    }

    /*
     Derived property creators
     */
    final String posterPathWidth185() {
        return IMAGE_BASE_URL + "w185" + posterPathWidth185;
    }

    final String posterPathWidth342() {
        return IMAGE_BASE_URL + "w342" + posterPathWidth342;
    }

    final String posterPathWidth500() {
        return IMAGE_BASE_URL + "w500" + posterPathWidth500;
    }

    final String posterPathWidthOriginal() {
        return IMAGE_BASE_URL + "original" + posterPathWidthOriginal;
    }

    final String backDropPathWidth185() {
        return IMAGE_BASE_URL + "w185" + backDropPathWidth185;
    }

    final String backDropPathWidth342() {
        return IMAGE_BASE_URL + "w342" + backDropPathWidth342;
    }

    final String backDropPathWidth500() {
        return IMAGE_BASE_URL + "w500" + backDropPathWidth500;
    }

    final String backDropPathWidthOriginal() {
        return IMAGE_BASE_URL + "original" + backDropPathWidthOriginal;
    }

    final String imdbPath() {
        return IMDB_URL + imdbPath;
    }

    // Moshi adapter
    public static JsonAdapter<MovieDetails> jsonAdapter(Moshi moshi) {
        return new AutoValue_MovieDetails.MoshiJsonAdapter(moshi);
    }
}