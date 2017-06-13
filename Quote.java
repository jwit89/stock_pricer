import java.util.*;
 
// class used to contain all the information for a single day
public class Quote{
	//Date,Open,High,Low,Close,Volume,Adj Close
	private GregorianCalendar date;
	private double open, high, low, close, volume, adjustedClose;
	
	public Quote(String s){
		StringTokenizer st = new StringTokenizer(s,",");
		setDate(extractDate(st.nextToken()));
		setOpen(Double.parseDouble(st.nextToken()));
		setHigh(Double.parseDouble(st.nextToken()));
		setLow(Double.parseDouble(st.nextToken()));
		setClose(Double.parseDouble(st.nextToken()));
		setVolume(Double.parseDouble(st.nextToken()));
		setAdjustedClose(Double.parseDouble(st.nextToken()));
	}
	
	private GregorianCalendar extractDate(String s){
		StringTokenizer st = new StringTokenizer(s,"-");
		int year = Integer.parseInt(st.nextToken());
		int month = Integer.parseInt(st.nextToken());
		int day = Integer.parseInt(st.nextToken());
		return new GregorianCalendar(year, month-1, day);
	}
	
	// yyyy-mm-dd
	private void setDate(GregorianCalendar d){		
		date = d;
	}
	
	public GregorianCalendar getDate(){
		return date;
	}
	
	private void setOpen(double o){
		open = o;
	}
	
	public double getOpen(){
		return open;
	}
	
	private void setHigh(double h){
		high = h;
	}
	
	public double getHigh(){
		return high;
	}
	
	private void setLow(double l){
		low = l;
	}
	
	public double getLow(){
		return low;
	}
	
	private void setClose(double c){
		close = c;
	}
	
	public double getClose(){
		return close;
	}
	
	private void setVolume(double v){
		volume = v;
	}
	
	public double getVolume(){
		return volume;
	}
	
	private void setAdjustedClose(double ac){
		adjustedClose = ac;
	}
	
	public double getAdjustedClose(){
		return adjustedClose;
	}
}
