package com.raphaelrosa.clothadviser.DAO;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.raphaelrosa.clothadviser.Util.LocationService;

import org.bitpipeline.lib.owm.OwmClient;
import org.bitpipeline.lib.owm.WeatherData;
import org.bitpipeline.lib.owm.WeatherStatusResponse;

/**
 * Created by Raphael on 10/07/2015.
 */
public class WeatherDAO extends AsyncTask<String, Void, String>{
    private Context context;
    private String apiKey = "a92ec11192c73f10743e65f1e3d7abbb";
    private WeatherData weather;

    public WeatherDAO(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params){
        try {

            OwmClient owm = new OwmClient();
            LocationService location = new LocationService(this.context);
            if (location.canGetLocation()) {
                float lat =(float) Math.floor(location.getLatitude());
                float lon = (float) Math.floor(location.getLongitude());
                WeatherStatusResponse currentWeather = owm.currentWeatherAroundPoint(lat, lon, 10);
                if (currentWeather.hasWeatherStatus()) {
                    this.weather = currentWeather.getWeatherStatus().get(0);
                }
                return lat + ", " + lon;
            }
        }catch (Exception e){
            return "";
        }
        return "";
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        Toast.makeText(this.context,result,Toast.LENGTH_SHORT).show();
    }

    public double getTemp(Boolean celsius){
        if (!celsius){
            return Math.floor(this.weather.getTemp());
        }
        return Math.floor(this.weather.getTemp() - 273.15);
    }
}
