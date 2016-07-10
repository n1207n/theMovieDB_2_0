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

    public static Movie create(int id, int vote_count, String poster_path, String overview,
                               String release_date, String original_title, String original_language,
                               String title, String backdrop_path, double popularity, double vote_average,
                               boolean adult, boolean video, List<Integer> genre_ids) {

        return new AutoValue_Movie(
                id, vote_count, poster_path, overview, release_date,
                original_title, original_language, title, backdrop_path,
                popularity, vote_average, adult, video, genre_ids
        );
    }

    /*
     * Derived properties
     */
    public String posterPathWidth185() {
        return IMAGE_BASE_URL + "w185" + poster_path();
    }

    public String posterPathWidth342() {
        return IMAGE_BASE_URL + "w342" + poster_path();
    }

    public String posterPathWidth500() {
        return IMAGE_BASE_URL + "w500" + poster_path();
    }

    public String posterPathWidthOriginal() {
        return IMAGE_BASE_URL + "original" + poster_path();
    }

    public String backDropPathWidth185() {
        return IMAGE_BASE_URL + "w185" + backdrop_path();
    }

    public String backDropPathWidth342() {
        return IMAGE_BASE_URL + "w342" + backdrop_path();
    }

    public String backDropPathWidth500() {
        return IMAGE_BASE_URL + "w500" + backdrop_path();
    }

    public String backDropPathWidthOriginal() {
        return IMAGE_BASE_URL + "original" + backdrop_path();
    }

    // Moshi adapter
    public static JsonAdapter<Movie> jsonAdapter(Moshi moshi) {
        return new AutoValue_Movie.MoshiJsonAdapter(moshi);
    }
}
