package com.example.trouversoiree;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;


public class FirstFragment extends Fragment implements OnMapReadyCallback{
    GoogleMap mGoogleMap;
    MapView mMapView;
    View mView;

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.main_first_tab, container, false);
        Button organiserButton = (Button) mView.findViewById(R.id.button_organiser);
       organiserButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               android.support.v4.app.Fragment fragment = null;
               fragment = new ChoixParamsBarFrag();

                   android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                   android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
                   transaction.replace(R.id.choixparambar, fragment);
                   transaction.addToBackStack(null);
                   transaction.commit();

           }
        });

        return mView;
    }

    public void onViewCreated (View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        mMapView = (MapView) mView.findViewById(R.id.mapView);
        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());

        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

    }
}