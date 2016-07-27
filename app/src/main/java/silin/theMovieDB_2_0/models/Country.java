package silin.theMovieDB_2_0.models;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * Created on 7/27/16: theMovieDB_2_0 by @n1207n
 */
@AutoValue
public abstract class Country implements Parcelable {
    public abstract String iso_3166_1();

    public abstract String name();

    public static Country create(String iso_3166_1, String name) {
        return new AutoValue_Country(iso_3166_1, name);
    }

    // Moshi adapter
    public static JsonAdapter<Country> jsonAdapter(Moshi moshi) {
        return new AutoValue_Country.MoshiJsonAdapter(moshi);
    }
}
