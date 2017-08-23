package a4c.weather.stage_1.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ramil on 19.08.2017.
 */

public class Model {

    @SerializedName("cod")
    private int code;

    public Model(int code, List<mos> list, String name, Long firstBrewed) {
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

    @SerializedName("list")
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

    public Model(int code, String name, Long firstBrewed) {
        this.code = code;
        this.name = name;
        this.firstBrewed = firstBrewed;
    }

   public class mos {

       @SerializedName("dt")
       private Long main;

       @SerializedName("main")
       private tt main1;

       public Long getMain() {
           return main;
       }

       public void setMain(Long main) {
           this.main = main;
       }

       public tt getMain1() {
           return main1;
       }

       public void setMain1(tt main1) {
           this.main1 = main1;
       }

       public mos(Long main, tt main1) {

           this.main = main;
           this.main1 = main1;
       }



   }


    public class tt{

        @SerializedName("temp")
        private double temp;

        public double getTemp() {
            return temp;
        }

        public void setTemp(int temp) {
            this.temp = temp;
        }

        public tt(int temp) {

            this.temp = temp;
        }
    }
   }
