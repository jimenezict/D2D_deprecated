package inputs.tests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import etl.FountainDAO;
import etl.model.FountainDTO;

public class FountainDAOTest {
	private FountainDAO fountainDAO;
	private FountainDTO dummyFountain;
	
	@Before public void initialize() {
		fountainDAO = new FountainDAO();
		fountainDAO.removeByCondition("Origine", "Dummy");
		dummyFountain = new FountainDTO();
		dummyFountain.setXcordenate("0000");
		dummyFountain.setYcordenate("1111");
		dummyFountain.setOrigine("Dummy");
		dummyFountain.setOrigineCodeId("Dummy-1");
	}	
	
	@After public void finalize(){
		fountainDAO.removeByCondition("Origine", "Dummy");
	}
	
	@Test
	public void testGetObject(){
		fountainDAO.insertIfNew(dummyFountain);
		int testId = fountainDAO.getLastId();
		FountainDTO fountainTest = fountainDAO.getFountainById(testId);
		assertEquals(fountainTest.getId(),testId);
		fountainDAO.deleteFountainById(testId);
	}
	
	@Test
	public void insertAndRemove(){
		int initialValue = fountainDAO.countvalues("fountains");
		int id = fountainDAO.insertIfNew(dummyFountain);
		int middleValue = fountainDAO.countvalues("fountains");
		assertEquals(initialValue + 1, middleValue);
		fountainDAO.deleteFountainById(id);
		int lastValue = fountainDAO.countvalues("fountains");
		assertEquals(lastValue + 1, middleValue);
	}
}
