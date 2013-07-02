package com.tonyandollie.yafood;

import java.util.Map;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

	
public  class YaFoodPreferences {
	
	public static Map<String,String> loadPreferences (Activity a) {
	    SharedPreferences settings = a.getSharedPreferences("YaFood", android.content.Context.MODE_PRIVATE);
	    Log.d("DEBUG", settings.getAll().toString());
		return (Map<String,String>) settings.getAll();
	}
}
