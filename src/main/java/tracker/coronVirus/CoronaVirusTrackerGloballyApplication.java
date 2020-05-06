package tracker.coronVirus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = { "tracker.coronVirus.dataService", "tracker.coronaVirus.Controller",
		"tracker.coronVirus.dataModels" })
public class CoronaVirusTrackerGloballyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronaVirusTrackerGloballyApplication.class, args);
	}

}
