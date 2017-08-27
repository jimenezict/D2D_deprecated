package inputs.tests;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import etl.FountainDAO;
import etl.ImportProcedure;
import etl.model.ImportMetadata;
import inputs.ContextFacade;
import inputs.webserviceJson.bcn.BarcelonaJSON;

public class ImportProcedureValenciaTest {
	List<HashMap<String, String>> listFileCSV,listFileWebService;
	ImportMetadata importMetadataCSV,importMetadataWS;
	ContextFacade contextCSV;
	ContextFacade contextWSBarcelona;
	private String webserviceURL = "http://opendata-ajuntament.barcelona.cat/data/api/action/datastore_search?resource_id=21f7a4df-2e73-45f8-8c6d-0b3db8c21527&limit=5";
	private FountainDAO fountainDAO;
	
	@Before public void initialize() {
		contextCSV = new ContextFacade();
		contextCSV.ContextCSVSemiColon("C:\\2D2\\git\\D2D\\src\\test\\java\\inputs\\tests\\CIA_FUENTESShort.csv");

		listFileCSV = contextCSV.extractData();		
		
		HashMap<String, String> mapping = new HashMap<String, String>();
		mapping.put("xcordenate", "X");
		mapping.put("ycordenate", "Y");
		mapping.put("origineCodeId", "codigo");
		mapping.put("comment1", "calle");
		
		importMetadataCSV = new ImportMetadata(mapping,"ValenciaCSV");
		
		fountainDAO = new FountainDAO();
		fountainDAO.removeByCondition("Origine", "ValenciaCSV");
		
	}
	
	@After public void finalize(){
		fountainDAO.removeByCondition("Origine", "ValenciaCSV");
	}

	@Test
	public void testExtractionOfData(){
		listFileCSV = contextCSV.extractData();
	}
	
	@Test
	public void testContextCSVFacade() {		
		assertEquals(listFileCSV.size(),4);
	}	
	
	@Test
	public void testHeadersContextCSVFacade() {		
		assertEquals(contextCSV.exploreHeaders().get(0),"codigo");
	}
	
	
	@Test
	public void testConnection(){
		assertEquals(fountainDAO.testConnection(),1);
	}
	
	
	@Test
	public void testImportProcedureCSV(){
		ImportProcedure importProcedure = new ImportProcedure(importMetadataCSV);
		int initialCount = importProcedure.numberOfRegisters();
		importProcedure.importTask(listFileCSV);
		int finalCount = importProcedure.numberOfRegisters();
	}	
	
}
