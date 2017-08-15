package inputs;

import java.util.HashMap;
import java.util.List;

public interface Strategy {

	Object openConnection();
	
	List<HashMap<String, Object>> readFile();

	Object closeConnection();

}
