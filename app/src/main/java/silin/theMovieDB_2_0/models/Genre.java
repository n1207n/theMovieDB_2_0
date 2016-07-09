package silin.theMovieDB_2_0.models;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * Created on 12/25/15: theMovieDB_2_0 by @n1207n
 */
@AutoValue
public abstract class Genre implements Parcelable {
    public abstract int id();

    public abstract String name();

    static Genre create(int id, String name) {
        return new AutoValue_Genre(id, name);
    }

    // Moshi adapter
    public static JsonAdapter<Genre> jsonAdapter(Moshi moshi) {
        return new AutoValue_Genre.MoshiJsonAdapter(moshi);
    }
}