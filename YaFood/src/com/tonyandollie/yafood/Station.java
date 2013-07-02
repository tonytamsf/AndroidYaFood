package com.tonyandollie.yafood;

import java.util.ArrayList;

import org.json.crockford.JSONArray;
import org.json.crockford.JSONObject;

import android.util.Log;

public class Station extends BaseModel {
	
    public static Station fromJson(JSONObject jsonObject) {
        Station station = new Station();
        station.jsonObject = jsonObject;
        Log.d("DEBUG", jsonObject.toString());
        return station;
    }
    
    public static ArrayList<Station> fromJson(JSONArray jsonArray) {
        ArrayList<Station> stations = new ArrayList<Station>(jsonArray.length());

        for (int i=0; i < jsonArray.length(); i++) {
            JSONObject StationJson = null;
            try {
                StationJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            Station station = Station.fromJson(StationJson);
            if (stations != null) {
                stations.add(station);
            }
        }

        return stations;
    }    
}
