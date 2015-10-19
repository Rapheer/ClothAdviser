package com.raphaelrosa.clothadviser.DAO;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import com.raphaelrosa.clothadviser.Activity.Config;
import com.raphaelrosa.clothadviser.R;
import com.raphaelrosa.clothadviser.Util.GPSTracker;

import org.bitpipeline.lib.owm.OwmClient;
import org.bitpipeline.lib.owm.WeatherData;
import org.bitpipeline.lib.owm.WeatherStatusResponse;

/**
 * Created by Raphael on 10/07/2015.
 */
public class WeatherDAO extends AsyncTask<String, Void, Float>{
    private Context context;
    private WeatherData weather;
    private float[] location;

    public WeatherDAO(float[] location){
        this.context = Config.context;
        this.location = location;
    }

    @Override
    protected Float doInBackground(String... params){
        try {

            OwmClient owm = new OwmClient();
            WeatherStatusResponse currentWeather = owm.currentWeatherAroundPoint(this.location[0], this.location[1], 10);
                if (currentWeather.hasWeatherStatus()) {
                    this.weather = currentWeather.getWeatherStatus().get(0);
                }
                return this.weather.getTemp();
        }catch (Exception e){
            return 0.0f;
        }
    }

    @Override
    protected void onPostExecute(Float result) {
        super.onPostExecute(result);
        TextView t = (TextView) Config.context.findViewById(R.id.weatherLabel);
        t.setText("Temperatura = " + this.getTemp(true));
        TextView t2 = (TextView) Config.context.findViewById(R.id.rainLabel);
        t2.append(" " + this.getRain());
    }

    public double getTemp(Boolean celsius){
        if (!celsius){
            return Math.round(this.weather.getTemp());
        }
        return Math.round(this.weather.getTemp() - 273.15);
    }

    public float getRain () {

        return this.weather.getPressure();
    }
}
