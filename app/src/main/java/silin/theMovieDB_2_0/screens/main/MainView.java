package silin.theMovieDB_2_0.screens.main;

import silin.theMovieDB_2_0.models.MovieList;

/**
 * Created on 7/9/16: theMovieDB_2_0 by @n1207n
 */

public interface MainView {
    void showLoading();

    void hideLoading();

    void showLoadingMovieListError(String errorMessage);

    void showMovieList(MovieList movieList);
}
