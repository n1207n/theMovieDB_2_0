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

    public abstract double budget();

    public abstract double revenue();

    public abstract double runtime();

    public abstract boolean adult();

    public abstract boolean video();

    public abstract List<Genre> genres();

    public abstract Collection belongs_to_collection();

    public abstract List<Company> production_companies();

    public abstract List<Country> production_countries();

    public abstract List<Language> spoken_languages();

    public static MovieDetails create(int id, int vote_count, String poster_path, String overview,
                                      String release_date, String original_title, String original_language,
                                      String title, String backdrop_path, String homepage, String imdb_id,
                                      String status, String tagline, double popularity, double vote_average,
                                      double budget, double revenue, double runtime, boolean adult,
                                      boolean video, List<Genre> genres, Collection collection,
                                      List<Company> production_companies, List<Country> production_countries,
                                      List<Language> spoken_languages) {
        return new AutoValue_MovieDetails(
                id, vote_count, poster_path, overview, release_date,
                original_title, original_language, title, backdrop_path, homepage, imdb_id,
                status, tagline, popularity, vote_average, budget, revenue,
                runtime, adult, video, genres, collection,
                production_companies, production_countries, spoken_languages
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

    public String imdbPath() {
        return IMDB_URL + imdb_id();
    }

    // Moshi adapter
    public static JsonAdapter<MovieDetails> jsonAdapter(Moshi moshi) {
        return new AutoValue_MovieDetails.MoshiJsonAdapter(moshi);
    }
}