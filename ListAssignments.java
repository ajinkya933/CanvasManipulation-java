/**
@ Author:Prasad patil
Description: List Assignments of a particular course.

*/
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class ListAssignments {
public static HttpURLConnection connection;
public static void main(String[] args) throws Exception {
	try {
	String auth = fileReader("auth.dat");
	// put the required courseID
	String courseId="10300000000000133";
	String url1= "https://canvas.instructure.com/api/v1/courses/";
	URL url = new URL(url1+courseId+"/assignments");
	connection = (HttpURLConnection) url.openConnection();
	connection.setRequestProperty("Authorization", "Bearer "+auth);
	connection.setRequestMethod("GET");
    System.out.println("\nSending 'GET' request to URL : " + url);
    System.out.println("Response code:" + connection.getResponseCode());
    System.out.println("Response message:" + connection.getResponseMessage());
    
    File file = new File("JSON1.txt");
    // creates the file
    file.createNewFile();
    // creates a FileWriter Object
    FileWriter writer = new FileWriter(file); 
    // Writes the content to the file
  
        // Read the response:
        BufferedReader reader = new BufferedReader(new InputStreamReader(
        connection.getInputStream()));
        String line;
        StringBuffer response = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        System.out.println(response.toString());
        writer.write(response.toString()); 
        writer.flush();
        writer.close();
	}
	catch (MalformedURLException e1) {
		e1.printStackTrace();
	} catch (IOException e2) {
		e2.printStackTrace();
	}
	jsonParser();
}

public static String fileReader(String fileName) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(fileName));
    try {
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            line = br.readLine();
        }
        return sb.toString();
    } finally {
        br.close();
    }
}
public static void jsonParser() throws Exception{
    try{
    	String jsonData = fileReader("JSON1.txt");
    	JSONParser parser = new JSONParser();
    	JSONArray array = (JSONArray)parser.parse(jsonData);
    	Iterator i = array.iterator();
    	System.out.println( "\n    id"+"\t\t\t"+ "dueAt" +"\t"+ "assignmentName"+"\n");
    	// take each value from the json array separately
    	while (i.hasNext()) {
    	JSONObject innerObj = (JSONObject) i.next();
       
    	// get a number from the JSON object
    	long id = (long) innerObj.get("id");         
    	// get a String from the JSON object
    	String assignmentName = (String) innerObj.get("name");
    	String dueAt = (String) innerObj.get("due_at");
    	System.out.println( id+"\t"+ dueAt +"\t"+ assignmentName);
    	}
    	}catch(ParseException ex){
    		ex.printStackTrace();        	
    	}catch (IOException e2) {
    		e2.printStackTrace();
		}
	}
}
