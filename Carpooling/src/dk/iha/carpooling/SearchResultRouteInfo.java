package dk.iha.carpooling;

import dk.iha.carpooling.data.Route;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SearchResultRouteInfo extends Activity {
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.search_results_route_info_view);
		
		Intent i = getIntent();
		
		Bundle b = i.getExtras();
		Route r = (Route) b.get("data");
		
		((TextView) findViewById(R.id.seach_results_route_info_from)).setText(": " + r.from);
		((TextView) findViewById(R.id.seach_results_route_info_destination)).setText(": " + r.destination);
		((TextView) findViewById(R.id.seach_results_route_info_date)).setText(": " + r.date);
		((TextView) findViewById(R.id.seach_results_route_info_driver)).setText(": " + r.driver);
		((TextView) findViewById(R.id.seach_results_route_info_price)).setText(": " + r.price);
		((TextView) findViewById(R.id.seach_results_route_info_time)).setText(": " + r.time);
	}
}
