package com.tonyandollie.yafood;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DayPartArrayAdpater extends ArrayAdapter<DayPart> {

	public DayPartArrayAdpater(Context context, ArrayList<DayPart> dayParts) {
		super(context, R.layout.dayparts_list_item, dayParts);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view;
		
		if (convertView == null ) {
		
			LayoutInflater inflater  = LayoutInflater.from(getContext());
			view = (View) inflater.inflate(
				R.layout.dayparts_list_item,
				parent,
				false
			);
		
		} else  {
			view = (View) convertView;
		}
		
		DayPart dp = getItem(position);
		
		TextView name = (TextView) view.findViewById(R.id.dayPartName);
		name.setText(dp.getName());
		
		ArrayList<Station> stations = dp.getStations();
		StationListArrayAdapter stationsAdapter = new StationListArrayAdapter(getContext(), stations);
		
		ListView stationsLv = (ListView) view.findViewById(R.id.stationsList);
		stationsLv.setAdapter(stationsAdapter);
		
		return view;

	}
	
}
