package stage_2.service;

import android.support.annotation.NonNull;

import a4c.weather.stage_1.api.AuthenticationInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ApiFactory {

    private static final String API_BASE_URL = "http://api.openweathermap.org/data/2.5/";

    private static Retrofit sRetrofit;

    private static OkHttpClient sHttpClient;

    private ApiFactory() {
        throw new IllegalStateException("Final class can not be instantiated");
    }

    @NonNull
    public static Retrofit getRetrofitInstance() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .client(sHttpClient == null ? sHttpClient = provideClient() : sHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }

    private static OkHttpClient provideClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();

            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));

        builder.addInterceptor(new AuthenticationInterceptor());
        return builder.build();
    }
}
