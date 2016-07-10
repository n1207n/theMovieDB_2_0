package silin.theMovieDB_2_0.models;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

/**
 * Created on 12/24/15: theMovieDB_2_0 by @n1207n
 */
@AutoValue
public abstract class Movie implements Parcelable {

    private static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";

    public abstract int id();

    public abstract int vote_count();

    public abstract String poster_path();

    public abstract String overview();

    public abstract String release_date();

    public abstract String original_title();

    public abstract String original_language();

    public abstract String title();

    public abstract String backdrop_path();

    public abstract double popularity();

    public abstract double vote_average();

    public abstract boolean adult();

    public abstract boolean video();

    public abstract List<Integer> genre_ids();

    // Derived properties
    public String posterPathWidth185;
    public String posterPathWidth342;
    public String posterPathWidth500;
    public String posterPathWidthOriginal;
    public String backDropPathWidth185;
    public String backDropPathWidth342;
    public String backDropPathWidth500;
    public String backDropPathWidthOriginal;

    public static Movie create(int id, int vote_count, String poster_path, String overview,
                               String release_date, String original_title, String original_language,
                               String title, String backdrop_path, double popularity, double vote_average,
                               boolean adult, boolean video, List<Integer> genre_ids,
                               String posterPathWidth185, String posterPathWidth342,
                               String posterPathWidth500, String posterPathWidthOriginal,
                               String backDropPathWidth185, String backDropPathWidth342,
                               String backDropPathWidth500,
                               String backDropPathWidthOriginal) {
        Movie movie = new AutoValue_Movie(
                id, vote_count, poster_path, overview, release_date,
                original_title, original_language, title, backdrop_path,
                popularity, vote_average, adult, video, genre_ids
        );

        // Derived properties
        movie.posterPathWidth185 = posterPathWidth185;
        movie.posterPathWidth342 = posterPathWidth342;
        movie.posterPathWidth500 = posterPathWidth500;
        movie.posterPathWidthOriginal = posterPathWidthOriginal;
        movie.backDropPathWidth185 = backDropPathWidth185;
        movie.backDropPathWidth342 = backDropPathWidth342;
        movie.backDropPathWidth500 = backDropPathWidth500;
        movie.backDropPathWidthOriginal = backDropPathWidthOriginal;

        return movie;
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

    // Moshi adapter
    public static JsonAdapter<Movie> jsonAdapter(Moshi moshi) {
        return new AutoValue_Movie.MoshiJsonAdapter(moshi);
    }
}
