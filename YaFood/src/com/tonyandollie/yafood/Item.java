package com.tonyandollie.yafood;

import java.util.ArrayList;

import org.json.crockford.JSONArray;
import org.json.crockford.JSONObject;

public class Item extends BaseModel {
	
	private String itemTitle;
	private String itemDesc;
//	private ArrayList<Attribute> attributes;

	
    public static Item fromJson(JSONObject jsonObject) {
        Item item = new Item();
        item.jsonObject = jsonObject;

        item.itemTitle = jsonObject.getString("txtTitle");
        item.itemDesc = jsonObject.getString("txtDescription");

//    	JSONArray itemArray = new JSONArray("[\"tblItem\"]");
//		JSONArray itemsArray = jsonObject.toJSONArray(itemArray);
//    	item.items = Item.fromJson(itemsArray);

        return item;
    }
    
    public static ArrayList<Item> fromJson(JSONArray jsonArray) {
        ArrayList<Item> items = new ArrayList<Item>(jsonArray.length());

        for (int i=0; i < jsonArray.length(); i++) {
            JSONObject ItemJson = null;
            try {
                ItemJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            Item item = Item.fromJson(ItemJson);
            if (items != null) {
                items.add(item);
            }
        }

        return items;
    }    

    public String getTitle() {
    	return this.itemTitle;
    }
    
    public String getDesc() {
    	return this.itemDesc;
    }
    
//    public ArrayList<Item> getItems() {
//    	return this.items;
//    }
    
}
