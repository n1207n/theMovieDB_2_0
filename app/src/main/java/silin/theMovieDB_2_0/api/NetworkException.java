package silin.theMovieDB_2_0.api;

import android.text.TextUtils;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import javax.inject.Inject;

import autodagger.AutoInjector;
import retrofit2.adapter.rxjava.HttpException;
import silin.theMovieDB_2_0.BaseApplication;

import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;

/**
 * Created on 7/8/16: theMovieDB_2_0 by @n1207n
 *
 * Credit goes to https://github.com/ennur/Clean-Android-Code Thanks!
 */
@AutoInjector(BaseApplication.class)
public class NetworkException extends Throwable {

    @Inject
    Moshi mMoshi;

    private static final String DEFAULT_ERROR_MESSAGE = "Something went wrong! Please try again.";
    private static final String NETWORK_ERROR_MESSAGE = "No Internet Connection!";

    private final Throwable error;

    public NetworkException(Throwable e) {
        super(e);
        this.error = e;

        BaseApplication.sharedApplication().getComponentApplication().inject(this);
    }

    public String getMessage() {
        return error.getMessage();
    }

    public boolean isAuthFailure() {
        return error instanceof HttpException &&
                ((HttpException) error).code() == HTTP_UNAUTHORIZED;
    }

    public boolean isResponseNull() {
        return error instanceof HttpException && ((HttpException) error).response() == null;
    }

    public String getAppErrorMessage() {
        if (this.error instanceof IOException) return NETWORK_ERROR_MESSAGE;
        if (!(this.error instanceof HttpException)) return DEFAULT_ERROR_MESSAGE;

        retrofit2.Response<?> response = ((HttpException) this.error).response();

        if (response != null) {
            String status = getJsonStringFromResponse(response);
            if (!TextUtils.isEmpty(status)) return status;
        }

        return DEFAULT_ERROR_MESSAGE;
    }

    protected String getJsonStringFromResponse(final retrofit2.Response<?> response) {
        try {
            String jsonString = response.errorBody().string();
            JsonAdapter<ErrorResponse> errorResponseJsonAdapter = ErrorResponse.jsonAdapter(mMoshi);
            ErrorResponse errorResponse = errorResponseJsonAdapter.fromJson(jsonString);

            return errorResponse.status_message();
        } catch (Exception e) {
            return null;
        }
    }

    public Throwable getError() {
        return error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NetworkException that = (NetworkException) o;

        return error != null ? error.equals(that.error) : that.error == null;

    }

    @Override
    public int hashCode() {
        return error != null ? error.hashCode() : 0;
    }
}
