package silin.theMovieDB_2_0;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

import javax.inject.Singleton;

import autodagger.AutoComponent;
import autodagger.AutoInjector;
import silin.theMovieDB_2_0.modules.ContextModule;
import silin.theMovieDB_2_0.modules.EnvironmentModule;
import silin.theMovieDB_2_0.modules.NetworkModule;
import silin.theMovieDB_2_0.modules.SharedPrefModule;

/**
 * Created on 7/4/16: theMovieDB_2_0
 */
@AutoComponent(
        modules = {
                ContextModule.class,
                EnvironmentModule.class,
                NetworkModule.class,
                SharedPrefModule.class
        }
)
@Singleton
@AutoInjector(BaseApplication.class)
public class BaseApplication extends Application {

    // Shared BaseApplication for Mock testing that needs Application instances
    private static BaseApplication sBaseApplication;
    // Component for mocking any dependencies in testing
    private BaseApplicationComponent mBaseApplicationComponent;

    public static BaseApplication sharedApplication() {
        return sBaseApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);
        LeakCanary.install(this);

        sBaseApplication = this;

        buildComponents();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        sBaseApplication = null;
    }

    BaseApplicationComponent getComponentApplication() {
        return mBaseApplicationComponent;
    }

    private void buildComponents() {
        mBaseApplicationComponent = DaggerBaseApplicationComponent.builder()
                .build();
    }
}
