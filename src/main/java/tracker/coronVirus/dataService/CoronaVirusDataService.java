package tracker.coronVirus.dataService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import tracker.coronVirus.dataModels.ConfirmedCases;
import tracker.coronVirus.dataModels.GlobalDeathCases;
import tracker.coronVirus.dataModels.GlobalRecovered;

@Service
public class CoronaVirusDataService {

	private static final String GLOBAL_CONFIRMED = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

	private static final String GLOBAL_DEATH = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";

	private static final String GLOBAL_RECOVERED = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";
	/*
	 * @Param cSVRecord This will keep every single record coming un-filtered
	 */
	private List<CSVRecord> cSVRecord;

	/*
	 * @Field privateStringField it's the main field of the CSVRecord Class we will
	 * use this to access the main value from the class
	 */
	private Field privateStringField;

	private String[] fieldValue;

	/*
	 * @Field returnValue this contain the actual value of ArrayList<String>
	 * Accessed value of value variable fro CSVRecord class
	 */
	List<String> returnValue = new ArrayList<>();

	@PostConstruct
	@Scheduled(cron = "0 0 */12 ? * *")
	public List<GlobalDeathCases> getCurrentDeathCases() {
		List<GlobalDeathCases> deathCases = new ArrayList<>();
		try {
			CloseableHttpClient client = HttpClientBuilder.create().build();
			HttpGet httpGet = new HttpGet(GLOBAL_DEATH);
			CloseableHttpResponse response = client.execute(httpGet);
			// System.out.println(response.getStatusLine().toString());
			HttpEntity entity = response.getEntity();

			StringReader in = new StringReader(EntityUtils.toString(entity));
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader("Province/State", "Country/Region").parse(in);

			for (CSVRecord record : records) {
				// System.out.println(record.get("Confirmed"));
				GlobalDeathCases data = new GlobalDeathCases();
				data.setCountry_Region(record.get("Country/Region"));
				data.setProvince_State(record.get("Province/State"));
				data.setDeathCases(record.get(record.size() - 1));
				deathCases.add(data);
				/* This will bring data of last column */
				// System.out.println(record.get(record.size()-1));

			}
			deathCases.remove(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return deathCases;
	}

	@PostConstruct
	@Scheduled(cron = "0 0 */12 ? * *")
	public List<ConfirmedCases> getCurrentConfirmedCases() {
		List<ConfirmedCases> confirmedCases = new ArrayList<>();
		try {
			CloseableHttpClient client = HttpClientBuilder.create().build();
			HttpGet httpGet = new HttpGet(GLOBAL_CONFIRMED);
			CloseableHttpResponse response = client.execute(httpGet);
			// System.out.println(response.getStatusLine().toString());
			HttpEntity entity = response.getEntity();

			StringReader in = new StringReader(EntityUtils.toString(entity));
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader("Province/State", "Country/Region").parse(in);

			for (CSVRecord record : records) {
				ConfirmedCases data = new ConfirmedCases();
				data.setCountry_Region(record.get("Country/Region"));
				data.setProvince_State(record.get("Province/State"));
				data.setConfirmedCases(record.get(record.size() - 1));
				confirmedCases.add(data);
				/* This will bring data of last column */
				// System.out.println(record.get(record.size()-1));

			}
			confirmedCases.remove(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return confirmedCases;
	}

	@PostConstruct
	@Scheduled(cron = "0 0 */12 ? * *")
	public List<GlobalRecovered> getCurrentRecoveredCases() {
		List<GlobalRecovered> recoveredCases = new ArrayList<>();
		try {
			CloseableHttpClient client = HttpClientBuilder.create().build();
			HttpGet httpGet = new HttpGet(GLOBAL_RECOVERED);
			CloseableHttpResponse response = client.execute(httpGet);
			// System.out.println(response.getStatusLine().toString());
			HttpEntity entity = response.getEntity();

			StringReader in = new StringReader(EntityUtils.toString(entity));
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader("Province/State", "Country/Region").parse(in);

			cSVRecord = new ArrayList<>();

			for (CSVRecord record : records) {
				GlobalRecovered data = new GlobalRecovered();
				// RecoveryDateWise recoveryDatewise = new RecoveryDateWise();
				data.setCountry_Region(record.get("Country/Region"));
				data.setProvince_State(record.get("Province/State"));
				data.setRecoveredCases(record.get(record.size() - 1));
				recoveredCases.add(data);
				cSVRecord.add(record);
				// recoveryDatewise.setRecovered(record.get(0));
				/* This will bring data of last column */
				// System.out.println(record.get(record.size()-1));
			}
			recoveredCases.remove(0);

			// Trying to access vales from the

		} catch (IOException e) {
			e.printStackTrace();
		}
		return recoveredCases;
	}

	private List<String> toJson(List<?> list, String location) {
		ObjectMapper objectMapper = new ObjectMapper();
		List<String> jsonValue = new ArrayList<String>();
		try {
			objectMapper.writeValue(new FileOutputStream(location), list.toString());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		list.forEach(record -> {
			try {
				jsonValue.add(objectMapper.writeValueAsString(record));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return jsonValue;
	}

	/*
	 * @Param list pass the list of ? type Objects to get the json representation of
	 * of that list. This method will internally calls toJson() which returns the
	 * List<String> which is in JSON representation.
	 */
	public List<String> JavaObjectIntoJson(List<?> list, String location) {
		return toJson(list, location);
	}

	/* For iteration */
	int i = 0;

	@PostConstruct
	private void getOriginalRecordList() {
		try {
			privateStringField = cSVRecord.get(1).getClass().getDeclaredField("values");
			privateStringField.setAccessible(true);

			cSVRecord.forEach((elem) -> {
				try {
					fieldValue = (String[]) privateStringField.get(cSVRecord.get(i));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
				returnValue.add(Arrays.toString(fieldValue));
				i++;
			});
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<String> getMainRaw(){
		return returnValue;
	}
	/*
	 * public static final Date getYesterdayDate() { Calendar cal =
	 * Calendar.getInstance(); DateFormat dateFormat = new
	 * SimpleDateFormat("M/dd/yy"); cal.add(Calendar.DATE, -1); return
	 * cal.getTime(); }
	 */

}
