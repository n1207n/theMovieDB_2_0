package silin.theMovieDB_2_0.api;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import silin.theMovieDB_2_0.BuildConfig;
import silin.theMovieDB_2_0.models.MovieDetails;
import silin.theMovieDB_2_0.models.MovieList;
import silin.theMovieDB_2_0.models.MovieReviewList;
import silin.theMovieDB_2_0.models.MovieTrailerList;

/**
 * Created on 7/8/16: theMovieDB_2_0 by @n1207n
 */

public interface NetworkService {
    /*
     * GET parameters:
     *
     * sort_by -- String
     * popularity.asc
     * popularity.desc
     * release_date.asc
     * release_date.desc
     * revenue.asc
     * revenue.desc
     * primary_release_date.asc
     * primary_release_date.desc
     * original_title.asc
     * original_title.desc
     * vote_average.asc
     * vote_average.desc
     * vote_count.asc
     * vote_count.desc
     *
     * api_key -- String
     */
    @GET("discover/movie?api_key=" + BuildConfig.API_BASE_KEY)
    Observable<MovieList> getPopularMovieList(@Query("primary_release_date.gte") String primary_release_date_start, @Query("primary_release_date.lte") String primary_release_date_end, @Query("sort_by") String sorting);

    /*
     * GET parameters:
     *
     * api_key -- String
     */
    @GET("movie/{id}?api_key=" + BuildConfig.API_BASE_KEY)
    Observable<MovieDetails> getMovieDetails(@Path("id") String movie_id);

    @GET("movie/{id}/videos?api_key=" + BuildConfig.API_BASE_KEY)
    Observable<MovieTrailerList> getMovieTrailerList(@Path("id") String movie_id);

    @GET("movie/{id}/reviews?api_key=" + BuildConfig.API_BASE_KEY)
    Observable<MovieReviewList> getMovieReviewList(@Path("id") String movie_id, @Query("page") int page);
}
