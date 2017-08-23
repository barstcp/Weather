package a4c.weather.stage_1.api;

import android.support.annotation.NonNull;

import java.io.IOException;

import a4c.weather.BuildConfig;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticationInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl url = originalHttpUrl.newBuilder().addQueryParameter("appid", BuildConfig.WEATER_API_ID).build();

        // Request customization: add request headers
        Request.Builder requestBuilder = original.newBuilder().url(url);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}