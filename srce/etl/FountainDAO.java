package etl;

import org.springframework.jdbc.core.JdbcTemplate;

public class FountainDAO {
	
	public static void insertOrUpdate(FountainDTO fountain){
		isElementOnDatabase(fountain);
	}
	
	private static boolean isElementOnDatabase(FountainDTO fountain){
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String isElementOnDatabase = "select count(*) from table1 where origine = ? and origineCode = ?";
		int numResults = jdbcTemplate.queryForObject(isElementOnDatabase, Integer.class, fountain.getOrigine(),fountain.getOrigineCodeId());
		if(numResults > 0) 
			return true;
		return false;		
	}
}
