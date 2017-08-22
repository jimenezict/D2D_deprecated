package inputs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

import inputs.webserviceJson.bcn.BarcelonaJSON;


public class WebServiceStrategy implements Strategy{
	
	private String urlService;
	private BufferedReader rd = null;
	private Gson gson;
	private Type mappingClass;
	
	public WebServiceStrategy(String urlService, Type mappingClass){
		super();
		this.urlService = urlService;	
		if(urlService == null||!booleanIsValidName()){
			throw new IllegalArgumentException();
		}
		gson = new Gson();
		this.mappingClass = mappingClass;
	}
	
	@Override
	public Object openConnection() {
		URL url;
		HttpURLConnection connection = null;
		
		try {
			url = new URL(urlService);
			connection = (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	@Override
	public List<HashMap<String, String>> readFile(Object connection) {		
		String inputString = readWebService(connection);
		BarcelonaJSON barcelonaJSON = gson.fromJson(inputString, mappingClass);
		return barcelonaJSON.getFields();
	}	

	@Override
	public void closeConnection(Object connection) {		
		try {
			if(rd != null){
				rd.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	private boolean booleanIsValidName(){
		urlService = urlService.trim();
		if(!urlService.startsWith("http:/")){
			return false;
		}else if(!urlService.contains("?")){
			return false;
		}
		return true;
	}
	
	private String readWebService(Object connection){
		StringBuilder result = new StringBuilder();
		try {			
			HttpURLConnection auxConnection = (HttpURLConnection) connection;
			auxConnection.setRequestMethod("GET");
			rd = new BufferedReader(new InputStreamReader(auxConnection.getInputStream()));
		    String line;
		    while ((line = rd.readLine()) != null) {
		         result.append(line);
		    }
		    rd.close();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString();
	}	
}
