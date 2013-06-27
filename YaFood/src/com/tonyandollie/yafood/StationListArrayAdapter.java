package com.tonyandollie.yafood;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class StationListArrayAdapter extends ArrayAdapter<Station> {


	/**
	 * @param context
	 * @param textViewResourceId
	 */
	public StationListArrayAdapter(Context context, List<Station> stations) {
		super(context, R.layout.fragment_station, stations);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		if (convertView == null ) {
			LayoutInflater inflater  = LayoutInflater.from(getContext());
			view = (View) inflater.inflate(
					R.layout.fragment_station,
					parent,
					false
					);
		} else  {
			view = (View) convertView;
		}
		
		
		return view;
	}

	
}
