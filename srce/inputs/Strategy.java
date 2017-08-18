package inputs;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

public interface Strategy {

	Object openConnection();
	
	List<HashMap<String, String>> readFile(Object connection);

	void closeConnection(Object connection);

}
