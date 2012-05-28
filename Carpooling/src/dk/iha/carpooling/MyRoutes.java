package dk.iha.carpooling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MyRoutes extends Activity {
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_routes_view);

		String[] keys = { "name" };
		int[] layoutIds = {R.id.my_routes_list_item};

		HashMap<String, String> val1 = new HashMap<String, String>();
		HashMap<String, String> val2 = new HashMap<String, String>();
		HashMap<String, String> val3 = new HashMap<String, String>();

		val1.put(keys[0], "Aarhus -> København");
		val2.put(keys[0], "København -> Herning");
		val3.put(keys[0], "Herning -> Aarhus");

		routes.add(val1);
		routes.add(val2);
		routes.add(val3);

		ListView lv = (ListView) findViewById(R.id.my_routes_list);
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,

			int position, long id) {
				startNavigation(view);
			}
		});

		lv.setAdapter(new SimpleAdapter(this, routes, R.layout.my_routes_list_item_view, keys, layoutIds));
	}

	static final List<HashMap<String, String>> routes = new ArrayList<HashMap<String, String>>();

	// static final String[] routes = new String[] {
	// "Aarhus -> København", "København -> Herning", "Herning -> Aarhus"
	// };

	public void startNavigation(View v) {
		Intent intent = new Intent();
		intent.setClass(this, Ruting.class);
		startActivity(intent);
	}
}
