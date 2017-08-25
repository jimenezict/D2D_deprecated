package inputs.tests;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import etl.FountainDAO;
import etl.ImportProcedure;
import etl.model.ImportMetadata;
import inputs.ContextFacade;
import inputs.webserviceJson.bcn.BarcelonaJSON;

public class ImportProcedureTest {
	List<HashMap<String, String>> listFileCSV,listFileWebService;
	ImportMetadata importMetadataCSV,importMetadataWS;
	ContextFacade contextCSV;
	ContextFacade contextWSBarcelona;
	private String webserviceURL = "http://opendata-ajuntament.barcelona.cat/data/api/action/datastore_search?resource_id=21f7a4df-2e73-45f8-8c6d-0b3db8c21527&limit=5";
	private FountainDAO fountainDAO;
	
	@Before public void initialize() {
		contextCSV = new ContextFacade();
		contextCSV.ContextCSV("C:\\2D2\\git\\D2D\\src\\test\\java\\inputs\\tests\\0_Fonts_201610Short.csv");
		listFileCSV = contextCSV.extractData();
		
		contextWSBarcelona = new ContextFacade();
		contextWSBarcelona.ContextWS(webserviceURL, BarcelonaJSON.class);
		listFileWebService = contextWSBarcelona.extractData();
		
		HashMap<String, String> mapping = new HashMap<String, String>();
		mapping.put("xcordenate", "INVENTARI_COORDENADA_X");
		mapping.put("ycordenate", "INVENTARI_COORDENADA_Y");
		mapping.put("origineCodeId", "INVENTARI_CODI");
		
		importMetadataCSV = new ImportMetadata(mapping,"BarcelonaTestCSV");
		importMetadataWS = new ImportMetadata(mapping,"BarcelonaTestWS");
		fountainDAO = new FountainDAO();
	}

	@Test
	public void testContextCSVFacade() {		
		assertEquals(listFileCSV.size(),4);
	}
	
	@Test
	public void testContextWSFacade() {		
		assertEquals(listFileWebService.size(),5);
	}
	
	@Test
	public void testHeadersContextCSVFacade() {		
		assertEquals(contextCSV.exploreHeaders().get(0),"INVENTARI_TIPUS");
	}
	
	@Test
	public void testHeadersContextWSFacade() {		
		assertEquals(contextWSBarcelona.exploreHeaders().get(0),"INVENTARI_TIPUS");
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
	
	@Test
	public void testImportProcedureWS(){
		ImportProcedure importProcedure = new ImportProcedure(importMetadataWS);
		int initialCount = importProcedure.numberOfRegisters();
		importProcedure.importTask(listFileWebService);
		int finalCount = importProcedure.numberOfRegisters();
	}	
}
