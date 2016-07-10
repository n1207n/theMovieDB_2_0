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
public abstract class MovieReviewList implements Parcelable {

    public abstract int id();

    public abstract int page();

    public abstract int total_pages();

    public abstract int total_results();

    public abstract List<MovieReview> results();

    public static MovieReviewList create(int id, int page, int total_pages, int total_results, List<MovieReview> results) {
        return new AutoValue_MovieReviewList(id, page, total_pages, total_results, results);
    }

    // Moshi adapter
    public static JsonAdapter<MovieReviewList> jsonAdapter(Moshi moshi) {
        return new AutoValue_MovieReviewList.MoshiJsonAdapter(moshi);
    }
}