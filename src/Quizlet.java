import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Quizlet {

	private static String CLIENT_ID;
	private static String SEARCHCLIENT_ID;
	private static int SET_ID;
	private static String SEARCH_WORD;
	private static String full;

	/**
	 * This method sets the Quiz and retries the json file.
	 * @param clientID
	 * @param setID
	 * @return
	 * @throws Exception
	 */
	public static JSONObject setQuiz(String clientID, int setID) throws Exception {
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

	/**
	 * Gets the terms in a set without the setQuiz
	 * @param clientID
	 * @param setID
	 * @return
	 * @throws JSONException
	 * @throws Exception
	 */
	public static String[] getTerms(String clientID, int setID) throws JSONException, Exception {
		 JSONArray terms_data = setArray(clientID, setID, "terms");
		 String[] terms = new String[terms_data.length()];
	     for(int i=0;i<terms_data.length();i++) {
	    	 terms[i] = terms_data.getJSONObject(i).getString("term");
	     }
	     return terms;
	}

	/**
	 * Gets the terms in a set based on setQuiz
	 * @return
	 * @throws JSONException
	 * @throws Exception
	 */
	public static String[] getTerms() throws JSONException, Exception {
		 JSONArray terms_data = setArray(CLIENT_ID, SET_ID, "terms");
		 String[] terms = new String[terms_data.length()];
	     for(int i=0;i<terms_data.length();i++) {
	    	 terms[i] = terms_data.getJSONObject(i).getString("term");
	     }
	     return terms;
	}	

	/**
	 * Gets the definition of a set without the setQuiz
	 * @param clientID
	 * @param setID
	 * @return
	 * @throws JSONException
	 * @throws Exception
	 */
	public static String[] getDefinition(String clientID, int setID) throws JSONException, Exception {
		 JSONArray terms_data = setArray(clientID, setID, "terms");
	   	 String[] definition = new String[terms_data.length()];
	     for(int i=0;i<terms_data.length();i++) {
	    	 definition[i] = terms_data.getJSONObject(i).getString("definition");
	     }
	     return definition;	
	}
	/**
	 * Gets the definition in a set based on setQuiz
	 * @return
	 * @throws JSONException
	 * @throws Exception
	 */
	public static String[] getDefinition() throws JSONException, Exception {
		 JSONArray terms_data = setArray(CLIENT_ID, SET_ID, "terms");
	   	 String[] definition = new String[terms_data.length()];
	     for(int i=0;i<terms_data.length();i++) {
	    	 definition[i] = terms_data.getJSONObject(i).getString("definition");
	     }
	     return definition;	
	}

	/**
	 * Gets the description without the setQuiz
	 * @param clientID
	 * @param setID
	 * @return
	 * @throws JSONException
	 * @throws Exception
	 */
	public static String getDescription(String clientID, int setID) throws JSONException, Exception {
	    return setQuiz(clientID, setID).getString("description"); 
	}
	
	/**
	 * Gets the description based on setQuiz
	 * @return
	 * @throws JSONException
	 * @throws Exception
	 */
	public static String getDescription() throws JSONException, Exception {
	    return setQuiz(CLIENT_ID, SET_ID).getString("description");
	}

	/**
	 * Gets the title without the setQuiz
	 * @param clientID
	 * @param setID
	 * @return
	 * @throws JSONException
	 * @throws Exception
	 */
	public static String getTitle(String clientID, int setID) throws JSONException, Exception {
	     return setQuiz(clientID, setID).getString("title");
	}

	/**
	 * Gets the title based on setQuiz
	 * @return
	 * @throws JSONException
	 * @throws Exception
	 */
	public static String getTitle() throws JSONException, Exception {
		return setQuiz(CLIENT_ID, SET_ID).getString("title");
	}
	
	/**
	 * Gets the creator without setQuiz
	 * @param clientID
	 * @param setID
	 * @return
	 * @throws JSONException
	 * @throws Exception
	 */
	public static String getCreator(String clientID, int setID) throws JSONException, Exception {
		return setQuiz(clientID, setID).getString("created_by");
	}

	/**
	 * Gets the creator based on setQuiz
	 * @return
	 * @throws JSONException
	 * @throws Exception
	 */
	public static String getCreator() throws JSONException, Exception {
		return setQuiz(CLIENT_ID, SET_ID).getString("created_by");
	}
	
	/**
	 * Gets the Term_Count of set without setQuiz
	 * @param clientID
	 * @param setID
	 * @return
	 * @throws JSONException
	 * @throws Exception
	 */
	public static int getTermCount(String clientID, int setID) throws JSONException, Exception {
		return setQuiz(clientID, setID).getInt("term_count");
	}

	/**
	 * Gets the Term_Count based on setQuiz
	 * @return
	 * @throws JSONException
	 * @throws Exception
	 */
	public static int getTermCount() throws JSONException, Exception {
		return setQuiz(CLIENT_ID, SET_ID).getInt("term_count");
	}

	/**
	 * Sets the JSON array within the object
	 * @param clientID
	 * @param setID
	 * @param name
	 * @return
	 * @throws JSONException
	 * @throws Exception
	 */
	private static JSONArray setArray(String clientID, int setID, String name) throws JSONException, Exception {
		JSONArray array = setQuiz(clientID, setID).getJSONArray(name);
		return array;
	}

	/* SEARCHING METHODS */

	/**
	 * Sets the search based on keyword.
	 * Searches in title, description and username
	 * @param clientID
	 * @param keyword
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 */
	public static JSONObject setSearch(String clientID, String keyword) throws IOException, JSONException {
		SEARCHCLIENT_ID = clientID;
		SEARCH_WORD = keyword;

		String search =  "https://api.quizlet.com/2.0/search/sets/?q=" + keyword;
		String fullSearch = search + "&client_id=" + clientID; 

		URL obj = new URL(fullSearch);
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
		JSONObject mySearch = new JSONObject(response.toString());

		return mySearch;	
	}

	/**
	 * Gets the id of all the sets in your search
	 * @return
	 * @throws JSONException
	 * @throws IOException
	 */
	public static int[] getSearchID() throws JSONException, IOException {
		JSONArray search = setSearch(SEARCHCLIENT_ID, SEARCH_WORD).getJSONArray("sets");
	   	 int[] id = new int[search.length()];
	     for(int i=0;i<search.length();i++) {
	    	 id[i] = search.getJSONObject(i).getInt("id");
	     }
		return id; 
	}

	/**
	 * Returns CLIENT_ID value
	 * @return
	 */
	public static String getClientID() {
		return CLIENT_ID;
	}

	/**
	 * Returns SET_ID value
	 * @return
	 */
	public static int getSetID() {
		return SET_ID;
	}

	/**
	 * Returns the URL value
	 * @return
	 */
	public static String getPlain() {
		return full;
	}

}
