package dk.iha.carpooling;

import android.app.Activity;
import android.os.Bundle;

public class UserProfile extends Activity {
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.user_profile_view);

	}
}
