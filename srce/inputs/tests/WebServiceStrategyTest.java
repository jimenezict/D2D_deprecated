package inputs.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.supercsv.io.ICsvMapReader;

import inputs.Context;
import inputs.WebServiceStrategy;
import inputs.webserviceJson.bcn.BarcelonaJSON;

public class WebServiceStrategyTest {

	public String webservice = "http://opendata-ajuntament.barcelona.cat/data/api/action/datastore_search?resource_id=21f7a4df-2e73-45f8-8c6d-0b3db8c21527&limit=5";
	
	@Test
	public void Build() {
		Context context = new Context(new WebServiceStrategy("http://valid.csv?",BarcelonaJSON.class));
		assertTrue("",true);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void validWS1() {
		Context context = new Context(new WebServiceStrategy(null,BarcelonaJSON.class));
		context.openConnection();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void validWS2() {
		Context context = new Context(new WebServiceStrategy("nothttpaddres",BarcelonaJSON.class));
		context.openConnection();
	}
	
	@Test
	public void openBarcelonaWebService() {
		Context context = new Context(new WebServiceStrategy(webservice,BarcelonaJSON.class));
		Object testConnection = context.openConnection();
		assertNotNull(testConnection);
	}	

	@Test
	public void openAndCloseConnection() {
		Context context = new Context(new WebServiceStrategy(webservice,BarcelonaJSON.class));
		Object testConnection = context.openConnection();
		context.closeConnection(testConnection);
	}
	
	@Test
	public void readFile() {
		Context context = new Context(new WebServiceStrategy(webservice,BarcelonaJSON.class));
		Object testConnection = context.openConnection();
		List<HashMap<String,String>> listWebServicefile = context.read(testConnection);
		assertEquals(listWebServicefile.size(),5);
	}
	
	@Test
	public void readFileCheckData() {
		Context context = new Context(new WebServiceStrategy(webservice,BarcelonaJSON.class));
		Object testConnection = context.openConnection();
		List<HashMap<String,String>> listWebServicefile = context.read(testConnection);
		assertEquals(listWebServicefile.get(0).get("INVENTARI_CODI"),"01-001");
	}
}
