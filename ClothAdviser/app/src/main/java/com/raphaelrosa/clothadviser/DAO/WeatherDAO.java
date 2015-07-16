package com.raphaelrosa.clothadviser.DAO;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.raphaelrosa.clothadviser.HomeActivity;
import com.raphaelrosa.clothadviser.Util.LocationService;

import org.bitpipeline.lib.owm.OwmClient;
import org.bitpipeline.lib.owm.WeatherData;
import org.bitpipeline.lib.owm.WeatherStatusResponse;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.Buffer;
import java.nio.charset.Charset;

/**
 * Created by Raphael on 10/07/2015.
 */
public class WeatherDAO extends AsyncTask<String, Void, Boolean>{
    private Context context;
    private String apiKey = "a92ec11192c73f10743e65f1e3d7abbb";
    private WeatherData weather;

    public WeatherDAO(Context context){
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(String... params){
        try {
            OwmClient owm = new OwmClient();
            LocationService location = new LocationService(this.context);
            float lat = (float)location.getLatitude();
            float lon = (float)location.getLongitude();
            if (location.canGetLocation()) {
                WeatherStatusResponse currentWeather = owm.currentWeatherAroundPoint(lat, lon, 10);
                if (currentWeather.hasWeatherStatus()) {
                    this.weather = currentWeather.getWeatherStatus().get(0);
                }
            }
            return true;
        }catch (Exception e){
            Toast.makeText(this.context,e.getMessage(),Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);

        Toast.makeText(this.context,"temperatura = "+ getTemp(true),Toast.LENGTH_SHORT).show();
    }

    public double getTemp(Boolean celsius){
        if (!celsius){
            return this.weather.getTemp();
        }

        return (this.weather.getTemp() - 273.15);
    }
}
