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
		Intent intent = new Intent();
		
		switch (view.getId())
		{
		case R.id.createRouteBtn:
	        intent.setClass(this, CreateRoute.class);
	        
	        startActivityForResult(intent, view.getId());
			
			break;
			
		case R.id.searchRouteBtn:
	        intent.setClass(this, SearchForRoute.class);
	        
	        startActivityForResult(intent, view.getId());
			
			break;
			
		case R.id.myRoutesBtn:
	        intent.setClass(this, MyRoutes.class);
	        
	        startActivityForResult(intent, view.getId());
			
			break;

		default:
			Toast.makeText(getApplicationContext(), "Method not implemented jet :(", Toast.LENGTH_SHORT).show();
			
			break;
		}
		
		
	}
}
