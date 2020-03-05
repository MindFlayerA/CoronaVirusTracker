package tracker.coronVirus.dataService;

import java.io.IOException;
import java.io.StringReader;

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

@Service
public class CoronaVirusDataService {

	private static final String url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/03-03-2020.csv";

	@PostConstruct
	@Scheduled(cron = "1 * * * * *")
	public String getCurrentData() {
		try {

			CloseableHttpClient client = HttpClientBuilder.create().build();
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpResponse response = client.execute(httpGet);
			System.out.println(response.getStatusLine().toString());
			HttpEntity entity = response.getEntity();

			StringReader in = new StringReader(EntityUtils.toString(entity));
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader("Province/State", "Country/Region").parse(in);
			for (CSVRecord record : records) {
				System.out.println(record.get("Country/Region"));
				System.out.println(record.get("Province/State"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

}
