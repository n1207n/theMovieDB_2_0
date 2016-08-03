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
import silin.theMovieDB_2_0.models.MovieTrailerList;

/**
 * Created on 7/11/16: theMovieDB_2_0 by @n1207n
 */
@AutoInjector(BaseApplication.class)
public class DetailsPresenter extends MvpBasePresenter<DetailsView> {
    @Inject
    APIService apiService;

    private CompositeSubscription mCompositeSubscription;
    private MovieDetails mMovieDetails;
    private MovieTrailerList mMovieTrailerList;

    DetailsPresenter() {
        BaseApplication.sharedApplication().getComponentApplication().inject(this);
        mCompositeSubscription = new CompositeSubscription();
    }

    String returnStringForGenreList() {
        String genreString = "Genres: ";

        for (int i = 0; i < mMovieDetails.genres().size(); i++) {
            if (i != mMovieDetails.genres().size() - 1) {
                genreString += mMovieDetails.genres().get(i).name() + " | ";
            } else {
                genreString += mMovieDetails.genres().get(i).name();
            }
        }

        return genreString;
    }

    String returnStringForLanguageList() {
        String languageString = "Languages: ";

        for (int i = 0; i < mMovieDetails.spoken_languages().size(); i++) {
            if (i != mMovieDetails.spoken_languages().size() - 1) {
                languageString += mMovieDetails.spoken_languages().get(i).name() + " | ";
            } else {
                languageString += mMovieDetails.spoken_languages().get(i).name();
            }
        }

        return languageString;
    }

    String returnStringForCompanyList() {
        String companyString = "Production companies: ";

        for (int i = 0; i < mMovieDetails.production_companies().size(); i++) {
            if (i != mMovieDetails.production_companies().size() - 1) {
                companyString += mMovieDetails.production_companies().get(i).name() + " | ";
            } else {
                companyString += mMovieDetails.production_companies().get(i).name();
            }
        }

        return companyString;
    }

    String returnStringForCountryList() {
        String countryString = "Production countries: ";

        for (int i = 0; i < mMovieDetails.production_countries().size(); i++) {
            if (i != mMovieDetails.production_countries().size() - 1) {
                countryString += mMovieDetails.production_countries().get(i).name() + " | ";
            } else {
                countryString += mMovieDetails.production_countries().get(i).name();
            }
        }

        return countryString;
    }

    void showMovieDetails(String movie_id) {
        if (isViewAttached()) {
            Subscription detailsSubscription = apiService.getMovieDetails(movie_id, new APIService.GetMovieDetailsCallback() {
                @Override
                public void onSuccess(MovieDetails movieDetails) {
                    DetailsPresenter.this.mMovieDetails = movieDetails;
                    getView().setData(DetailsPresenter.this.mMovieDetails);
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

    void showMovieTrailerDetails(String movie_id) {
        Subscription trailerSubscription = apiService.getMovieTrailers(movie_id, new APIService.GetMovieTrailerListCallback() {

            @Override
            public void onSuccess(MovieTrailerList movieTrailerList) {
                DetailsPresenter.this.mMovieTrailerList = movieTrailerList;
                getView().setTrailerRecyclerView();
                getView().setTrailerData(DetailsPresenter.this.mMovieTrailerList);
            }

            @Override
            public void onError(NetworkException exception) {
                getView().showError(exception);
            }
        });

        mCompositeSubscription.add(trailerSubscription);
    }

    void onStop() {
        if (!mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
