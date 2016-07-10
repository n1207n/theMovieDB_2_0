package silin.theMovieDB_2_0.screens.main;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.List;

import silin.theMovieDB_2_0.models.Movie;

/**
 * Created on 7/9/16: theMovieDB_2_0 by @n1207n
 */

public interface MainView extends MvpLceView<List<Movie>> {
    @Override
    void showLoading(boolean pullToRefresh);

    @Override
    void showContent();

    @Override
    void showError(Throwable e, boolean pullToRefresh);

    @Override
    void setData(List<Movie> data);

    @Override
    void loadData(boolean pullToRefresh);
}
