package dk.iha.carpooling;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Main extends Activity {
	
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main_view);
	}
	
	public void navigateTo(View view)
	{
		switch (view.getId())
		{
		case R.id.createRouteBtn:
			Intent intent = new Intent();
	        intent.setClass(this, CreateRoute.class);
	        
	        startActivityForResult(intent, 1);
			
			break;

		default:
			Toast.makeText(getApplicationContext(), "Method not implemented jet :(", Toast.LENGTH_SHORT).show();
			
			break;
		}
		
		
	}
}
