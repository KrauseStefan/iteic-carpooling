package dk.iha.carpooling.data;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class Route implements Parcelable{

	
	private Route(Parcel in){
		ArrayList<String> l = new ArrayList<String>();
		in.readStringList(l);
		
		this.from = l.get(0);
		this.destination = l.get(1);
		this.date = l.get(2);
		this.driver = l.get(3);
		this.time = l.get(4);
		this.price = l.get(5);
		
	}
	
	public Route(String from, String destination, String driver, String time,
			String date, String price) {
		this.from = from;
		this.destination = destination;
		this.date = date;
		this.driver = driver;
		this.time = time;
		this.price = price;

	}

	public String from;
	public String destination;
	public String date;
	public String driver;
	public String time;
	public String price;
	
	public int describeContents() {
		return 0;
	}
	
	public void writeToParcel(Parcel out, int flags) {
		List<String> strings = new ArrayList<String>();
		strings.add(from);
		strings.add(destination);
		strings.add(date);
		strings.add(driver);
		strings.add(time);
		strings.add(price);
		
		out.writeStringList(strings);		
	}

	public static final Parcelable.Creator<Route> CREATOR = new Creator<Route>() {
		
		public Route createFromParcel(Parcel source) {
			return new Route(source);
		}

		public Route[] newArray(int size) {
			return new Route[size];
		}
		
	};
	
}
