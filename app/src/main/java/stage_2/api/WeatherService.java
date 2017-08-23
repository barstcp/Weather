package stage_2.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import stage_2.model.WeatherModel;


public interface WeatherService {
    @GET("forecast/daily")
    Call<WeatherModel> getweather(@Query("id") long id, @Query("units") String metric, @Query("cnt") int cnt);
}
