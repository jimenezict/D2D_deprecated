package inputs;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class CSVStrategy implements Strategy{
	
	private String fileName;

	public CSVStrategy(String fileName){
		super();
		this.fileName = fileName;		
	}
	
	@Override
	public Object openConnection() {
		if(fileName == null || !booleanIsValidName()){
			throw new IllegalArgumentException();
		}
		return null;
	}

	@Override
	public List<HashMap<String, Object>> readFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object closeConnection() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private boolean booleanIsValidName(){
		fileName = fileName.trim();
		if(fileName.length() <= 4){
			return false;
		}else if(!fileName.substring(fileName.length() - 4).equals(".csv")){
			return false;
		}
		return true;
	}

}
