package silin.theMovieDB_2_0.screens.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

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
import silin.theMovieDB_2_0.BaseApplication;
import silin.theMovieDB_2_0.BuildConfig;
import silin.theMovieDB_2_0.R;

@AutoInjector(BaseApplication.class)
public class MainActivity extends AppCompatActivity {

    @Inject
    OkHttpClient mClient;

    @Inject
    Picasso mPicasso;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.fab)
    FloatingActionButton mFloatingActionButton;

    private DebugDrawer debugDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BaseApplication.sharedApplication().getComponentApplication().inject(this);

        setTheme(R.style.AppTheme_NoActionBar);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initializeViews();

        if (BuildConfig.FLAVOR.equals("dev")) {
            buildDebugDrawer();
        }
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

    private void initializeViews() {
        setSupportActionBar(mToolbar);

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
