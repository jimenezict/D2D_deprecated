package inputs;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.supercsv.prefs.CsvPreference;

public class ContextFacade {
	private Context context;
	
	public void ContextCSV(String fileName){
		context = new Context(new CSVStrategy(fileName));
	}
	
	public void ContextCSVSemiColon(String fileName){
		CSVStrategy semiColonStrategy = new CSVStrategy(fileName);
		semiColonStrategy.setCsvPreference(CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
		context = new Context(semiColonStrategy);
	}
	
	public void ContextWS(String url, Type resultObject){
		context = new Context(new WebServiceStrategy(url,resultObject));
	}
	
	public List<HashMap<String, String>> extractData(){
		Object connection = context.openConnection();
		List<HashMap<String, String>> results = context.read(connection);
		context.closeConnection(connection);
		return results;
	}
	
	public List<String> exploreHeaders(){
		Object connection = context.openConnection();
		List<HashMap<String, String>> results = context.read(connection);
		context.closeConnection(connection);
		if(results.size() > 0){
			List<String> returningList = new ArrayList<String>(results.get(0).keySet());
			return (List<String>) returningList;
		}
			
		return null;
	}
}
