package com.raphaelrosa.clothadviser.DAO;

import android.content.Context;
import android.widget.Toast;

import com.raphaelrosa.clothadviser.HomeActivity;
import com.raphaelrosa.clothadviser.Util.LocationService;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.Buffer;
import java.nio.charset.Charset;

/**
 * Created by Raphael on 10/07/2015.
 */
public class WeatherDAO {
    private String apiKey = "a92ec11192c73f10743e65f1e3d7abbb";

    public String getWeather(Context context) throws Exception{
        BufferedReader rd = null;
        try {
            String url = "http://api.openweathermap.org/data/2.5/weather?";

            LocationService location = new LocationService(context);

            if (location.canGetLocation()) {
                double latitude = Math.ceil(location.getLatitude());
                double longitude = Math.ceil(location.getLongitude());

                url += "lat=" + latitude + "&lon=" + longitude;
                Toast.makeText(context, url, Toast.LENGTH_SHORT).show();

                URL urlObject = new URL(url);

                rd = new BufferedReader(new InputStreamReader(urlObject.openStream()));
                StringBuffer sb = new StringBuffer();
                int cp;
                char[] chars = new char[1024];
                while ((cp = rd.read(chars)) != -1){
                    sb.append(chars,0, cp);
                }
                String jsonStr = sb.toString();
                JSONObject json = new JSONObject(jsonStr);
                //Toast.makeText(context, jsonStr, Toast.LENGTH_SHORT).show();
            } else {
                location.showSettingsAlert();
            }
        }catch(Exception e){
            throw e;
        }
        return "";
    }

}
