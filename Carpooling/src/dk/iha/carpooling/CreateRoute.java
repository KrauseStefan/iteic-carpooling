package dk.iha.carpooling;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CreateRoute extends Activity {
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.create_route_view);
		
		ImageView ll = (ImageView) findViewById(R.id.Maps);
		
		Display display = getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int height =  display.getHeight();
		height = height*3/5;
		
		
		ll.setImageBitmap(getMap(height,width));
	}
	
	
	
	private Bitmap getMap(int height, int length)
	{
		try
		{
			URL url = new URL("http://maps.googleapis.com/maps/api/staticmap?size="+length+"x"+height+"&maptype=roadmap&markers=color:red|label:A|"+URLEncoder.encode(getIntent().getStringArrayExtra("points")[0])+"&markers=color:red|label:B|"+URLEncoder.encode(getIntent().getStringArrayExtra("points")[1])+"&sensor=false");
	        URLConnection conn = url.openConnection();
	        conn.connect();
	        InputStream is = conn.getInputStream();
	
	
	        BufferedInputStream bis = new BufferedInputStream(is);
	
	        Bitmap bm = BitmapFactory.decodeStream(bis);
	
	        bis.close();
	        is.close();

	        
	        return bm;
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
			
		}
	}
}
