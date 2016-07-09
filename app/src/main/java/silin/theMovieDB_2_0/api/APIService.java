package silin.theMovieDB_2_0.api;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import silin.theMovieDB_2_0.models.MovieDetails;
import silin.theMovieDB_2_0.models.MovieList;
import silin.theMovieDB_2_0.models.MovieReviewList;
import silin.theMovieDB_2_0.models.MovieTrailerList;

/**
 * Created on 7/8/16: theMovieDB_2_0 by @n1207n
 */

public class APIService {
    private final NetworkService networkService;

    public APIService(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Subscription getPopularMovieList(final GetPopularMovieListCallback callback) {
        return networkService.getPopularMovieList("popularity.desc")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends MovieList>>() {
                    @Override
                    public Observable<? extends MovieList> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<MovieList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkException(e));
                    }

                    @Override
                    public void onNext(MovieList movieList) {
                        callback.onSuccess(movieList);
                    }
                });
    }

    public Subscription getMovieDetails(final String movie_id, final GetMovieDetailsCallback callback) {
        return networkService.getMovieDetails(movie_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends MovieDetails>>() {
                    @Override
                    public Observable<? extends MovieDetails> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<MovieDetails>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkException(e));
                    }

                    @Override
                    public void onNext(MovieDetails movieDetails) {
                        callback.onSuccess(movieDetails);
                    }
                });
    }

    public Subscription getMovieTrailers(final String movie_id, final GetMovieTrailerListCallback callback) {
        return networkService.getMovieTrailerList(movie_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends MovieTrailerList>>() {
                    @Override
                    public Observable<? extends MovieTrailerList> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<MovieTrailerList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkException(e));
                    }

                    @Override
                    public void onNext(MovieTrailerList movieTrailerList) {
                        callback.onSuccess(movieTrailerList);
                    }
                });
    }

    public Subscription getMovieReviews(final String movie_id, final int page, final GetMovieReviewListCallback callback) {
        return networkService.getMovieReviewList(movie_id, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends MovieReviewList>>() {
                    @Override
                    public Observable<? extends MovieReviewList> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<MovieReviewList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkException(e));
                    }

                    @Override
                    public void onNext(MovieReviewList movieReviewList) {
                        callback.onSuccess(movieReviewList);
                    }
                });
    }

    public interface GetPopularMovieListCallback {
        void onSuccess(MovieList movieList);

        void onError(NetworkException exception);
    }

    public interface GetMovieDetailsCallback {
        void onSuccess(MovieDetails movieDetails);

        void onError(NetworkException exception);
    }

    public interface GetMovieTrailerListCallback {
        void onSuccess(MovieTrailerList movieTrailerList);

        void onError(NetworkException exception);
    }

    public interface GetMovieReviewListCallback {
        void onSuccess(MovieReviewList movieReviewList);

        void onError(NetworkException exception);
    }
}
