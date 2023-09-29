package leitura;
//line example 
//id,city,state,date_time,stats,report_link,text,city_latitude,city_longitude

// Class UFO
public class Ufo {
//--> ATRIBUTOS
	private int id;
	private	String city;
	private char[] state;
	private String date; //mudar para date
	private String stats;
	private String report_link;
	private String text;
	private float city_latitude;
	private float city_longitude;

//--> CONSTRUTOR
	protected Ufo () {
		
	}
	protected Ufo (String[] data) {
		if (data[0].length() == 0)
			throw new AssertionError();
		else
			setId(Integer.parseInt(data[0]));
		
		setCity(data[1]);
		setState(data[2]);
		setDate(data[3]);
		setStats(data[4]);
		setReportLink(data[5]);
		setText(data[6]);
		if (data[7] == null || data[7].length() == 0) 
			setCityLatitude(0);
		else {
			try {
				setCityLatitude(Float.parseFloat(data[7]));
			} catch (NumberFormatException e) {
				setCityLatitude(0);
			}
		}
		if (data[8] == null || data[8].length() == 0)
			setCityLongitude(0);
		else {
			try {
				setCityLongitude(Float.parseFloat(data[8]));
			} catch (NumberFormatException e) {
				setCityLongitude(0);
			}
		}
	}
//--> SETTERS & GETTERS
	protected void setId (int id) {
		this.id = id;
	}
	protected int getId () {
		return id;
	}
	protected void setCity (String city) {
		this.city = city;
	}
	protected String getCity () {
		return city;
	}
	protected void setState (String state) {
		if (state == null || state.length() != 2) {
			this.state = null;
		} else {
			this.state = new char[2];
			this.state = state.toCharArray();
		}
	}
	protected char[] getState () {
		return state;
	}
	protected void setDate (String date) { //mudar para date
		this.date = date;
	}
	protected String getDate () {
		return date;
	}
	protected void setStats (String stats) {
		// using a function that returns a pattern in given string
		Pattern pattern = Pattern.compile("Occurred : (.+?) Reported: (.+?) Posted: (.+?) Location: (.+?) Shape: (.+?) Duration:(.+) seconds");
		Matcher matcher = pattern.matcher(text);

		if (matcher.find()) {
			try {
				String occurred = convertEmptyToNull(matcher.group(1));
				String reported = convertEmptyToNull(matcher.group(2));
				String posted = convertEmptyToNull(matcher.group(3));
				String location = convertEmptyToNull(matcher.group(4));
				String shape = convertEmptyToNull(matcher.group(5));
				String duration = convertEmptyToNull(matcher.group(6));

				String[] newText = {occurred, reported, posted, location, shape, duration};
				this.stats = newText;
			} catch (Exception e) {
				System.out.println(e);
			}
		}else {
            System.out.println("Error in format.");
        }
	}
	protected String getStats () {
		return stats;
	}
	protected void setReportLink (String report_link) {
		this.report_link = report_link;
	}
	protected String getReportLink () {
		return report_link;
	}
	protected void setText (String text) {
		this.text = text;
	}
	protected String getText () {
		return text;
	}
	protected void setCityLatitude (float city_latitude) {
		this.city_latitude = city_latitude;
	}
	protected float getCityLatitude () {
		return city_latitude;
	}	
	protected void setCityLongitude (float city_longitude) {
		this.city_longitude = city_longitude;
	}
	protected float getCityLongitude () {
		return city_longitude;
	}
//--> PRINT
	// function that prints the object UFO
	public void printUfo () {
		System.out.println("ID: " + getId());
		System.out.println("Date: " + getDate());
		System.out.print("City: " + getCity());
		System.out.println(getState() == null ? " State: null" : " State: " + String.valueOf(getState()));
		System.out.println("City latitude: " + getCityLatitude() + " City longitude: " + getCityLongitude());
		System.out.println("Report link: " + getReportLink());
		System.out.println();
		System.out.println("Text: ");
		System.out.println(getText());
		System.out.println();
		System.out.println("Stats: " + getStats());
		System.out.println();
	}

}//END_UFO
