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
public abstract class MovieTrailerList implements Parcelable {

    public abstract int id();

    public abstract List<MovieTrailer> results();

    static MovieTrailerList create(int id, List<MovieTrailer> results) {
        return new AutoValue_MovieTrailerList(id, results);
    }

    // Moshi adapter
    public static JsonAdapter<MovieTrailerList> jsonAdapter(Moshi moshi) {
        return new AutoValue_MovieTrailerList.MoshiJsonAdapter(moshi);
    }
}