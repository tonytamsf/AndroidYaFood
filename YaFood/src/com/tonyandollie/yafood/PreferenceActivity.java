package com.tonyandollie.yafood;

import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

public class PreferenceActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preference);
		loadSpinner("defaultCafe", R.id.spDefaultCafe, R.array.cafeNames);
		
		Map<String,String> prefs = YaFoodPreferences.loadPreferences(this);

		CheckBox cbVegetarian = (CheckBox) findViewById(R.id.cb_vegetarian);
		CheckBox cbVegan = (CheckBox) findViewById(R.id.cb_vegan);
		CheckBox cbFarmToFork = (CheckBox) findViewById(R.id.cb_farm_to_fork);
		CheckBox cbMadeWithoutGlutun = (CheckBox) findViewById(R.id.cb_made_without_gluten);
		cbVegetarian.setChecked(Boolean.parseBoolean(prefs.get("vegetarian")));
		cbVegan.setChecked(Boolean.parseBoolean(prefs.get("vegan")));
		cbFarmToFork.setChecked(Boolean.parseBoolean(prefs.get("farm_to_fork")));
		cbMadeWithoutGlutun.setChecked(Boolean.parseBoolean(prefs.get("made_without_glutun")));

	}
	
	public void onSavePreference(View v) {
		Log.d("DEBUG", "user clicked save preferences");
		
		// Get the relative positions
		Spinner spDefaultCafe = (Spinner) findViewById(R.id.spDefaultCafe);
		CheckBox cbVegetarian = (CheckBox) findViewById(R.id.cb_vegetarian);
		CheckBox cbVegan = (CheckBox) findViewById(R.id.cb_vegan);
		CheckBox cbFarmToFork = (CheckBox) findViewById(R.id.cb_farm_to_fork);
		CheckBox cbMadeWithoutGlutun = (CheckBox) findViewById(R.id.cb_made_without_gluten);
			
		// TODO: I could  move all this to ImageSearchSettings, have to pass in a Map<String,String>
	    SharedPreferences settings = getSharedPreferences("YaFood", MODE_PRIVATE);
	    SharedPreferences.Editor ed = settings.edit();
	    ed.putString("defaultCafe", spDefaultCafe.getSelectedItem().toString());
	    ed.putString("vegetarian", Boolean.toString(cbVegetarian.isChecked()));
	    ed.putString("vegan", Boolean.toString(cbVegan.isChecked()));
	    ed.putString("farm_to_fork", Boolean.toString(cbFarmToFork.isChecked()));
	    ed.putString("made_without_glutun", Boolean.toString(cbMadeWithoutGlutun.isChecked()));

	    ed.commit();
	    
	    finishActivity(OverviewActivity.PREFERENCE_SAVED);
	    finish();
	    
	    Log.d("DEBUG", settings.toString());
	}

	public void loadSpinner(String prefKeyName, int res, int resArray) {
		// Populate Image Type
		Spinner spinner = (Spinner) findViewById(res);
		
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = 
				ArrayAdapter.createFromResource
					(
							this,
							resArray, 
							android.R.layout.simple_spinner_item
							);
		
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


		Map<String,String> prefs = YaFoodPreferences.loadPreferences(this);
		
		if (prefs == null) {
			return;
		}
		
		// Apply the adapter to the spinner		
		spinner.setAdapter(adapter);
		for(int i=0; i < adapter.getCount(); i++) {
			if (prefs != null  && prefs.get(prefKeyName) != null) {	
				if (prefs.get(prefKeyName).trim().equals(adapter.getItem(i).toString())) {
					spinner.setSelection(i);
				}
			}
		}
		
	}	

}
