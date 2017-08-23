package a4c.weather.stage_1.api;

import a4c.weather.stage_1.model.Model;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Timur Valiev
 */
public interface WeatherService {
    @GET("forecast")
    Call<Model> getweather(@Query("id") long id, @Query("units") String metric);
}
