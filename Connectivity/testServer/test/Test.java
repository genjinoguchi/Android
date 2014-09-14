import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test{
    public static void main(String[]args){
	
	try{
	    URL root = new URL("http://192.168.1.9:5000");
	    HttpURLConnection con = (HttpURLConnection) root.openConnection();
	    con.setReadTimeout(5000);

	    con.setRequestMethod("GET");
	
	    int responseCode = con.getResponseCode();
	    
	    BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
	    String inputLine;
	    StringBuffer response = new StringBuffer();

	    while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
	    in.close();

	    System.out.println(response.toString());
 

	} catch (Exception e){
	    System.out.println("fk");
	    e.printStackTrace();
	}
        
	
    }

} 
