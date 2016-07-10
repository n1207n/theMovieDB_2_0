package silin.theMovieDB_2_0.models;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * Created on 12/24/15: theMovieDB_2_0 by @n1207n
 */
@AutoValue
public abstract class MovieReview implements Parcelable {

    public abstract String id();

    public abstract String author();

    public abstract String content();

    public abstract String url();

    public static MovieReview create(String id, String author, String content, String url) {
        return new AutoValue_MovieReview(id, author, content, url);
    }

    // Moshi adapter
    public static JsonAdapter<MovieReview> jsonAdapter(Moshi moshi) {
        return new AutoValue_MovieReview.MoshiJsonAdapter(moshi);
    }
}
