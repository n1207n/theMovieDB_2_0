package silin.theMovieDB_2_0.screens.details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hannesdorfmann.fragmentargs.FragmentArgs;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.hannesdorfmann.mosby.mvp.MvpFragment;

import java.util.ArrayList;

import autodagger.AutoInjector;
import butterknife.BindView;
import butterknife.ButterKnife;
import silin.theMovieDB_2_0.BaseApplication;
import silin.theMovieDB_2_0.R;
import silin.theMovieDB_2_0.models.Movie;
import silin.theMovieDB_2_0.models.MovieDetails;
import silin.theMovieDB_2_0.models.MovieTrailer;
import silin.theMovieDB_2_0.models.MovieTrailerList;

/**
 * A placeholder fragment containing a simple view.
 */
@FragmentWithArgs
@AutoInjector(BaseApplication.class)
public class DetailsActivityFragment extends MvpFragment<DetailsView, DetailsPresenter> implements DetailsView {

    @Arg
    Movie mMovie;

    private MovieDetails mMovieDetails;

    private MovieTrailerList mMovieTrailerData;

    private MovieTrailerAdapter mMovieTrailerAdapter;

    @BindView(R.id.popularity_value)
    TextView mPopularityTextView;

    @BindView(R.id.vote_rating_value)
    TextView mRatingTextView;

    @BindView(R.id.movie_info_value)
    TextView mInfoTextView;

    @BindView(R.id.overview_value)
    TextView mOverviewTextView;

    @BindView(R.id.imdb_btn)
    Button mImdbButton;

    @BindView(R.id.homepage_btn)
    Button mHomepageButton;

    @BindView(R.id.movie_trailer_list)
    RecyclerView mTrailerRecyclerView;

    @BindView(R.id.content_movie_scroll_view)
    NestedScrollView mScrollView;

    public DetailsActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        FragmentArgs.inject(this);
    }

    @NonNull
    @Override
    public DetailsPresenter createPresenter() {
        return new DetailsPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadMovieDetails();
    }

    private void loadMovieDetails() {
        presenter.showMovieDetails(String.valueOf(mMovie.id()));
        presenter.showMovieTrailerDetails(String.valueOf(mMovie.id()));
    }

    @Override
    public void setData(MovieDetails data) {
        mMovieDetails = data;
    }

    @Override
    public void showContent() {
        mPopularityTextView.setText(presenter.getPopularityString());
        mRatingTextView.setText(presenter.getRatingString());
        mInfoTextView.setText(presenter.getMovieInfoString());
        mOverviewTextView.setText(mMovieDetails.overview());
        mImdbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mMovieDetails.imdbPath())));
            }
        });

        mHomepageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mMovieDetails.homepage().equals("")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mMovieDetails.homepage())));
                } else {
                    Snackbar.make(mScrollView, "There is no homepage yet. Sorry!", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void setTrailerData(MovieTrailerList trailerData) {
        mMovieTrailerData = trailerData;
        mMovieTrailerAdapter.updateData((ArrayList<MovieTrailer>) mMovieTrailerData.results());
    }

    @Override
    public void setTrailerRecyclerView() {
        mTrailerRecyclerView.setHasFixedSize(true);
        mTrailerRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        if (mMovieTrailerAdapter == null) {
            mMovieTrailerAdapter = new MovieTrailerAdapter(getContext(), new ArrayList<MovieTrailer>());
        }

        mTrailerRecyclerView.setAdapter(mMovieTrailerAdapter);
    }

    @Override
    public void showError(Throwable e) {
        Snackbar.make(mScrollView, String.format("Failed to load movie details: %s", e.getMessage()), Snackbar.LENGTH_LONG)
                .setAction("Reload", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loadMovieDetails();
                    }
                }).show();
    }
}
