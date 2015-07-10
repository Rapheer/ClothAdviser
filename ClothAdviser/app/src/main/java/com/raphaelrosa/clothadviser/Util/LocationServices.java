package com.raphaelrosa.clothadviser.Util;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by Raphael on 10/07/2015.
 */
public class LocationServices implements LocationListener{

    @Override
    public void onLocationChanged(Location loc){

    }

    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}
}
