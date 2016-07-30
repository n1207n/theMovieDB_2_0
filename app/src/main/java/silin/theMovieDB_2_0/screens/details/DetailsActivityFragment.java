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
import silin.theMovieDB_2_0.models.Company;
import silin.theMovieDB_2_0.models.Country;
import silin.theMovieDB_2_0.models.Genre;
import silin.theMovieDB_2_0.models.Language;
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

        String genreString = "| ", languageStrings = "| ", companyStrings = "| ", countryStrings = "| ";

        for (Genre genre : mMovieDetails.genres()) {
            genreString += genre.name() + " | ";
        }

        for (Language language : mMovieDetails.spoken_languages()) {
            languageStrings += language.name() + " | ";
        }

        for (Company company : mMovieDetails.production_companies()) {
            companyStrings += company.name() + " | ";
        }

        for (Country country : mMovieDetails.production_countries()) {
            countryStrings += country.name() + " | ";
        }

        mGenreTextView.setText(genreString);
        mLanguageTextView.setText(languageStrings);
        mCompanyTextView.setText(companyStrings);
        mCountryTextView.setText(countryStrings);
        mMovieDetails.imdbPath();
        mMovieDetails.homepage();
        mMovieDetails.revenue();
        mMovieDetails.status();
        mMovieDetails.tagline();
        // mMovieDetails.belongs_to_collection();
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
