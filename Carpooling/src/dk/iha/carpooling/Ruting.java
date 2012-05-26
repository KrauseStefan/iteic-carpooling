package dk.iha.carpooling;

import android.os.Bundle;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class Ruting extends MapActivity {
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ruting_view);
		
		MapView mapView = (MapView) findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
