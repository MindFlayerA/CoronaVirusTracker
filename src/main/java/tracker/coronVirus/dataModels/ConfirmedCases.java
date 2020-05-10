package tracker.coronVirus.dataModels;

import org.springframework.stereotype.Component;

@Component
public class ConfirmedCases {
	private String Province_State;
	private String Country_Region;
	private String confirmedCases;

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

	public String getConfirmedCases() {
		return confirmedCases;
	}

	public void setConfirmedCases(String confirmedCases) {
		this.confirmedCases = confirmedCases;
	}

	@Override
	public String toString() {
		return "ConfirmedCases [Province_State=" + Province_State + ", Country_Region=" + Country_Region
				+ ", confirmedCases=" + confirmedCases + "]";
	}

}
