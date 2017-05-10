package com.example.trouversoiree;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Malik on 09/05/2017.
 */

public class ParserTask extends AsyncTask<String, Integer, List<HashMap<String, String>>> {

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
    @Override
    protected void onPostExecute(List<HashMap<String, String>> list) {

        Log.d("Map", "list size: " + list.size());
        // Clears all the existing markers;

        while (list.size()!=1) {
            for (int i = 0; i < list.size(); i++) {

                HashMap<String, String> hmPlaceI = list.get(i);

                for (int j = 0; j < list.size(); i++) {

                    HashMap<String, String> hmPlaceJ = list.get(j);
                    Integer ratingI = Integer.parseInt(hmPlaceI.get("rating"));
                    Integer ratingJ = Integer.parseInt(hmPlaceJ.get("rating"));

                    if (ratingI > ratingJ) {
                        list.remove(list.get(j));
                    }
                }
            }
        }

        // Getting the place from the places list
        HashMap<String, String> hmPlace = list.get(0);

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

        //Getting rating
        Integer rating = Integer.parseInt(hmPlace.get("rating"));

    }
}