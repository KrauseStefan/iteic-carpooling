package dk.iha.carpooling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dk.iha.carpooling.data.Route;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class SearchForRoute extends Activity implements OnItemClickListener {

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
		list.setOnItemClickListener(this);
		list.setAdapter(new SimpleAdapter(this, routeList,
				R.layout.route_list_layout, from, toIds));

	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		Intent i = new Intent(this, SearchResultRouteInfo.class);

		startActivity(i);
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

}
