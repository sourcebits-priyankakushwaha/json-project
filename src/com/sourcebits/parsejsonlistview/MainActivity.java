package com.sourcebits.parsejsonlistview;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends ListActivity {

	final String strJson = "{\"items\":[{\"id\":\"A19\",\"name\":\"Extra Beans\",\"addr\":\"15 Main St\",\"hours\":{ \"monday\":\"closed\",\"tuesday\":\"9 AM to 5 PM\", \"wednesday\":\"9 AM to 6 PM\",\"thursday\":\"9 AM to 8 PM\",\"friday\":\"8 AM to 8 PM\", \"saturday\":\"noon to 5 PM\",\"sunday\":\"closed\"},{\"id\":\"B24\",\"name\":\"Tamales Adventure\",\"addr\":\"54 Queen St\",\"hours\":{ \"monday\":\"closed\",\"tuesday\":\"9 AM to 5 PM\", \"wednesday\":\"9 AM to 6 PM\",\"thursday\":\"9 AM to 8 PM\",\"friday\":\"8 AM to 8 PM\", \"saturday\":\"noon to 5 PM\",\"sunday\":\"closed\"},{\"id\":\"C73\",\"name\":\"Soup Explosion\",\"addr\":\"99 King St\",\"hours\":{ \"monday\":\"closed\",\"tuesday\":\"9 AM to 5 PM\", \"wednesday\":\"9 AM to 6 PM\",\"thursday\":\"9 AM to 8 PM\",\"friday\":\"8 AM to 8 PM\", \"saturday\":\"noon to 5 PM\",\"sunday\":\"closed\"},{\"id\":\"E87\",\"name\":\"Candy Party\",\"addr\":\"654 Eastern Ave\",\"hours\":{ \"monday\":\"closed\",\"tuesday\":\"9 AM to 5 PM\", \"wednesday\":\"9 AM to 6 PM\",\"thursday\":\"9 AM to 8 PM\",\"friday\":\"8 AM to 8 PM\", \"saturday\":\"noon to 5 PM\",\"sunday\":\"closed\"}] }";

	private static final String TAG_ITEMS = "items";
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "name";
	private static final String TAG_ADDRESS = "addr";
	private static final String TAG_HOURS = "hours";
	private static final String TAG_HOURS_MONDAY = "monday";
	private static final String TAG_HOURS_TUESDAY = "tuesday";
	private static final String TAG_HOURS_WEDNESDAY = "wednesday";
	private static final String TAG_HOURS_THURSDAY = "thursday";
	private static final String TAG_HOURS_FRIDAY = "friday";
	private static final String TAG_HOURS_SATURDAY = "saturday";
	private static final String TAG_HOURS_SUNDAY = "sunday";

	// Hashmap for ListView
	ArrayList<HashMap<String, String>> itemList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initList();

		ListView listView = (ListView) findViewById(android.R.id.list);
		// adapter
		ListAdapter adapter = new SimpleAdapter(MainActivity.this, itemList, R.layout.list_item,
				new String[] { TAG_ID, TAG_NAME, TAG_ADDRESS, TAG_HOURS_MONDAY },
				new int[] { R.id.itemId, R.id.itemName, R.id.itemAddress, R.id.itemDay });
		setListAdapter(adapter);
	}

	private void initList() {
		JSONObject jsonResponse;

		try {

			/******
			 * Creates a new JSONObject with name/value mappings from the JSON
			 * string.
			 ********/
			jsonResponse = new JSONObject(strJson);

			/*****
			 * Returns the value mapped by name if it exists and is a JSONArray.
			 * Returns null otherwise.
			 *******/
			JSONArray jsonMainNode = jsonResponse.optJSONArray("items");

			/*********** Process each JSON Node ************/
			int lengthJsonArr = jsonMainNode.length();
			for (int i = 0; i < lengthJsonArr; i++) {
				/****** Get Object for each JSON node. ***********/
				JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);

				/******* Fetch node values **********/
				String id = jsonChildNode.optString("id").toString();
				String name = jsonChildNode.optString("name").toString();
				String addr = jsonChildNode.optString("addr").toString();

				/******* Fetch hours values **********/
				JSONObject jsonGrandChildNode = jsonChildNode.getJSONObject(TAG_HOURS);
				String monday = jsonGrandChildNode.optString("TAG_HOURS_MONDAY").toString();
				String tuesday = jsonGrandChildNode.optString("TAG_HOURS_TUESDAY").toString();
				String wednesday = jsonGrandChildNode.optString("AG_HOURS_WEDNESDAY").toString();
				String thursday = jsonGrandChildNode.optString("TAG_HOURS_THURSDAY ").toString();
				String friday = jsonGrandChildNode.optString("TAG_HOURS_FRIDAY").toString();
				String saturday = jsonGrandChildNode.optString("TAG_HOURS_SATURDAY").toString();
				String sunday = jsonGrandChildNode.optString("TAG_HOURS_SUNDAY").toString();
				// Log.i(sunday, sunday);

				/******* Hash map for single item **********/
				HashMap<String, String> item = new HashMap<String, String>();
				item.put(TAG_ID, id);
				item.put(TAG_NAME, name);
				item.put(TAG_ADDRESS, addr);
				item.put(TAG_HOURS_MONDAY, "monday");
				itemList.add(item);

			}

		} catch (JSONException e) {

			e.printStackTrace();
		}

	}

}
