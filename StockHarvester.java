import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;
import javax.swing.JOptionPane;
 
public class StockHarvester{
	
	// construct a URL string linking to the appropriate csv file
	public static String constructURL(String symbol, Calendar start, Calendar end){
		return "http://ichart.finance.yahoo.com/table.csv" +
				"?s=" + symbol +
				"&a=" + Integer.toString(start.get(Calendar.MONTH)) +
				"&b=" + Integer.toString(start.get(Calendar.DAY_OF_MONTH)) + 
				"&c=" + Integer.toString(start.get(Calendar.YEAR)) + 
				"&d=" + Integer.toString(end.get(Calendar.MONTH)) +
				"&e=" + Integer.toString(end.get(Calendar.DAY_OF_MONTH)) + 
				"&f=" + Integer.toString(end.get(Calendar.YEAR))+
				"&g=d";
	}
	
	public static String[] getCSVData(String symbol,
			GregorianCalendar start, GregorianCalendar end) throws Exception{
		
		String urlString = constructURL(symbol, start, end);
		// System.out.println(urlString);
		URL url = new URL(urlString);
		
		BufferedReader in = new BufferedReader(
				new InputStreamReader(url.openStream()));
		String inputLine = in.readLine();
		Vector<String> v = new Vector<String>();
		while((inputLine = in.readLine()) != null){
			v.addElement(inputLine);
		}
		
		String[] csvData = new String[v.size()];
		
		for(int i = 0; i < csvData.length; i++){
			csvData[i] = v.get(i);
		}
		
		return csvData;
		
	}
	
	public static Quotes processData(String[] input){	
		
		// if the input isn't sufficient, just return null
		if (input.length < 7)
			return null;
		
		// otherwise, process the data
		String symbol = input[0];
		int startMonth = Integer.parseInt(input[1]);
		int startDay = Integer.parseInt(input[2]);
		int startYear = Integer.parseInt(input[3]);
		int endMonth = Integer.parseInt(input[4]);
		int endDay = Integer.parseInt(input[5]);
		int endYear = Integer.parseInt(input[6]);
		
		// generate an array of the data
		// (each cell is a day's worth of data)
		// ** NOTE ** month is 0-indexed, day is not
		String[] data = null;
		try {
			data = getCSVData(symbol,
					new GregorianCalendar(startYear, startMonth-1, startDay),
					new GregorianCalendar(endYear, endMonth-1, endDay));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error retrieving data.");
		}
		
		if (data == null)
			return null;
		
		// generate an array of Quote from the data
		// generate it backwards such that the first element is the earliest
		Quote q[] = new Quote[data.length];
		for (int i = data.length-1; i >= 0; i--)
			q[i] = new Quote(data[data.length-1-i]);
		// generate a Quotes object from this array and returns this object
		return new Quotes(q);
			
	}	
}

