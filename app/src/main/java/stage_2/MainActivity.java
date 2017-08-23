package stage_2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    WeatherAdapter weatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        List<WeatherDTO> weatherDTOList = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        weatherAdapter = new WeatherAdapter(weatherDTOList);

        recyclerView.setAdapter(weatherAdapter);

        weatherService = ApiFactory.getRetrofitInstance().create(WeatherService.class);

        getWeather();
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


                    weatherAdapter.add(0, weatherDTOList1, longs);
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


    class WeatherAdapter extends RecyclerView.Adapter<weatherViewHolder> {

        List<WeatherDTO> weatherDTOList;
        List<Long> longs;

        public WeatherAdapter(List<WeatherDTO> list) {
            weatherDTOList = list;

        }

        @Override
        public weatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View rowView = layoutInflater.inflate(R.layout.weather_row,parent,false);
            return new weatherViewHolder(rowView);
        }

        @Override
        public void onBindViewHolder(weatherViewHolder holder, int position) {
            WeatherDTO weatherDTO = weatherDTOList.get(position);
            holder.morn.setText(String.valueOf(weatherDTO.getDay()));
            holder.day.setText(String.valueOf(weatherDTO.getMorn()));
            holder.ev.setText(String.valueOf(weatherDTO.getEve()));
            holder.night.setText(String.valueOf(weatherDTO.getNight()));


            holder.dt.setText(convertDate(longs.get(position),"dd/MM/yyyy"));
        }

        public String convertDate(Long dateInMilliseconds,String dateFormat) {
            return new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date (dateInMilliseconds*1000));
        }

        @Override
        public int getItemCount() {
            return weatherDTOList.size();
        }

        public void add(int i, List<WeatherDTO> newList, List<Long> longs) {
            weatherDTOList.addAll(i, newList);
            this.longs = longs;
            notifyItemRangeInserted(i, newList.size());
        }
    }

    public class weatherViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.morn)
        TextView morn;
        @BindView(R.id.day)
        TextView day;
        @BindView(R.id.ev)
        TextView ev;
        @BindView(R.id.night)
        TextView night;
        @BindView(R.id.dt)
        TextView dt;


        public weatherViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
