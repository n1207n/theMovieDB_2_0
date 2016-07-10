package silin.theMovieDB_2_0.screens.main;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

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
public class MainPresenter extends MvpBasePresenter<MainView> {
    @Inject
    APIService mApiService;

    private CompositeSubscription mCompositeSubscription;

    MainPresenter() {
        BaseApplication.sharedApplication().getComponentApplication().inject(this);
        mCompositeSubscription = new CompositeSubscription();
    }

    void loadPopularMovieList(final boolean pullToRefresh) {
        if (isViewAttached()) getView().showLoading(pullToRefresh);

        Subscription subscription = mApiService.getPopularMovieList(new APIService.GetPopularMovieListCallback() {
            @Override
            public void onSuccess(MovieList movieList) {
                if (isViewAttached()) {
                    getView().setData(movieList.results());
                    getView().showContent();
                }
            }

            @Override
            public void onError(NetworkException exception) {
                if (isViewAttached()) {
                    getView().showError(exception, pullToRefresh);
                }
            }
        });

        mCompositeSubscription.add(subscription);
    }

    void onStop() {
        if (!mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
