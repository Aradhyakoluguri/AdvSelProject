package GenericUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.cj.jdbc.Driver;

public class DataBaseUtility {
public Connection con;

public Connection connectToDB() throws SQLException {
	Driver driver = new Driver();
	DriverManager.registerDriver(driver);
	 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/advproject","root","root");
	 return con;
	 }
public ResultSet fetchTheDataFromDB(String query) throws SQLException {
	Statement stat = con.createStatement();
	ResultSet res = stat.executeQuery(query);
	return res;
}
public int writeDataBackToDB(String query) throws SQLException {
	Statement stat = con.createStatement();
	  int res = stat.executeUpdate(query);
	  return res;
}
public void DisconnectWithDB() throws SQLException {
	con.close();
}
}