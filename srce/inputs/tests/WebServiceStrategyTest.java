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

public class WebServiceStrategyTest {

	@Test
	public void Build() {
		Context context = new Context(new WebServiceStrategy("http://valid.csv?"));
		assertTrue("",true);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void validWS1() {
		Context context = new Context(new WebServiceStrategy(null));
		context.openConnection();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void validWS2() {
		Context context = new Context(new WebServiceStrategy("nothttpaddres"));
		context.openConnection();
	}
	
	@Test
	public void openBarcelonaWebService() {
		Context context = new Context(new WebServiceStrategy("http://opendata-ajuntament.barcelona.cat/data/api/action/datastore_search?resource_id=21f7a4df-2e73-45f8-8c6d-0b3db8c21527&limit=5"));
		Object testConnection = context.openConnection();
		assertNotNull(testConnection);
	}
	
	@Ignore
	@Test
	public void correctHeadersOnWebService() {
		Context context = new Context(new WebServiceStrategy("http://opendata-ajuntament.barcelona.cat/data/api/action/datastore_search?resource_id=21f7a4df-2e73-45f8-8c6d-0b3db8c21527&limit=5"));
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
		Context context = new Context(new WebServiceStrategy("http://opendata-ajuntament.barcelona.cat/data/api/action/datastore_search?resource_id=21f7a4df-2e73-45f8-8c6d-0b3db8c21527&limit=5"));
		Object testConnection = context.openConnection();
		context.closeConnection(testConnection);
	}
	
	@Test
	public void readFile() {
		Context context = new Context(new WebServiceStrategy("http://opendata-ajuntament.barcelona.cat/data/api/action/datastore_search?resource_id=21f7a4df-2e73-45f8-8c6d-0b3db8c21527&limit=5"));
		Object testConnection = context.openConnection();
		List<HashMap<String,String>> listCSVfile = context.read(testConnection);
		assertEquals(listCSVfile.size(),1988);
	}
}
