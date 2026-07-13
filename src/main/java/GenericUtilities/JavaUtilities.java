package GenericUtilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
/**
 * this method is used to fetch random int till 5000
 */
public class JavaUtilities {
public int fetchRandomNumber() {
	Random r = new Random();
	int rnum = r.nextInt(5000);
	return rnum;
}
/**
 * this method is used to fetch current date
 * @return
 */
public String fetchCurrentDate() {
	Date dobj = new Date();
	SimpleDateFormat sim = new SimpleDateFormat("YYYY-MM-dd");
	String currentdate = sim.format(dobj);
	return currentdate;
	
}
/**
 * this method is used to fetch date after given days of month
 * @param days
 * @return
 */
public String fetchDateAfterGivenDays(int days) {
	Date dobj = new Date();
	SimpleDateFormat sim = new SimpleDateFormat("YYYY-MM-dd");
	String currentdate = sim.format(dobj);
	Calendar cal = sim.getCalendar();
	cal.add(Calendar.DAY_OF_MONTH,days);
	String date = sim.format(cal.getTime());
	return date;
}
}
