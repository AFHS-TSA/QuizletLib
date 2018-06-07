import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Quizlet {

	private static String CLIENT_ID;
	private static String SET_ID;
	private static String full;

	public static JSONObject setQuiz(String clientID, String setID) throws Exception {
		CLIENT_ID = clientID;
		SET_ID = setID;

		String set = "https://api.quizlet.com/2.0/sets/" + SET_ID;
		String full = set + "?client_id=" + CLIENT_ID;

		URL obj = new URL(full);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		// add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		JSONObject myResponse = new JSONObject(response.toString());

		return myResponse;
		 
	}	

	public static String[] getTerms(String clientID, String setID) throws JSONException, Exception {
		JSONArray terms_data = setQuiz(clientID, setID).getJSONArray("terms");
		String[] terms = new String[terms_data.length()];
	     for(int i=0;i<terms_data.length();i++) {
	    	 terms[i] = terms_data.getJSONObject(i).getString("term");
	     }
	     return terms;
	}
	
	public static String[] getDefinition(String clientID, String setID) throws JSONException, Exception {
		JSONArray terms_data = setQuiz(clientID, setID).getJSONArray("terms");
		String[] definition = new String[terms_data.length()];
	     for(int i=0;i<terms_data.length();i++) {
	    	 definition[i] = terms_data.getJSONObject(i).getString("definition");
	     }
	     return definition;	
	}
	
	public static String getClientID() {
		return CLIENT_ID;
	}

	public static String getSetID() {
		return SET_ID;
	}

	public static String getPlain() {
		return full;
	}

}
