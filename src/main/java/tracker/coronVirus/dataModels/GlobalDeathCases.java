package tracker.coronVirus.dataModels;

public class GlobalDeathCases {
	private String Province_State;
	private String Country_Region;
	private String deathCases;

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

	public String getDeathCases() {
		return deathCases;
	}

	public void setDeathCases(String deathCases) {
		this.deathCases = deathCases;
	}

	@Override
	public String toString() {
		return "GlobalDeathCases [Province_State=" + Province_State + ", Country_Region=" + Country_Region
				+ ", deathCases=" + deathCases + "]";
	}

}
