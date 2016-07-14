package silin.theMovieDB_2_0.screens.details;

import com.hannesdorfmann.mosby.mvp.MvpView;

import silin.theMovieDB_2_0.models.MovieDetails;

/**
 * Created on 7/11/16: theMovieDB_2_0 by @n1207n
 */

public interface DetailsView extends MvpView {
    void setData(MovieDetails data);
    void showContent();

    void showError(Throwable e);
}
