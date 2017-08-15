package inputs.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
	
	@Test
	public void validCSV2() {
		Context context = new Context(new CSVStrategy("notVeryImportantName.csv"));
		context.openConnection();
		assertTrue("",true);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void validCSV3() {
		Context context = new Context(new CSVStrategy(".csv"));
		context.openConnection();
	}

}
