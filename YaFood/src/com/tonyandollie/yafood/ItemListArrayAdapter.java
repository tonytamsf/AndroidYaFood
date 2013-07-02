package com.tonyandollie.yafood;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ItemListArrayAdapter extends ArrayAdapter<Item> {

	public ItemListArrayAdapter(Context context, ArrayList<Item> items) {
		super(context, R.layout.fragment_item, items);
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
					R.layout.fragment_item,
					parent,
					false
					);
		} else  {
			view = (View) convertView;
		}
		
		Item i = getItem(position);

		TextView name = (TextView) view.findViewById(R.id.itemTitle);
		name.setText(i.getTitle());
		
		TextView desc = (TextView) view.findViewById(R.id.itemDesc);
		desc.setText(i.getDesc());
		
//		ArrayList<Item> items = s.getItems();
//		ItemListArrayAdapter itemsAdapter = new ItemListArrayAdapter(getContext(), items);
//		
//		ListView lvItems = (ListView) view.findViewById(R.id.lvItems);
//		lvItems.setAdapter(itemsAdapter);

		return view;
	}

}
