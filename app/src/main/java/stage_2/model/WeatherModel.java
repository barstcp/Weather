package stage_2.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ramil on 22.08.2017.
 */

public class WeatherModel {

    @SerializedName("cod")
    private int code;

    public WeatherModel(int code, List<mos> list, String name, Long firstBrewed) {
        this.code = code;
        this.list = list;
        this.name = name;
        this.firstBrewed = firstBrewed;
    }

    public List<mos> getList() {

        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    //@SerializedName("list")
    private List<mos> list;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String  name) {
        this.name = name;
    }

    public Long getFirstBrewed() {
        return firstBrewed;
    }

    public void setFirstBrewed(Long firstBrewed) {
        this.firstBrewed = firstBrewed;
    }

    @SerializedName("message")
    private String name;

   @SerializedName("cnt")
    private Long firstBrewed;

    public WeatherModel(int code, String name, Long firstBrewed) {
        this.code = code;
        this.name = name;
        this.firstBrewed = firstBrewed;
    }

    public class mos {

        @SerializedName("dt")
        private Long main;

        @SerializedName("temp")
        private WeatherDTO temp;

        public Long getMain() {
            return main;
        }

        public void setMain(Long main) {
            this.main = main;
        }

        public WeatherDTO getMain1() {
            return temp;
        }

        public void setMain1(WeatherDTO main1) {
            this.temp = main1;
        }

        public mos(Long main, WeatherDTO main1) {

            this.main = main;
            this.temp = main1;
        }

    }

}
