package silin.theMovieDB_2_0.screens.main;

import javax.inject.Inject;

import autodagger.AutoInjector;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import silin.theMovieDB_2_0.BaseApplication;
import silin.theMovieDB_2_0.api.APIService;
import silin.theMovieDB_2_0.api.NetworkException;
import silin.theMovieDB_2_0.models.MovieList;

/**
 * Created on 7/9/16: theMovieDB_2_0 by @n1207n
 */
@AutoInjector(BaseApplication.class)
public class MainPresenter {
    @Inject
    APIService apiService;

    private final CompositeSubscription compositeSubscription;

    private final MainView mainView;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;

        compositeSubscription = new CompositeSubscription();
    }

    public void loadPopularMovieList() {
        mainView.showLoading();

        Subscription subscription = apiService.getPopularMovieList(new APIService.GetPopularMovieListCallback() {
            @Override
            public void onSuccess(MovieList movieList) {
                mainView.hideLoading();
                mainView.showMovieList(movieList);
            }

            @Override
            public void onError(NetworkException exception) {
                mainView.hideLoading();
                mainView.showLoadingMovieListError(exception.getAppErrorMessage());
            }
        });

        compositeSubscription.add(subscription);
    }

    public void onStop() {
        if (!compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
    }
}
