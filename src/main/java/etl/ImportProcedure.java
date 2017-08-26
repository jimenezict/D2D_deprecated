package etl;

import java.util.HashMap;
import java.util.List;

import etl.model.FountainDTO;
import etl.model.ImportMetadata;

public class ImportProcedure {
	ImportMetadata importMetadata;
	FountainDAO fountainDAO = new FountainDAO();
		
	public ImportProcedure(ImportMetadata importMetadata){
		if(importMetadata == null){
			throw new IllegalArgumentException();
		}
		this.importMetadata = importMetadata;
	}
	
	public void importTask(List<HashMap<String, String>> importData){
		importData.forEach((line) -> {
			insert(setFountainDTO(line));
		});
	}
	
	public int numberOfRegisters(){
		return fountainDAO.countvalues("fountains");
	}	
	
	private FountainDTO setFountainDTO(HashMap<String, String> line){
		FountainDTO fountain = new FountainDTO();
		fountain.setOrigine(importMetadata.getOrigine());
		fountain.setOrigineCodeId(line.get(importMetadata.getMapping().get("origineCodeId")));
		fountain.setXcordenate(line.get(importMetadata.getMapping().get("xcordenate")));
		fountain.setYcordenate(line.get(importMetadata.getMapping().get("ycordenate")));
		return fountain;
	}
	
	private void insert(FountainDTO fountain){
		fountainDAO.insertIfNew(fountain);
	}

}
