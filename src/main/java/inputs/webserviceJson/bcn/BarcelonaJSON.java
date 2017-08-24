package inputs.webserviceJson.bcn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import inputs.webserviceJson.WebServiceJson;

public class BarcelonaJSON extends WebServiceJson {

	private String help;
	private String success;
	private ResultBCN result;
	
	@Override
	public List<HashMap<String, String>> getFields() {			
		List<HashMap<String,String>> fountainList = new ArrayList<HashMap<String,String>>();

		if(result!=null && result.getRecords()!= null && !result.getRecords().isEmpty()){
			for(RecordsBCN record : result.getRecords()){
				fountainList.add(mapRecord(record));
			}		
		}
		return fountainList;
	}

	private HashMap<String, String> mapRecord(RecordsBCN record) {
		HashMap<String, String> map = new HashMap<String,String>();
		map.put("INVENTARI_NOM", record.getINVENTARI_NOM());
		map.put("INVENTARI_CODI", record.getINVENTARI_CODI());
		map.put("INVENTARI_CARRER", record.getINVENTARI_CARRER());
		map.put("INVENTARI_TIPUS", record.getINVENTARI_TIPUS());
		map.put("INVENTARI_COORDENADA_X", record.getINVENTARI_COORDENADA_X());
		map.put("INVENTARI_COORDENADA_Y", record.getINVENTARI_COORDENADA_Y());
		map.put("INVENTARI_NUMERO_CARRER", record.getINVENTARI_NUMERO_CARRER());
		return map;
	}

	public String getHelp() {
		return help;
	}

	public void setHelp(String help) {
		this.help = help;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public ResultBCN getResult() {
		return result;
	}

	public void setResult(ResultBCN result) {
		this.result = result;
	}

}
