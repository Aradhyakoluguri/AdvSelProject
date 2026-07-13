package GenericUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author k.sreeja this method is used to work with property file
 */
public class PropertyFileUtility {
	/**
	 * this method is used to fetch the data from property file
	 * @param key
	 * @return
	 * @throws IOException
	 */
public String fetchdatafrompropertyfile(String key) throws IOException {
	FileInputStream fis = new FileInputStream("./src/test/resources/VtigerCommonData.properties");
	Properties p = new Properties();
	p.load(fis);
	 String value = p.getProperty(key);
	return value;
}
/**
 * this method is used to write back the data to property file
 * @param key
 * @param value
 * @throws IOException
 */
public void writebackdatatopropertyfile(String key,String value) throws IOException {
	FileInputStream fis = new FileInputStream("./src/test/resources/VtigerCommonData.properties");
	Properties p = new Properties();
	p.load(fis);
	p.put(key, value);
	FileOutputStream fos = new FileOutputStream("./src/test/resources/VtigerCommonData");
	 p.store(fos,"updated");
}
}
