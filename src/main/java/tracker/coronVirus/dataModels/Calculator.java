package tracker.coronVirus.dataModels;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class Calculator<T> implements Comparator<Object> {
	
	public Calculator() {
	}

	private static int countDeath = 0;
	private static int confirmed = 0;
	private static int totalrecovered = 0;

	public boolean getFieldSum(List<?> list, Model model) {
		return getSum(list, model);
	}

	@SuppressWarnings("unchecked")
	private boolean getSum(List<?> list, Model m) {
		Object data=list.get(0);
		
		if(data instanceof ConfirmedCases) {
			confirmed = list.stream().mapToInt((i)->{
				ConfirmedCases cases = (ConfirmedCases)i;
				return Integer.valueOf(cases.getConfirmedCases());
			}).sum();
			m.addAttribute("confirmedData", (List<ConfirmedCases>) list);
			m.addAttribute("confirmed", confirmed);
		}
		if(data instanceof GlobalDeathCases) {
			countDeath=list.stream().mapToInt((i)->{
				GlobalDeathCases cases= (GlobalDeathCases)i;
				return Integer.valueOf(cases.getDeathCases());
			}).sum();
			m.addAttribute("deathData", (List<GlobalDeathCases>) list);
			m.addAttribute("countDeath", countDeath);
		}
		if(data instanceof GlobalRecovered) {
			totalrecovered=list.stream().mapToInt((i)->{
				GlobalRecovered cases= (GlobalRecovered)i;
				return Integer.valueOf(cases.getRecoveredCases());
			}).sum();
			m.addAttribute("recoveredData", (List<GlobalRecovered>) list);
			m.addAttribute("totalrecovered", totalrecovered);
		}
		
		return false;
	}

	@Override
	public int compare(Object arg0, Object arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

}
