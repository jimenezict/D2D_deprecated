package inputs;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ContextFacade {
	private Context context;
	
	public void ContextCSV(String fileName){
		context = new Context(new CSVStrategy(fileName));
	}
	
	public void ContextWS(String url, Type type){
		context = new Context(new WebServiceStrategy(url,type));
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
