import java.util.GregorianCalendar;
import java.util.Calendar;
 
// calendar utilities class
public class CalUtils {
	// returns the current date in form "mm/dd/yyyy"
	public static String getCurrentDate(){
		String month="";
		String day="";
		String year="";
		GregorianCalendar gregorianCalendar=new GregorianCalendar();
		month=String.valueOf(gregorianCalendar.get(GregorianCalendar.MONTH));
		day=String.valueOf(gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH));
		year=String.valueOf(gregorianCalendar.get(GregorianCalendar.YEAR));
		// month is 0-indexed
		month=((Integer.parseInt(month)+1)+"");
		
		return month+"/"+day+"/"+year;
	}
	
	// returns the days between now and exp
	// now and exp in form mm/dd/yyyy
	public static int getDaysBetween(String now, String exp){
		String[] today = now.split("/");
		String[] expiration = exp.split("/");
		
		if (today.length < 3)
			return 0;
		if (expiration.length < 3)
			return 0;
		
		GregorianCalendar gc1 = new GregorianCalendar(
				Integer.parseInt(today[2]),
				Integer.parseInt(today[0]) - 1,
				Integer.parseInt(today[1]));
		GregorianCalendar gc2 = new GregorianCalendar(
				Integer.parseInt(expiration[2]),
				Integer.parseInt(expiration[0]) - 1,
				Integer.parseInt(expiration[1]));
		
		int daysBetween = 0;
		while (gc1.before(gc2)){
			gc1.add(Calendar.DAY_OF_MONTH, 1);
			daysBetween++;
		}
		return daysBetween;
				
	}
}

