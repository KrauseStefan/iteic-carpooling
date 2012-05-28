package dk.iha.carpooling;

import java.text.AttributedCharacterIterator.Attribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import dk.iha.carpooling.data.Route;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class SearchForRoute extends Activity {

	ListView list = null;

	List<HashMap<String, String>> routeList = new ArrayList<HashMap<String, String>>();

	ArrayList<Route> routes = new ArrayList<Route>();

	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.search_for_route_view);

		String[] from = { "from", "destination", "driver", "time", "date" };
		int[] toIds = { R.id.routeView_from, R.id.routeView_destination,
				R.id.routeView_driver, R.id.routeView_time, R.id.routeView_date };

		makeDummyData();
		makeDummyData();
		makeDummyData();

		for (int i = 0; i < routes.size(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();

			map.put(from[0], routes.get(i).from);
			map.put(from[1], routes.get(i).destination);
			map.put(from[2], routes.get(i).driver);
			map.put(from[3], routes.get(i).time);
			map.put(from[4], routes.get(i).date);

			routeList.add(map);
		}
		
		list = (ListView) findViewById(R.id.src_results_ListView);
		list.setAdapter(new SimpleAdapter(this, routeList,
				R.layout.route_list_layout, from, toIds));

	}

	private void makeDummyData() {
		routes.add(new Route("Århus", "København", "Tommy", "20:30", "12/6",
				"50kr."));
		routes.add(new Route("Skive", "Kastrup", "Louise", "10:30", "12/6",
				"50kr."));
		routes.add(new Route("Horsens", "Ålborg", "Mads", "12:50", "12/6",
				"50kr."));
		routes.add(new Route("Thisted", "Esbjerg", "Andrea", "14:00", "12/6",
				"50kr."));
		routes.add(new Route("Århus", "Skagen", "Henrik", "10:55", "13/6",
				"50kr."));
		routes.add(new Route("Viby J", "Fredericia", "Tom", "10:30", "13/6",
				"50kr."));
		routes.add(new Route("Alborg", "Odense", "Mia", "19:00", "13/6",
				"50kr."));
		routes.add(new Route("Århus", "København", "Mads", "20:00", "13/6",
				"50kr."));
		routes.add(new Route("Århus", "Esbjerg", "Lily", "23:30", "13/6",
				"50kr."));
	}
	//
	// public int getCount() {
	// return routes.size();
	// }
	//
	// public Object getItem(int index) {
	// return routes.get(index);
	// }
	//
	// public long getItemId(int index) {
	// return index;
	// }
	//
	// public int getItemViewType(int arg0) {
	//
	// return IGNORE_ITEM_VIEW_TYPE;
	// }
	//
	// public View getView(int index, View convertView, ViewGroup parent) {
	// LinearLayout baseLayout = (LinearLayout) convertView;
	//
	//
	// if (baseLayout == null) {
	// baseLayout = new LinearLayout(this.getBaseContext());
	// LinearLayout rightLayout = new LinearLayout(this.getBaseContext());
	// rightLayout.setGravity(Gravity.RIGHT);
	//
	// TextView fromLabel = new TextView(this.getBaseContext());
	// TextView destinationLabel = new TextView(this.getBaseContext());
	// TextView driverLabel = new TextView(this.getBaseContext());
	// TextView timeLabel = new TextView(this.getBaseContext());
	// TextView dateLabel = new TextView(this.getBaseContext());
	//
	// TextView from = new TextView(this.getBaseContext());
	// TextView destination = new TextView(this.getBaseContext());
	// TextView driver = new TextView(this.getBaseContext());
	// TextView time = new TextView(this.getBaseContext());
	// TextView date = new TextView(this.getBaseContext());
	//
	// from.setWidth(115);
	// destination.setWidth(120);
	// driver.setWidth(100);
	//
	// from.setPadding(5, 5, 5, 5);
	// destination.setPadding(5, 5, 5, 5);
	// driver.setPadding(5, 5, 5, 5);
	// time.setPadding(5, 5, 5, 5);
	// date.setPadding(5, 5, 5, 5);
	//
	// fromLabel.setText("From");
	// destinationLabel.setText("Destination");
	// driverLabel.setText("Driver");
	// timeLabel.setText("Time");
	// dateLabel.setText("Date");
	//
	// from.setText(routes.get(index).from);
	// destination.setText(routes.get(index).destination);
	// driver.setText(routes.get(index).driver);
	// time.setText(routes.get(index).time);
	// date.setText(routes.get(index).date);
	//
	// baseLayout.addView(from);
	// baseLayout.addView(destination);
	// baseLayout.addView(driver);
	//
	// rightLayout.addView(time);
	// rightLayout.addView(date);
	//
	// baseLayout.addView(rightLayout);
	//
	// }
	//
	// return baseLayout;
	// }
	//
	// public int getViewTypeCount() {
	// return 1;
	// }
	//
	// public boolean hasStableIds() {
	// return true;
	// }
	//
	// public boolean isEmpty() {
	// return routes.isEmpty();
	// }
	//
	// public void registerDataSetObserver(DataSetObserver arg0) {
	// }
	//
	// public void unregisterDataSetObserver(DataSetObserver arg0) {
	// }
	//
	// public boolean areAllItemsEnabled() {
	// return true;
	// }
	//
	// public boolean isEnabled(int position) {
	// return true;
	// }

}
