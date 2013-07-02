package com.tonyandollie.yafood;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class DayPartArrayAdpater extends ArrayAdapter<DayPart> {

	public DayPartArrayAdpater(Context context, ArrayList<DayPart> dayParts) {
		super(context, R.layout.fragment_dayparts, dayParts);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view;
		
		if (convertView == null ) {
		
			LayoutInflater inflater  = LayoutInflater.from(getContext());
			view = (View) inflater.inflate(
				R.layout.fragment_dayparts,
				parent,
				false
			);
		
		} else  {
			view = (View) convertView;
		}
		
		return view;

	}
	
}
