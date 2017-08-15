package inputs;

import java.util.HashMap;
import java.util.List;

public class Context {
	Strategy c;

	public Context( Strategy c )
	{
		this.c = c;
	}

	public void setStrategy(Strategy c) {
		this.c = c;
	}
	
	public Object openConnection(){
		return c.openConnection();
	}
	
	public List<HashMap<String,Object>> read(){
		return c.readFile();
	}
	
	public Object closeConnection(){
		return c.closeConnection();
	}
	
}