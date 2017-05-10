package com.example.trouversoiree;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class FirstFragment extends Fragment implements OnMapReadyCallback {
    public GoogleMap mGoogleMap;
    MapView mMapView;
    View mView;
    double Latitude1 ;
    double Longitude1;
    public int k = 1;
    public HashMap<String, String> barChoisi = new HashMap<String, String>();
    public HashMap<String, String> restauChoisi = new HashMap<String, String>();
    public HashMap<String, String> boiteChoisi = new HashMap<String, String>();

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
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {
            GPSTracker gps = new GPSTracker(getContext());

            if (gps.canGetLocation()) {
                Latitude1 = gps.getLatitude();
                Longitude1 = gps.getLongitude();
                moveMap(Latitude1, Longitude1);
            } else {
                gps.showSettingsAlert();
            }
            mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
            mGoogleMap.setMyLocationEnabled(true);
            mGoogleMap.getUiSettings().setMapToolbarEnabled(true);

            while(k <= 3) {

                if (k == 1) {
                    StringBuilder sbValueRestau = new StringBuilder(sbMethod("restaurant"));
                    PlacesTask placesTaskRestau = new PlacesTask();
                    placesTaskRestau.execute(sbValueRestau.toString());
                    k++;
                }
                if (k == 2) {
                    StringBuilder sbValueBar = new StringBuilder(sbMethod("bar"));
                    PlacesTask placesTaskBar = new PlacesTask();
                    placesTaskBar.execute(sbValueBar.toString());
                    k++;
                }
                if (k==3) {
                    StringBuilder sbValueBoite = new StringBuilder(sbMethod("night_club"));
                    PlacesTask placesTaskBoite = new PlacesTask();
                    placesTaskBoite.execute(sbValueBoite.toString());
                   k++;
                }
            }

            mGoogleMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
                @Override

                @Deprecated public boolean onMyLocationButtonClick() {

                    if (mGoogleMap.getMyLocation() == null) {
                        Toast.makeText(getContext(), "Désolé, impossible de trouver votre position actuelle", Toast.LENGTH_SHORT).show();
                    } else {
                        Longitude1 = mGoogleMap.getMyLocation().getLongitude();
                        Latitude1 = mGoogleMap.getMyLocation().getLatitude();

                        LatLng loc = new LatLng(Latitude1, Longitude1);

                        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
                        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(18));
                    }
                    return true;
                }
            });

        }     }





    private void moveMap(double lat, double Long) {
        LatLng latLng = new LatLng(lat, Long);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(13));
    }

    public StringBuilder sbMethod(String type)
    {

        //use your current location here

        double mLatitude = Latitude1;
        double mLongitude = Longitude1;


        StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        sb.append("location=").append(mLatitude).append(",").append(mLongitude);
        sb.append("&radius=2000");
        sb.append("&types=").append(type);
        sb.append("&sensor=true");

        sb.append("&key=AIzaSyCUmeItPM6WGFdWJvgvdYiPBI42yCx6q4Y");

        Log.d("Map", "url: " + sb.toString());

        return sb;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "Impossible de vous localiser sans autorisation", Toast.LENGTH_LONG).show();
            } else {
                onMapReady(mGoogleMap);
            }
        }
    }

    private class PlacesTask extends AsyncTask<String, Integer, String>
    {

        String data = null;

        // Invoked by execute() method of this object
        @Override
        protected String doInBackground(String... url) {
            try {
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        // Executed after the complete execution of doInBackground() method
        @Override
        protected void onPostExecute(String result) {
            ParserTask parserTask = new ParserTask();

            // Start parsing the Google places in JSON format
            // Invokes the "doInBackground()" method of the class ParserTask
            parserTask.execute(result);
        }
    }

    private String downloadUrl(String strUrl) throws IOException
    {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
            assert iStream != null;
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    private class ParserTask extends AsyncTask<String, Integer, List<HashMap<String, String>>> {

        JSONObject jObject;

        // Invoked by execute() method of this object
        @Override
        protected List<HashMap<String, String>> doInBackground(String... jsonData) {

            List<HashMap<String, String>> places = null;
            Place_JSON placeJson = new Place_JSON();

            try {
                jObject = new JSONObject(jsonData[0]);

                places = placeJson.parse(jObject);

            } catch (Exception e) {
                Log.d("Exception", e.toString());
            }
            return places;
        }

        // Executed after the complete execution of doInBackground() method
        protected void onPostExecute(List<HashMap<String, String>> list) {

            Log.d("Map", "list size: " + list.size());


           // if(k == 1) {
                for (int i = 0; i < list.size(); i++) {

                    // Creating a marker
                    MarkerOptions markerOptions = new MarkerOptions();
                    // Getting a place from the places list
                    HashMap<String, String> hmPlace = list.get(i);
                    // Getting latitude of the place
                    double lat = Double.parseDouble(hmPlace.get("lat"));
                    // Getting longitude of the place
                    double lng = Double.parseDouble(hmPlace.get("lng"));
                    // Getting name
                    String name = hmPlace.get("place_name");
                    Log.d("Map", "place: " + name);

                    // Getting vicinity
                    String vicinity = hmPlace.get("vicinity");

                    LatLng latLng = new LatLng(lat, lng);

                    // Setting the position for the marker
                    markerOptions.position(latLng);

                    markerOptions.title(name + " : " + vicinity);

                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                    Marker m = mGoogleMap.addMarker(markerOptions);
                }
            /*}

            else if (k == 2) {

                for (int i = 0; i < list.size(); i++) {

                    // Creating a marker
                    MarkerOptions markerOptions = new MarkerOptions();

                    // Getting a place from the places list
                    HashMap<String, String> hmPlace = list.get(i);


                    // Getting latitude of the place
                    double lat = Double.parseDouble(hmPlace.get("lat"));

                    // Getting longitude of the place
                    double lng = Double.parseDouble(hmPlace.get("lng"));

                    // Getting name
                    String name = hmPlace.get("place_name");

                    Log.d("Map", "place: " + name);

                    // Getting vicinity
                    String vicinity = hmPlace.get("vicinity");

                    LatLng latLng = new LatLng(lat, lng);

                    // Setting the position for the marker
                    markerOptions.position(latLng);

                    markerOptions.title(name + " : " + vicinity);


                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                    Marker m = mGoogleMap.addMarker(markerOptions);
                }
                Toast.makeText(getContext(), String.valueOf(k), Toast.LENGTH_SHORT).show();

            }

            else if (k== 3){

                for (int i = 0; i < list.size(); i++) {

                    // Creating a marker
                    MarkerOptions markerOptions = new MarkerOptions();

                    // Getting a place from the places list
                    HashMap<String, String> hmPlace = list.get(i);


                    // Getting latitude of the place
                    double lat = Double.parseDouble(hmPlace.get("lat"));

                    // Getting longitude of the place
                    double lng = Double.parseDouble(hmPlace.get("lng"));

                    // Getting name
                    String name = hmPlace.get("place_name");

                    Log.d("Map", "place: " + name);

                    // Getting vicinity
                    String vicinity = hmPlace.get("vicinity");

                    LatLng latLng = new LatLng(lat, lng);

                    // Setting the position for the marker
                    markerOptions.position(latLng);

                    markerOptions.title(name + " : " + vicinity);


                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                    Marker m = mGoogleMap.addMarker(markerOptions);

                }
                Toast.makeText(getContext(), String.valueOf(k), Toast.LENGTH_SHORT).show();

                // Placing a marker on the touched position
                //Marker m = mGoogleMap.addMarker(markerOptions);


            }*/


        }

        /*public void afficheMarkers(List<HashMap<String, String>> list, float color){
            for (int i = 0; i < list.size(); i++) {

                // Creating a marker
                MarkerOptions markerOptions = new MarkerOptions();

                // Getting a place from the places list
                HashMap<String, String> hmPlace = list.get(i);


                // Getting latitude of the place
                double lat = Double.parseDouble(hmPlace.get("lat"));

                // Getting longitude of the place
                double lng = Double.parseDouble(hmPlace.get("lng"));

                // Getting name
                String name = hmPlace.get("place_name");

                Log.d("Map", "place: " + name);

                // Getting vicinity
                String vicinity = hmPlace.get("vicinity");

                LatLng latLng = new LatLng(lat, lng);

                // Getting rating

                // Setting the position for the marker
                markerOptions.position(latLng);

                markerOptions.title(name + " : " + vicinity);

                MarkerOptions icon = markerOptions.icon(BitmapDescriptorFactory.defaultMarker(color));

                // Placing a marker on the touched position
                Marker m = mGoogleMap.addMarker(markerOptions);
            }
        }*/
    }




    public class Place_JSON {

        /**
         * Receives a JSONObject and returns a list
         */
        public List<HashMap<String, String>> parse(JSONObject jObject) {

            JSONArray jPlaces = null;
            try {
                /** Retrieves all the elements in the 'places' array */
                jPlaces = jObject.getJSONArray("results");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            /** Invoking getPlaces with the array of json object
             * where each json object represent a place
             */
            return getPlaces(jPlaces);
        }

        private List<HashMap<String, String>> getPlaces(JSONArray jPlaces) {
            int placesCount = jPlaces.length();
            List<HashMap<String, String>> placesList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> place = null;

            /** Taking each place, parses and adds to list object */
            for (int i = 0; i < placesCount; i++) {
                try {
                    /** Call getPlace with place JSON object to parse the place */
                    place = getPlace((JSONObject) jPlaces.get(i));
                    placesList.add(place);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return placesList;
        }

        /**
         * Parsing the Place JSON object
         */
        private HashMap<String, String> getPlace(JSONObject jPlace)
        {

            HashMap<String, String> place = new HashMap<String, String>();
            String placeName = "-NA-";
            String vicinity = "-NA-";
            String latitude = "";
            String longitude = "";
            String reference = "";
            String rating = "";
            String type = "";

            try {
                // Extracting Place name, if available
                if (!jPlace.isNull("name")) {
                    placeName = jPlace.getString("name");
                }

                // Extracting Place Vicinity, if available
                if (!jPlace.isNull("vicinity")) {
                    vicinity = jPlace.getString("vicinity");
                }

                latitude = jPlace.getJSONObject("geometry").getJSONObject("location").getString("lat");
                longitude = jPlace.getJSONObject("geometry").getJSONObject("location").getString("lng");
                reference = jPlace.getString("reference");
                place.put("place_name", placeName);
                place.put("vicinity", vicinity);
                place.put("lat", latitude);
                place.put("lng", longitude);
                place.put("reference", reference);

            } catch (JSONException e) {
                e.printStackTrace();
            }


            return place;
        }
    }


}
