package com.raphaelrosa.clothadviser.DAO;

import android.content.Context;
import android.widget.Toast;

import com.raphaelrosa.clothadviser.HomeActivity;
import com.raphaelrosa.clothadviser.Util.LocationService;

/**
 * Created by Raphael on 10/07/2015.
 */
public class WeatherDAO {
    private String apiKey = "a92ec11192c73f10743e65f1e3d7abbb";

    public String getWeather(Context context){
        String url = "api.openweathermap.org/data/2.5/weather?";

        LocationService location = new LocationService(context);

        if (location.canGetLocation()){
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            Toast.makeText(context,"Teste: " + latitude + ", " + longitude, Toast.LENGTH_LONG).show();
        }else{
            location.showSettingsAlert();
        }

        return "";
    }

}
