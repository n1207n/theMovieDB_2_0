package silin.theMovieDB_2_0.screens.details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    @BindView(R.id.popularity_value)
    TextView mPopularityTextView;

    @BindView(R.id.vote_rating_value)
    TextView mVoteRatingTextView;

    @BindView(R.id.overview_value)
    TextView mOverviewTextView;

    @BindView(R.id.genre_value)
    TextView mGenreTextView;

    @BindView(R.id.language_value)
    TextView mLanguageTextView;

    @BindView(R.id.company_value)
    TextView mCompanyTextView;

    @BindView(R.id.country_value)
    TextView mCountryTextView;

    @BindView(R.id.imdb_btn)
    Button mImdbButton;

    @BindView(R.id.homepage_btn)
    Button mHomepageButton;

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
        mPopularityTextView.setText(getActivity().getResources().getString(R.string.movie_popularity_format, mMovieDetails.popularity()));
        mVoteRatingTextView.setText(getActivity().getResources().getString(R.string.movie_rating_format, mMovieDetails.vote_average()));
        mOverviewTextView.setText(mMovieDetails.overview());
        mGenreTextView.setText(presenter.returnStringForGenreList());
        mLanguageTextView.setText(presenter.returnStringForLanguageList());
        mCompanyTextView.setText(presenter.returnStringForCompanyList());
        mCountryTextView.setText(presenter.returnStringForCountryList());
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

        mMovieDetails.revenue();
        mMovieDetails.status();
        mMovieDetails.tagline();
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
