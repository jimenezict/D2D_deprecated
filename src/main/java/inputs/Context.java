package inputs;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

public class Context {
	Strategy c;

	public Context( Strategy c )
	{
		this.c = c;
	}
	
	public Object openConnection(){
		return c.openConnection();
	}
	
	public List<HashMap<String,String>> read(Object connection){
		return c.readFile(connection);
	}
	
	public void closeConnection(Object connection){
		c.closeConnection(connection);
	}
	
}