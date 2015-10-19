package com.raphaelrosa.clothadviser.Activity;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.raphaelrosa.clothadviser.DAO.WeatherDAO;
import com.raphaelrosa.clothadviser.R;
import com.raphaelrosa.clothadviser.Util.GPSTracker;

/**
 * Created by Raphael on 17/07/2015.
 */
public class HomeFragment extends Fragment{


    public HomeFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


        GPSTracker tracker = new GPSTracker(Config.context);
        if (tracker.canGetLocation()) {
            float[] location = {(float)tracker.getLatitude(),(float)tracker.getLongitude()};
            WeatherDAO weatherController = new WeatherDAO(location);
            try {
                weatherController.execute();
            } catch (Exception e) {
                Toast.makeText(Config.context, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }else{
            tracker.showSettingsAlert();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_home,container,false);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
    }

    @Override
    public void onDetach(){
        super.onDetach();
    }

}
