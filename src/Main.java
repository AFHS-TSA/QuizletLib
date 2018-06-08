import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;


public class Main {

	public static void main(String[] args)  {
		try {
			Quizlet.setQuiz("UNNa57NRpT","172935780");
			System.out.println(Quizlet.getTitle());
			System.out.println(Quizlet.getTermCount());
			Quizlet.setQuiz("UNNa57NRpT","183763776");
			System.out.println(Quizlet.getTitle());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			String[] terms = Quizlet.getTerms();
		    for(String str : terms)
		    	System.out.println(str);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
        
}

