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
public abstract class MovieList implements Parcelable {
    public abstract int page();

    public abstract int total_results();

    public abstract int total_pages();

    public abstract List<Movie> results();

    static MovieList create(int page, int total_results, int total_pages, List<Movie> results) {
        return new AutoValue_MovieList(page, total_results, total_pages, results);
    }

    // Moshi adapter
    public static JsonAdapter<MovieList> jsonAdapter(Moshi moshi) {
        return new AutoValue_MovieList.MoshiJsonAdapter(moshi);
    }
}