package silin.theMovieDB_2_0.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import com.novoda.merlin.Merlin;
import com.novoda.merlin.MerlinsBeard;
import com.squareup.moshi.Moshi;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import silin.theMovieDB_2_0.IEnvironment;

/**
 * Created on 7/4/16: theMovieDB_2_0
 */
@Module
public class NetworkModule {
    String mAPIBaseUrl;

    public NetworkModule(String APIBaseUrl) {
        this.mAPIBaseUrl = APIBaseUrl;
    }

    @Provides
    @Singleton
    public OkHttpClient providesOkHttpClient(@NonNull final IEnvironment environment) {
        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(environment.getHttpLoggingLevel());

        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    @Provides
    @Singleton
    public Merlin providesMerlin(@NonNull final Context context) {
        return new Merlin.Builder()
                .withConnectableCallbacks()
                .withDisconnectableCallbacks()
                .withBindableCallbacks()
                .withLogging(true)
                .build(context);
    }

    @Provides
    @Singleton
    public MerlinsBeard providesMerlinBeard(@NonNull final Context context) {
        return MerlinsBeard.from(context);
    }

    @Provides
    @Singleton
    public Picasso providesPicasso(@NonNull final Context context) {
        final Picasso picasso = Picasso.with(context);
        picasso.setIndicatorsEnabled(true);
        picasso.setLoggingEnabled(true);
        return picasso;
    }

    @Provides
    @Singleton
    public Moshi providesMoshi() {
        return new Moshi.Builder().build();
    }

    @Provides
    @Singleton
    public Retrofit providesRetrofit(@NonNull final Moshi moshi, @NonNull final OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(mAPIBaseUrl)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(okHttpClient)
                .build();
    }
}
