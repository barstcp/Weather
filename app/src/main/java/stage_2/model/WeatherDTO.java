package stage_2.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramil on 22.08.2017.
 */

public class WeatherDTO {

    @SerializedName("day")
    private double day;

    @SerializedName("night")
    private double night;

    @SerializedName("eve")
    private double eve;

    @SerializedName("morn")
    private double morn;

    public double getDay() {
        return day;
    }

    public void setDay(double day) {
        this.day = day;
    }

    public double getNight() {
        return night;
    }

    public void setNight(double night) {
        this.night = night;
    }

    public double getEve() {
        return eve;
    }

    public void setEve(double eve) {
        this.eve = eve;
    }

    public double getMorn() {
        return morn;
    }

    public void setMorn(double morn) {
        this.morn = morn;
    }

    public WeatherDTO(double day, double night, double eve, double morn) {

        this.day = day;
        this.night = night;
        this.eve = eve;
        this.morn = morn;
    }

}
