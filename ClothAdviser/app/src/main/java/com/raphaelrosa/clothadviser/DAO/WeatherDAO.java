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
    private String apiKey = "a92ec11192c73f10743e65f1e3d7abbb";

    @Override
    protected Boolean doInBackground(String... params){
        return false;
    }

    public String getWeather(Context context) throws Exception{
        OwmClient owm = new OwmClient();
        LocationService location = new LocationService(context);

        if (location.canGetLocation()) {
            WeatherStatusResponse currentWeather = owm.currentWeatherAroundPoint((float)location.getLatitude(),(float)location.getLongitude(),1);
            if (currentWeather.hasWeatherStatus()){
                WeatherData weather = currentWeather.getWeatherStatus().get(0);
                Toast.makeText(context, weather.getTemp() + "", Toast.LENGTH_SHORT);
            }
        }
        return "";
    }
}
