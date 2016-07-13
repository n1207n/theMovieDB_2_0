package silin.theMovieDB_2_0.screens.details;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import autodagger.AutoInjector;
import butterknife.BindView;
import butterknife.ButterKnife;
import se.emilsjolander.intentbuilder.Extra;
import se.emilsjolander.intentbuilder.IntentBuilder;
import silin.theMovieDB_2_0.BaseApplication;
import silin.theMovieDB_2_0.R;
import silin.theMovieDB_2_0.models.Movie;

@AutoInjector(BaseApplication.class)
@IntentBuilder
public class DetailsActivity extends AppCompatActivity {

    @Extra
    Movie mMovie;

    @Inject
    Picasso mPicasso;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.fab)
    FloatingActionButton mFloatingActionButton;

    @BindView(R.id.bar_image_background)
    ImageView mBarImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BaseApplication.sharedApplication().getComponentApplication().inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        DetailsActivityIntentBuilder.inject(getIntent(), this);

        initializeViews();

        // Fragment creation
        if (savedInstanceState == null) {
            DetailsActivityFragment fragment = new DetailsActivityFragmentBuilder(mMovie).build();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    private void initializeViews() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(mMovie.title());

        mPicasso.load(mMovie.posterPathWidth342())
                .fit()
                .noPlaceholder()
                .into(mBarImageView);

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Sharing coming soon!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
