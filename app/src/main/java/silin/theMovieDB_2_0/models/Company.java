package silin.theMovieDB_2_0.models;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * Created on 7/27/16: theMovieDB_2_0 by @n1207n
 */
@AutoValue
public abstract class Company implements Parcelable {
    public abstract int id();

    public abstract String name();

    public static Company create(int id, String name) {
        return new AutoValue_Company(id, name);
    }

    // Moshi adapter
    public static JsonAdapter<Company> jsonAdapter(Moshi moshi) {
        return new AutoValue_Company.MoshiJsonAdapter(moshi);
    }
}
