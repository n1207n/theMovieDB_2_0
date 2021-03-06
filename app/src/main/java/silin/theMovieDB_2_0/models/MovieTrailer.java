package silin.theMovieDB_2_0.models;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * Created on 12/24/15: theMovieDB_2_0 by @n1207n
 */
@AutoValue
public abstract class MovieTrailer implements Parcelable {

    private static final String YOUTUBE_BASE_URL = "http://youtube.com/watch?v=";

    public abstract String id();

    public abstract String iso_639_1();

    public abstract String iso_3166_1();

    public abstract String key();

    public abstract String name();

    public abstract String site();

    public abstract String size();

    public abstract String type();

    public static MovieTrailer create(String id, String iso_639_1, String iso_3166_1, String key, String name,
                                      String site, String size, String type) {
        return new AutoValue_MovieTrailer(id, iso_639_1, iso_3166_1, key, name, site, size, type);
    }

    /*
     * Derived properties
     */
    public String getTrailerUrl() {
        return YOUTUBE_BASE_URL + key();
    }

    // Moshi adapter
    public static JsonAdapter<MovieTrailer> jsonAdapter(Moshi moshi) {
        return new AutoValue_MovieTrailer.MoshiJsonAdapter(moshi);
    }
}
