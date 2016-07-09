package silin.theMovieDB_2_0.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import silin.theMovieDB_2_0.BuildConfig;
import silin.theMovieDB_2_0.IEnvironment;

/**
 * Created on 7/4/16: theMovieDB_2_0
 */
@Module
public class EnvironmentModule {
    @Provides
    @Singleton
    public IEnvironment providesEnvironment() {
        return BuildConfig.ENVIRONMENT;
    }
}
