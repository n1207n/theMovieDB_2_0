package silin.theMovieDB_2_0.models;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * Created on 7/27/16: theMovieDB_2_0 by @n1207n
 */
@AutoValue
public abstract class Collection implements Parcelable {
    private static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
    private static final String IMDB_URL = "http://www.imdb.com/title/";

    public abstract int id();

    public abstract String name();

    public abstract String poster_path();

    public abstract String backdrop_path();

    public static Collection create(int id, String name, String poster_path, String backdrop_path) {
        return new AutoValue_Collection(id, name, poster_path, backdrop_path);
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
    public static JsonAdapter<Collection> jsonAdapter(Moshi moshi) {
        return new AutoValue_Collection.MoshiJsonAdapter(moshi);
    }
}
