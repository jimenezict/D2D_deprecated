package etl.model;

import java.util.HashMap;

public class ImportMetadata {
	
	private HashMap<String, String> mapperSourceToDatabase;
	private String dataSetOrigine;
	
	public ImportMetadata(HashMap<String, String> mapping, String dataSetOrigine) {
		super();
		this.mapperSourceToDatabase = mapping;
		this.dataSetOrigine = dataSetOrigine;
	}
	
	public HashMap<String, String> getMapping() {
		return mapperSourceToDatabase;
	}
	
	public String getOrigine() {
		return dataSetOrigine;
	}
}
