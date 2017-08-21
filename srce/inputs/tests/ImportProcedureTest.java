package inputs.tests;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import etl.ImportProcedure;
import etl.model.ImportMetadata;
import inputs.ContextFacade;

public class ImportProcedureTest {
	List<HashMap<String, String>> listCSVfile;
	ImportMetadata importMetadata;
	ContextFacade context;
	
	@Before public void initialize() {
		context = new ContextFacade();
		context.ContextCSV("C:\\2D2\\git\\D2D\\srce\\inputs\\tests\\0_Fonts_201610.csv");
		listCSVfile = context.extractData();
		HashMap<String, String> mapping = new HashMap<String, String>();
		mapping.put("xcordenate", "INVENTARI_COORDENADA_X");
		mapping.put("ycordenate", "INVENTARI_COORDENADA_Y");
		mapping.put("origineCodeId", "INVENTARI_CODI");
		importMetadata = new ImportMetadata(mapping,"BarcelonaTest");
	}

	@Test
	public void testContextFacade() {		
		assertEquals(listCSVfile.size(),1988);
	}
	
	@Ignore
	@Test
	public void testImportProcedure() {		
		ImportProcedure importProcedure = new ImportProcedure(importMetadata);
		importProcedure.importTask(listCSVfile);
	}
	
	@Test
	public void testHeadersContextFacade() {		
		assertEquals(context.exploreHeaders().get(0),"INVENTARI_TIPUS");
	}
}
