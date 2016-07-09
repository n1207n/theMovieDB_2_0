package silin.theMovieDB_2_0.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created on 7/4/16: theMovieDB_2_0
 */
@Module
public class SharedPrefModule {
    @Provides
    @Singleton
    public SharedPreferences providesSharedPreferences(@NonNull Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }
}
