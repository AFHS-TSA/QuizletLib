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
		 JSONArray terms_data = setArray(clientID, setID, "terms");
		 String[] terms = new String[terms_data.length()];
	     for(int i=0;i<terms_data.length();i++) {
	    	 terms[i] = terms_data.getJSONObject(i).getString("term");
	     }
	     return terms;
	}

	public static String[] getTerms() throws JSONException, Exception {
		 JSONArray terms_data = setArray(CLIENT_ID, SET_ID, "terms");
		 String[] terms = new String[terms_data.length()];
	     for(int i=0;i<terms_data.length();i++) {
	    	 terms[i] = terms_data.getJSONObject(i).getString("term");
	     }
	     return terms;
	}	

	public static String[] getDefinition(String clientID, String setID) throws JSONException, Exception {
		 JSONArray terms_data = setArray(clientID, setID, "terms");
	   	 String[] definition = new String[terms_data.length()];
	     for(int i=0;i<terms_data.length();i++) {
	    	 definition[i] = terms_data.getJSONObject(i).getString("definition");
	     }
	     return definition;	
	}
	
	public static String[] getDefinition() throws JSONException, Exception {
		 JSONArray terms_data = setArray(CLIENT_ID, SET_ID, "terms");
	   	 String[] definition = new String[terms_data.length()];
	     for(int i=0;i<terms_data.length();i++) {
	    	 definition[i] = terms_data.getJSONObject(i).getString("definition");
	     }
	     return definition;	
	}
	
	public static String getTitle(String clientID, String setID) throws JSONException, Exception {
	     return setQuiz(clientID, setID).getString("title");
	}
	
	public static String getTitle() throws JSONException, Exception {
		return setQuiz(CLIENT_ID, SET_ID).getString("title");
	}
	
	public static String getCreator(String clientID, String setID) throws JSONException, Exception {
		return setQuiz(clientID, setID).getString("created_by");
	}

	public static String getCreator() throws JSONException, Exception {
		return setQuiz(CLIENT_ID, SET_ID).getString("created_by");
	}
	
	public static int getTermCount(String clientID, String setID) throws JSONException, Exception {
		return setQuiz(clientID, setID).getInt("term_count");
	}
	
	public static int getTermCount() throws JSONException, Exception {
		return setQuiz(CLIENT_ID, SET_ID).getInt("term_count");
	}

	private static JSONArray setArray(String clientID, String setID, String name) throws JSONException, Exception {
		JSONArray array = setQuiz(clientID, setID).getJSONArray(name);
		return array;
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
