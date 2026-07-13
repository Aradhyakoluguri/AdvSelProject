package GenericUtilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtility {

	public String fetchDataFromJson(String key) throws FileNotFoundException, IOException, ParseException {
		JSONParser parse = new JSONParser();
		Object obj = parse.parse(new FileReader("./src/test/resources/data.json"));
		
		 JSONObject jsobj = (JSONObject) obj;
		 String value = jsobj.get(key).toString();
		 return value;
	}
}
