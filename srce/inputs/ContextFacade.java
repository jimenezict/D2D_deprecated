package inputs;

import java.util.HashMap;
import java.util.List;

public class ContextFacade {
	private Context context;
	
	public void ContextCSV(String fileName){
		context = new Context(new CSVStrategy(fileName));
	}
	
	public List<HashMap<String, String>> extractData(){
		Object connection = context.openConnection();
		List<HashMap<String, String>> results = context.read(connection);
		context.closeConnection(connection);
		return results;
	}
}
