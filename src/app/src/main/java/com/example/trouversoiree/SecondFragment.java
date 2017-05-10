package com.example.trouversoiree;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import static android.R.attr.name;

public class SecondFragment extends android.support.v4.app.Fragment{

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.main_second_tab, container, false);
    return  root ;
    }



    /*public StringBuilder sbMethod() {
        GPSTracker gps = new GPSTracker(getContext());
        double Latitude1 = 0.0000;
        double Longitude1 = 0.0000;
        if (gps.canGetLocation()) {
            Latitude1 = gps.getLatitude();
            Longitude1 = gps.getLongitude();

        }
        double mLatitude = Latitude1;
        double mLongitude = Longitude1;

        StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        sb.append("location=").append(mLatitude).append(",").append(mLongitude);
        sb.append("&radius=5000");
        sb.append("&types=" + "restaurant");
        sb.append("&sensor=true");
        sb.append("&key=AIzaSyBf03qezXGPyW4nbcbu2rdC33uzSEfI8VY");

        Log.d("Map", "api: " + sb.toString());

        return sb;
    }*/

}