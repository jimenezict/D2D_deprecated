package inputs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder.Redirect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class WebServiceStrategy implements Strategy{
	
	private String urlService;
	BufferedReader rd = null;
	Gson gson;
	
	public WebServiceStrategy(String urlService){
		super();
		this.urlService = urlService;	
		if(urlService == null||!booleanIsValidName()){
			throw new IllegalArgumentException();
		}
		gson = new Gson();
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
		String usefulData = getUsefulData(inputString,"\"records\": ");
		java.lang.reflect.Type type = new TypeToken<List<String>>(){}.getType();
		gson.fromJson(usefulData, type);
		return null;
	}

	private String getUsefulData(String inputString, String dataTag) {
		int startPosition = inputString.indexOf(dataTag) + 11;
		return inputString.substring(startPosition, inputString.indexOf("}]", startPosition) + 2);		
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
