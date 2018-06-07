import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Quizlet {

	private static String CLIENT_ID;
	private static String SET_ID;
	private static String full;
	
	public Quizlet(String clientID, String setID) {
		CLIENT_ID = clientID;
		SET_ID = setID;
		
		String set = "https://api.quizlet.com/2.0/sets/" + SET_ID;
		String full = set + "?client_id=" + CLIENT_ID;
		
	}
	
	public String getClientID() {
		return CLIENT_ID;
	}
	
	public String getSetID() {
		return SET_ID;
	}
	
	public String getPlain() {
		return full;
	}

}
