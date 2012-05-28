package dk.iha.carpooling.data;

public class Route {

	
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
	public String driver;
	public String time;
	public String date;
	public String price;

}
