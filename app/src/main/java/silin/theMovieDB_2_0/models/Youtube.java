package silin.theMovieDB_2_0.models;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * Created on 12/24/15: theMovieDB_2_0 by @n1207n
 */
@AutoValue
public abstract class Youtube implements Parcelable {

    private static final String YOUTUBE_BASE_URL = "http://youtube.com/watch?v=";

    public abstract String name();

    public abstract String size();

    public abstract String source();

    public abstract String type();

    // Derived properties
    private String trailerUrl;

    static Youtube create(String name, String size, String source, String type, String trailerUrl) {
        Youtube youtube = new AutoValue_Youtube(name, size, source, type);

        youtube.trailerUrl = trailerUrl;

        return youtube;
    }

    /*
     Derived property creators
     */
    final String trailerUrl() {
        return YOUTUBE_BASE_URL + trailerUrl;
    }

    // Moshi adapter
    public static JsonAdapter<Youtube> jsonAdapter(Moshi moshi) {
        return new AutoValue_Youtube.MoshiJsonAdapter(moshi);
    }
}
