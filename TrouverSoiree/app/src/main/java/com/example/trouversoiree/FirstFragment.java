package com.example.trouversoiree;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;


public class FirstFragment extends Fragment implements OnMapReadyCallback {
    public GoogleMap mGoogleMap;
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

    public void onViewCreated(View view, Bundle savedInstanceState) {
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

        GPSTracker gps = new GPSTracker(getContext());

        if (gps.canGetLocation()) {
            double Latitude1 = gps.getLatitude();
            double Longitude1 = gps.getLongitude();
            Toast.makeText(getContext(), Longitude1 + "   " + Latitude1, Toast.LENGTH_SHORT).show();
            moveMap(Latitude1, Longitude1);
        } else {
            gps.showSettingsAlert();
        }
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);

    }

    private void moveMap(double lat, double Long) {
        LatLng latLng = new LatLng(lat, Long);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(14.5f));
    }

}
