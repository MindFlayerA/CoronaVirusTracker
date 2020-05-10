package tracker.coronVirus.dataModels;

public class RecoveryDateWise {
	private String latitude;
	private String longitude;
	private String date;
	private String recovered;

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getRecovered() {
		return recovered;
	}

	public void setRecovered(String recovered) {
		this.recovered = recovered;
	}

	@Override
	public String toString() {
		return String.format("RecoveryDateWise [latitude=%s, longitude=%s, date=%s, recovered=%s]", latitude, longitude,
				date, recovered);
	}

}
