package tracker.coronVirus.dataModels;

public class GlobalRecovered {
	private String Province_State;
	private String Country_Region;
	private String recoveredCases;
	private String date;
	
	public String getProvince_State() {
		return Province_State;
	}
	public void setProvince_State(String province_State) {
		Province_State = province_State;
	}
	public String getCountry_Region() {
		return Country_Region;
	}
	public void setCountry_Region(String country_Region) {
		Country_Region = country_Region;
	}
	public String getRecoveredCases() {
		return recoveredCases;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setRecoveredCases(String recoveredCases) {
		this.recoveredCases = recoveredCases;
	}
	
	@Override
	public String toString() {
		return String.format("GlobalRecovered [Province_State=%s, Country_Region=%s, recoveredCases=%s]",
				Province_State, Country_Region, recoveredCases);
	}
	
}
