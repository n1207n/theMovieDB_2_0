package silin.theMovieDB_2_0.screens.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateFragment;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import autodagger.AutoInjector;
import butterknife.BindView;
import butterknife.ButterKnife;
import silin.theMovieDB_2_0.BaseApplication;
import silin.theMovieDB_2_0.R;
import silin.theMovieDB_2_0.api.NetworkException;
import silin.theMovieDB_2_0.models.Movie;

/**
 * A placeholder fragment containing a simple view.
 */
@AutoInjector(BaseApplication.class)
public class MainActivityFragment
        extends MvpLceViewStateFragment<SwipeRefreshLayout, List<Movie>, MainView, MainPresenter>
        implements MainView, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    Context mContext;

    @BindView(R.id.contentView)
    SwipeRefreshLayout mRefreshLayout;

    @BindView(R.id.loadingView)
    ProgressBar mProgressBar;

    @BindView(R.id.errorView)
    TextView mErrorTextView;

    @BindView(R.id.recycler_view_movie_list)
    RecyclerView mMovieRecyclerView;

    private MovieAdapter mMovieAdapter;

    public MainActivityFragment() {
        BaseApplication.sharedApplication().getComponentApplication().inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    /*
     * LCE ViewState setup start
     */

    @NonNull
    @Override
    public LceViewState<List<Movie>, MainView> createViewState() {
        return new RetainingLceViewState<>();
    }

    @Override
    public List<Movie> getData() {
        return mMovieAdapter == null ? null : mMovieAdapter.getMovieList();
    }

    /*
     LCE ViewState setup end
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set up the refresh listener
        contentView.setOnRefreshListener(this);

        // RecyclerView setup
        mMovieRecyclerView.setHasFixedSize(true);
        mMovieRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));

        // RecyclerView adapter setup
        mMovieAdapter = new MovieAdapter(new ArrayList<Movie>());
        mMovieRecyclerView.setAdapter(mMovieAdapter);

        // Load the movie list data
        loadData(false);
    }

    /*
     LCEView methods
     */

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return new NetworkException(e).getAppErrorMessage();
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        super.showLoading(pullToRefresh);
    }

    @Override
    public void hideLoading(boolean pullToRefresh) {
        if (pullToRefresh) contentView.setRefreshing(false);
    }

    @Override
    public void setData(List<Movie> data) {
        mMovieAdapter.updateData((ArrayList<Movie>) data);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadPopularMovieList(pullToRefresh);
    }
}
