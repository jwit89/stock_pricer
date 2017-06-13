import java.util.*;
 
// class for containing a set of Quotes
public class Quotes {
	private Vector<Quote> v = new Vector<Quote>();
	
	public Quotes(Quote[] q){
		for (int i = 0; i < q.length; i++){
			add(q[i]);
		}
	}
	
	public void add (Quote q){
		v.addElement(q);
	}
	
	public Quote[] getRecords(){
		Quote q[] = new Quote[v.size()];
		v.copyInto(q);
		return q;
	}
	
	// get close prices
	public double[] getClosePrices(){
		Quote quotes[] = getRecords();
		double d[] = new double[quotes.length];
		for (int i = 0; i < d.length; i++){
			d[i] = quotes[i].getClose();
		}
		return d;
	}
	
	// get adjusted close prices
	public double[] getAdjustedClosePrices(){
		Quote quotes[] = getRecords();
		double d[] = new double[quotes.length];
				for (int i=0; i < d.length; i++)
					d[i] = quotes[i].getAdjustedClose();
		return d;
	}
	
	public double getAverageDCGR(int days){
		// get the prices associated with the quotes
		double[] prices = getAdjustedClosePrices();
 
		// daily continuous growth rates
		Vector<Double> dcgr = new Vector<Double>();
		for(int i = 0; i + 1 < prices.length; i++){
			dcgr.addElement(Math.log(prices[i+1])-Math.log(prices[i]));
			// System.out.println(prices[i]+" "+prices[i+1]+" "+dcgr.get(i));
		}
		
		double average = 0;
		
		if (dcgr.size() >= days){
			List<Double> sublist = dcgr.subList(dcgr.size()-days, dcgr.size());
					average = StockStats.average(sublist);
		}
		
		return average;
	}
	
	public double getVolatilityDCGR(int days){
		// get the prices associated with the quotes
		double[] prices = getAdjustedClosePrices();
 
		// daily continuous growth rates
		Vector<Double> dcgr = new Vector<Double>();
		for(int i = 0; i + 1 < prices.length; i++){
			dcgr.addElement(Math.log(prices[i+1])-Math.log(prices[i]));
			// System.out.println(prices[i]+" "+prices[i+1]+" "+dcgr.get(i));
		}
		
		double stdev = 0;
		
		if (dcgr.size() >= days){
			List<Double> sublist = dcgr.subList(dcgr.size()-days, dcgr.size());
					stdev = StockStats.standardDeviation(sublist);
		}
		
		return stdev;
	}
}
