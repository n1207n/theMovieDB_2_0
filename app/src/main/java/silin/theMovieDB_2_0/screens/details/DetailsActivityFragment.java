package silin.theMovieDB_2_0.screens.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.fragmentargs.FragmentArgs;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.hannesdorfmann.mosby.mvp.MvpFragment;

import autodagger.AutoInjector;
import butterknife.BindView;
import butterknife.ButterKnife;
import silin.theMovieDB_2_0.BaseApplication;
import silin.theMovieDB_2_0.R;
import silin.theMovieDB_2_0.models.Movie;
import silin.theMovieDB_2_0.models.MovieDetails;

/**
 * A placeholder fragment containing a simple view.
 */
@FragmentWithArgs
@AutoInjector(BaseApplication.class)
public class DetailsActivityFragment extends MvpFragment<DetailsView, DetailsPresenter> implements DetailsView {

    @Arg
    Movie mMovie;

    private MovieDetails mMovieDetails;

    @BindView(R.id.movie_release_year_tv)
    TextView mReleaseYearTextView;

    @BindView(R.id.movie_runtime_tv)
    TextView mRuntimeTextView;

    @BindView(R.id.movie_rating_tv)
    TextView mRatingTextView;

    @BindView(R.id.movie_overview_tv)
    TextView mOverViewTextView;

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
    }

    @Override
    public void setData(MovieDetails data) {
        mMovieDetails = data;
    }

    @Override
    public void showContent() {
        mReleaseYearTextView.setText(mMovieDetails.release_date());
        mRuntimeTextView.setText(getActivity().getResources().getString(R.string.movie_runtime_format, String.valueOf(mMovieDetails.runtime())));
        mRatingTextView.setText(getActivity().getResources().getString(R.string.movie_rating_format, String.valueOf(mMovieDetails.vote_average())));
        mOverViewTextView.setText(mMovieDetails.overview());
    }

    @Override
    public void showError(Throwable e) {
        Snackbar.make(mScrollView, "Failed to load movie details", Snackbar.LENGTH_LONG)
                .setAction("Reload", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loadMovieDetails();
                    }
                }).show();
    }
}
