package silin.theMovieDB_2_0.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created on 7/4/16: theMovieDB_2_0
 */
@Module
public class ContextModule {
    private final Context mContext;

    public ContextModule(Context context) {
        this.mContext = context;
    }

    @Provides
    @Singleton
    public Context providesContext() {
        return mContext;
    }
}
