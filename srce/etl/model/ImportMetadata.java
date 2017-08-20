package etl.model;

import java.util.HashMap;

public class ImportMetadata {
	
	private HashMap<String, String> mapping;
	private String origine;
	
	public ImportMetadata(HashMap<String, String> mapping, String origine) {
		super();
		this.mapping = mapping;
		this.origine = origine;
	}
	
	public HashMap<String, String> getMapping() {
		return mapping;
	}
	
	public String getOrigine() {
		return origine;
	}
}
