package stage_3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

import a4c.weather.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import stage_2.api.WeatherService;
import stage_2.model.WeatherDTO;
import stage_2.model.WeatherModel;
import stage_2.service.ApiFactory;



/**
 * Created by Ramil on 22.08.2017.
 */

public class MainActivity extends AppCompatActivity {

    WeatherService weatherService;
    @BindView(R.id.barChart)
    BarChart mBarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphics);
        ButterKnife.bind(this);

        setupChart();

        weatherService = ApiFactory.getRetrofitInstance().create(WeatherService.class);

        getWeather();
    }

    private void addDataToChart(List<WeatherDTO> list, List<Long> longs) {
        List<BarEntry> entries = new ArrayList<>();
        List<BarEntry> entries1 = new ArrayList<>();
        List<BarEntry> entries2 = new ArrayList<>();
        List<BarEntry> entries3 = new ArrayList<>();
        List<BarEntry> entries4 = new ArrayList<>();
        List<BarEntry> entries5 = new ArrayList<>();
        List<BarEntry> entries6 = new ArrayList<>();


        entries.add(new BarEntry(0, Float.valueOf(Double.toString(list.get(0).getDay()))));
        entries1.add(new BarEntry(1, Float.valueOf(Double.toString(list.get(1).getDay()))));
        entries2.add(new BarEntry(2, Float.valueOf(Double.toString(list.get(2).getDay()))));
        entries3.add(new BarEntry(3, Float.valueOf(Double.toString(list.get(3).getDay()))));
        entries4.add(new BarEntry(4, Float.valueOf(Double.toString(list.get(4).getDay()))));
        entries5.add(new BarEntry(5, Float.valueOf(Double.toString(list.get(5).getDay()))));
        entries6.add(new BarEntry(6, Float.valueOf(Double.toString(list.get(6).getDay()))));



        List<BarDataSet> list1 = new ArrayList<>();

        BarDataSet set = new BarDataSet(entries,  convertDate(longs.get(0)));
        BarDataSet set1 = new BarDataSet(entries, convertDate(longs.get(1)));
        BarDataSet set2 = new BarDataSet(entries, convertDate(longs.get(2)));
        BarDataSet set3 = new BarDataSet(entries, convertDate(longs.get(3)));
        BarDataSet set4 = new BarDataSet(entries, convertDate(longs.get(4)));
        BarDataSet set5 = new BarDataSet(entries, convertDate(longs.get(5)));
        BarDataSet set6 = new BarDataSet(entries, convertDate(longs.get(6)));

        BarData data = new BarData(set, set1,set2, set3, set4, set5, set6);
        // устанавливаем ширину полосок графика
        data.setBarWidth(0.9f);
        mBarChart.setData(data);
        mBarChart.invalidate(); // refresh
    }

    public String convertDate(Long dateInMilliseconds) {
        return new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date (dateInMilliseconds*1000));
    }

    private void getWeather() {
        Call<WeatherModel> call = weatherService.getweather(484048, "metric", 7);

        call.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                if (response.isSuccessful()) {

                    List<WeatherModel.mos> weatherDTOList = response.body().getList();

                    List<WeatherDTO> weatherDTOList1 = new ArrayList<>();

                    List<Long> longs = new ArrayList<Long>();

                    for(int i =0; i < weatherDTOList.size(); i++) {
                        weatherDTOList1.add(weatherDTOList.get(i).getMain1());
                        longs.add(weatherDTOList.get(i).getMain());
                    }


                    addDataToChart(weatherDTOList1, longs);

                    //weatherAdapter.add(0, weatherDTOList1, longs);
                    Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();


                } else {
                    // Если пришел код ошибки, то обрабатываем её
                    Toast.makeText(MainActivity.this, String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void setupChart() {
        mBarChart.setDrawBarShadow(false);
        mBarChart.setDrawValueAboveBar(true);

        mBarChart.getDescription().setEnabled(false);

        // Если на экране отображается больше 60 значение, то подписи будут скрываться
        mBarChart.setMaxVisibleValueCount(60);

        mBarChart.setDrawGridBackground(false);

        // Устанавливаем что ось x будет подстраиваться под максимальное значение
        mBarChart.setFitBars(true);
    }
}
