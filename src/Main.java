import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;


import com.google.gson.Gson;


public class Main {

	public static void main(String[] args) throws Exception {
        URL base = new URL("https://api.quizlet.com/2.0/sets/234104177/?client_id=UNNa57NRpT");

		InputStreamReader baseReader = new InputStreamReader(base.openStream());
        BaseDto baseDto = new Gson().fromJson(baseReader, BaseDto.class);

        System.out.println(baseDto.title);
        
        System.out.println(baseDto.terms);
        
        String testing[] = baseDto.terms.toString().split("term=");
        for(int i=1;i<testing.length;i++) {
        	int j = testing[i].indexOf(' ');
        	System.out.println(testing[i].substring(0, j));
        }
    }
	
    private static class BaseDto<Item> {
    	List<Item> terms;
    	String term;
    	String title;
    	String url;
        
    }
}

