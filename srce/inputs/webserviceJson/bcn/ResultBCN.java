package inputs.webserviceJson.bcn;

import java.util.List;

import inputs.webserviceJson.FieldType;

public class ResultBCN {
	
	private String resource_id;
	private List<FieldType> fields;
	private List<RecordsBCN> records;
	
	public String getResource_id() {
		return resource_id;
	}
	
	public void setResource_id(String resource_id) {
		this.resource_id = resource_id;
	}	
	
	public List<FieldType> getFieldType() {
		return fields;
	}
	
	public void setFieldType(List<FieldType> fieldType) {
		this.fields = fieldType;
	}
	
	public List<RecordsBCN> getRecords() {
		return records;
	}
	
	public void setRecords(List<RecordsBCN> records) {
		this.records = records;
	}	
}
