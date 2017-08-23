package a4c.weather.stage_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import a4c.weather.R;
import a4c.weather.stage_1.api.WeatherService;
import a4c.weather.stage_1.model.Model;
import a4c.weather.stage_1.service.ApiFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView weather;
    WeatherService weatherService;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);

        weather = (TextView) findViewById(R.id.weat);


        // Создаем сервис через который будет выполняться запрос
        weatherService = ApiFactory.getRetrofitInstance().create(WeatherService.class);
        getWeather();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void getWeather() {
        // Создаем экземпляр запроса со всем необходимыми настройками
        Call<Model> call = weatherService.getweather(551487, "metric");

        // Отображаем progress bar;

        // Выполняем запрос асинхронно
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if (response.isSuccessful()) {

                    // Если в ответ нам пришел код 2xx, то отображаем содержимое запроса
                    //weather.setText(String.valueOf(response.body().getCode()));
                    Toast.makeText(MainActivity.this, String.valueOf(response.body().getList().get(3).getMain1().getTemp()), Toast.LENGTH_SHORT).show();

                    weather.setText(String.valueOf(response.body().getList().get(0).getMain1().getTemp()));
                } else {
                    // Если пришел код ошибки, то обрабатываем её
                    Toast.makeText(MainActivity.this, String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {

                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
            }


            // В случае если запрос выполнился успешно, то мы переходим в метод onResponse(...)
        });
    }
}
