package tracker.coronaVirus.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tracker.coronVirus.dataModels.Calculator;
import tracker.coronVirus.dataModels.ConfirmedCases;
import tracker.coronVirus.dataModels.GlobalDeathCases;
import tracker.coronVirus.dataModels.GlobalRecovered;
import tracker.coronVirus.dataService.CoronaVirusDataService;

@Controller
public class CoronaController {

	private CoronaVirusDataService coronVirusDataService;
	private Calculator<?> calculator;

	int sum = 0, confirmedSum = 0;

	@Autowired
	public CoronaController(CoronaVirusDataService coronVirusDataService, Calculator<?> calculator) {
		this.coronVirusDataService = coronVirusDataService;
		this.calculator = calculator;
	}

	@GetMapping("/")
	public String home(Model mv) {
		calculator.getFieldSum(coronVirusDataService.getCurrentConfirmedCases(), mv);
		calculator.getFieldSum(coronVirusDataService.getCurrentDeathCases(), mv);
		calculator.getFieldSum(coronVirusDataService.getCurrentRecoveredCases(), mv);
		return "index.html";
	}
	
	@ResponseBody
	@GetMapping("/getJson/confirmed")
	public ResponseEntity<List<ConfirmedCases>> raw() {
		return new ResponseEntity<List<ConfirmedCases>>(coronVirusDataService.getCurrentConfirmedCases(), HttpStatus.ACCEPTED);
	}
	
	@ResponseBody
	@GetMapping("/getJson/death")
	public ResponseEntity<List<GlobalDeathCases>> deathRaw(){
		return new ResponseEntity<List<GlobalDeathCases>>(coronVirusDataService.getCurrentDeathCases(), HttpStatus.ACCEPTED);
	}
	
	@ResponseBody
	@GetMapping("/getJson/recovered")
	public ResponseEntity<List<GlobalRecovered>> recovered(){
		return new ResponseEntity<List<GlobalRecovered>>(coronVirusDataService.getCurrentRecoveredCases(), HttpStatus.ACCEPTED);
	}
	
	@ResponseBody
	@GetMapping(value="/getJson/unfiltered/all", produces= {MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE})
	public List<String> returnRecord(){
		return coronVirusDataService.getMainRaw();
	}
}
