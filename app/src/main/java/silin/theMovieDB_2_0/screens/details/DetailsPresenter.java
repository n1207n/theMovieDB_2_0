package silin.theMovieDB_2_0.screens.details;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import javax.inject.Inject;

import autodagger.AutoInjector;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import silin.theMovieDB_2_0.BaseApplication;
import silin.theMovieDB_2_0.api.APIService;
import silin.theMovieDB_2_0.api.NetworkException;
import silin.theMovieDB_2_0.models.MovieDetails;

/**
 * Created on 7/11/16: theMovieDB_2_0 by @n1207n
 */
@AutoInjector(BaseApplication.class)
public class DetailsPresenter extends MvpBasePresenter<DetailsView> {
    @Inject
    APIService apiService;

    private CompositeSubscription mCompositeSubscription;

    DetailsPresenter() {
        BaseApplication.sharedApplication().getComponentApplication().inject(this);
        mCompositeSubscription = new CompositeSubscription();
    }

    void showMovieDetails(String movie_id) {
        if (isViewAttached()) {
            Subscription detailsSubscription = apiService.getMovieDetails(movie_id, new APIService.GetMovieDetailsCallback() {
                @Override
                public void onSuccess(MovieDetails movieDetails) {
                    getView().setData(movieDetails);
                    getView().showContent();
                }

                @Override
                public void onError(NetworkException exception) {
                    getView().showError(exception);
                }
            });

            mCompositeSubscription.add(detailsSubscription);
        }
    }

    void onStop() {
        if (!mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
