package inputs;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

public class CSVStrategy implements Strategy{
	
	private String fileName;
	private CsvPreference csvPreference;
	
	public CSVStrategy(String fileName){
		super();
		this.fileName = fileName;	
		csvPreference = CsvPreference.STANDARD_PREFERENCE;
	}
	
	@Override
	public Object openConnection() {
		if(fileName == null || !booleanIsValidName()){
			throw new IllegalArgumentException();
		}
		ICsvMapReader listReader = null;
		try {
			listReader = new CsvMapReader(new FileReader(fileName), csvPreference);
		} catch (FileNotFoundException e) {
			
		}
		return listReader;
	}

	@Override
	public List<HashMap<String, String>> readFile(Object connection) {
		ICsvMapReader listReader =(ICsvMapReader) connection;
		HashMap<String, String> fieldsInCurrentRow;
		ArrayList<HashMap<String, String>> fullCSVfile = new ArrayList<HashMap<String, String>>();
		try {
			String[] headers = listReader.getHeader(true);
			 while ((fieldsInCurrentRow = (HashMap<String, String>) listReader.read(headers)) != null) {
				fullCSVfile.add(fieldsInCurrentRow);
			}
		} catch (IOException e) {
			
		}		
		return fullCSVfile;
	}

	@Override
	public void closeConnection(Object connection) {
		ICsvMapReader connectionMap =(ICsvMapReader) connection;
		try {
			connectionMap.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	public void setCsvPreference(CsvPreference csvPreference){
		this.csvPreference = csvPreference;
	}

}
