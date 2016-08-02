package silin.theMovieDB_2_0.screens.details;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import autodagger.AutoInjector;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.palaima.debugdrawer.DebugDrawer;
import io.palaima.debugdrawer.commons.BuildModule;
import io.palaima.debugdrawer.commons.DeviceModule;
import io.palaima.debugdrawer.commons.NetworkModule;
import io.palaima.debugdrawer.commons.SettingsModule;
import io.palaima.debugdrawer.okhttp3.OkHttp3Module;
import io.palaima.debugdrawer.picasso.PicassoModule;
import io.palaima.debugdrawer.scalpel.ScalpelModule;
import okhttp3.OkHttpClient;
import se.emilsjolander.intentbuilder.Extra;
import se.emilsjolander.intentbuilder.IntentBuilder;
import silin.theMovieDB_2_0.BaseApplication;
import silin.theMovieDB_2_0.BuildConfig;
import silin.theMovieDB_2_0.R;
import silin.theMovieDB_2_0.models.Movie;

@AutoInjector(BaseApplication.class)
@IntentBuilder
public class DetailsActivity extends AppCompatActivity {

    @Extra
    Movie mMovie;

    @Inject
    OkHttpClient mClient;

    @Inject
    Picasso mPicasso;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.fab)
    FloatingActionButton mFloatingActionButton;

    @BindView(R.id.bar_image_background)
    ImageView mBarImageView;

    private DebugDrawer debugDrawer;

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

        if (BuildConfig.FLAVOR.equals("dev")) {
            buildDebugDrawer();
        }
    }

    private void initializeViews() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(mMovie.title());
        getSupportActionBar().setSubtitle(mMovie.release_date().split("-")[0]);

        mPicasso.load(mMovie.posterPathWidth342())
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

    private void buildDebugDrawer() {
        debugDrawer = new DebugDrawer.Builder(this)
                .modules(
                        new ScalpelModule(this),
                        new OkHttp3Module(mClient),
                        new PicassoModule(mPicasso),
                        new DeviceModule(this),
                        new BuildModule(this),
                        new NetworkModule(this),
                        new SettingsModule(this)
                ).build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        debugDrawer.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        debugDrawer.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        debugDrawer.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        debugDrawer.onStop();
    }
}
