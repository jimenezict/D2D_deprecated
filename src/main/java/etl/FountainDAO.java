package etl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import etl.model.FountainDTO;

public class FountainDAO {
	ApplicationContext ctx=new FileSystemXmlApplicationContext("C:\\2D2\\git\\D2D\\WebContent\\WEB-INF\\applicationContext.xml");
	JdbcTemplate jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
	
	public int testConnection(){
		return (int) jdbcTemplate.queryForObject("select 1 from dual", Integer.class);
	}
	
	public int insert(FountainDTO fountain){
		int id = 0;
		String insertSQL = "INSERT INTO FOUNTAINS (xcordenate,ycordenate,origineCodeId,origine) "
				+ "VALUES ("
				+ "'" + fountain.getXcordenate()+"',"
				+ "'" + fountain.getYcordenate()+"',"		
				+ "'" + fountain.getOrigineCodeId()+"',"
				+ "'" + fountain.getOrigine()+"')";
		try{
			jdbcTemplate.update(insertSQL);
			return getLastId();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return id;
	}
	
	public int countvalues(String table){
		int id = 0;
		String countSQL = "SELECT COUNT(*) FROM " + table;
		try{
			id = jdbcTemplate.queryForInt(countSQL);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return id;
	}
	
	public FountainDTO getFountainById(int id){
		String selectSQL = "SELECT * FROM FOUNTAINS WHERE id = " + id;
		return (FountainDTO) jdbcTemplate.queryForObject(selectSQL, new BeanPropertyRowMapper(FountainDTO.class));		
	}
	
	public void deleteFountainById(int id){
		String deleteSQL = "DELETE FROM FOUNTAINS WHERE ID = " + id;
		jdbcTemplate.execute(deleteSQL);
	}
	
	public int getLastId(){
		String selectLast = "SELECT id FROM FOUNTAINS ORDER BY ID DESC LIMIT 1";
		return (int) jdbcTemplate.queryForObject(selectLast, Integer.class);
	}
	
	public void removeByCondition(String key, String value){
		String deleteSQL = "DELETE FROM FOUNTAINS WHERE " + key + " = '" + value +"'";
		jdbcTemplate.execute(deleteSQL);
	}
}
