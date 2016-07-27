package silin.theMovieDB_2_0.models;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * Created on 7/27/16: theMovieDB_2_0 by @n1207n
 */
@AutoValue
public abstract class Language implements Parcelable {
    public abstract String iso_639_1();

    public abstract String name();

    public static Language create(String iso_639_1, String name) {
        return new AutoValue_Language(iso_639_1, name);
    }

    // Moshi adapter
    public static JsonAdapter<Language> jsonAdapter(Moshi moshi) {
        return new AutoValue_Language.MoshiJsonAdapter(moshi);
    }
}
