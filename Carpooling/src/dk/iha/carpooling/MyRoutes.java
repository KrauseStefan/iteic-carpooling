package dk.iha.carpooling;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MyRoutes extends Activity {
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_routes_view);
	}
	
	public void startNavigation(View v)
	{
		Intent intent = new Intent();
        intent.setClass(this, Ruting.class);	            
        startActivity(intent);
	}
}
