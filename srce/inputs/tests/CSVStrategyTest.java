package inputs.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.supercsv.io.ICsvMapReader;

import inputs.CSVStrategy;
import inputs.Context;

public class CSVStrategyTest {

	@Test
	public void Build() {
		Context context = new Context(new CSVStrategy("notVeryImportantName.csv"));
		assertTrue("",true);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void validCSV1() {
		Context context = new Context(new CSVStrategy("notVeryImportantNamecsv"));
		context.openConnection();
	}
	
	@Ignore
	@Test(expected = FileNotFoundException.class)
	public void unexistingFile() {
		Context context = new Context(new CSVStrategy("notValidName.csv"));
		context.openConnection();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void validCSV2() {
		Context context = new Context(new CSVStrategy(".csv"));
		context.openConnection();
	}
	
	@Test
	public void openBarcelonaFile() {
		Context context = new Context(new CSVStrategy("C:\\2D2\\git\\D2D\\srce\\inputs\\tests\\0_Fonts_201610.csv"));
		ICsvMapReader testConnection = (ICsvMapReader) context.openConnection();
		assertNotNull(testConnection);
	}
	
	@Test
	public void correctHeadersOnBarcelonaFile() {
		Context context = new Context(new CSVStrategy("C:\\2D2\\git\\D2D\\srce\\inputs\\tests\\0_Fonts_201610.csv"));
		ICsvMapReader testConnection = (ICsvMapReader) context.openConnection();
		String[] strHeaderList = null;
		try {
			strHeaderList = testConnection.getHeader(true);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		assertEquals(strHeaderList.length,7);
		assertEquals(strHeaderList[0],"INVENTARI_CODI");
	}
	
	@Test
	public void openAndCloseConnection() {
		Context context = new Context(new CSVStrategy("C:\\2D2\\git\\D2D\\srce\\inputs\\tests\\0_Fonts_201610.csv"));
		ICsvMapReader testConnection = (ICsvMapReader) context.openConnection();
		context.closeConnection(testConnection);
	}
	
	@Test
	public void readFile() {
		Context context = new Context(new CSVStrategy("C:\\2D2\\git\\D2D\\srce\\inputs\\tests\\0_Fonts_201610.csv"));
		ICsvMapReader testConnection = (ICsvMapReader) context.openConnection();
		List<HashMap<String,String>> listCSVfile = context.read(testConnection);
		assertEquals(listCSVfile.size(),1988);
	}
}
