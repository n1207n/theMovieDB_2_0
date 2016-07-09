package silin.theMovieDB_2_0;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created on 7/4/16: theMovieDB_2_0
 */

public interface IEnvironment {
    HttpLoggingInterceptor.Level getHttpLoggingLevel();

    boolean isDebugDrawerEnabled();
}
