package silin.theMovieDB_2_0.screens.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.fragmentargs.FragmentArgs;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.hannesdorfmann.mosby.mvp.MvpFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import silin.theMovieDB_2_0.R;
import silin.theMovieDB_2_0.models.Movie;

/**
 * A placeholder fragment containing a simple view.
 */
@FragmentWithArgs
public class DetailsActivityFragment extends MvpFragment<DetailsView, DetailsPresenter> {

    @Arg
    Movie mMovie;

    @BindView(R.id.movie_release_year_tv)
    TextView mReleaseYearTextView;

    @BindView(R.id.movie_runtime_tv)
    TextView mRuntimeTextView;

    @BindView(R.id.movie_rating_tv)
    TextView mRatingTextView;

    @BindView(R.id.movie_overview_tv)
    TextView mOverViewTextView;

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

        mReleaseYearTextView.setText(mMovie.release_date());
        mRuntimeTextView.setText(String.valueOf(mMovie.vote_count()));
        mRatingTextView.setText(getActivity().getResources().getString(R.string.movie_rating_format, String.valueOf(mMovie.vote_average())));
        mOverViewTextView.setText(mMovie.overview());
    }
}
