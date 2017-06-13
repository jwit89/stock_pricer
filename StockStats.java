import java.util.List;
import java.lang.Math;
 
public class StockStats {
	
	// returns the average value in a vector
	public static double average(List<Double> v){
		double length = v.size();
		double sum = 0;
		
		for (int i = 0; i < v.size(); i++)
			sum+=v.get(i);
		
		return sum/length;
	}
	
	// returns the variance of a vector
	public static double variance(List<Double> v){
		double avg = average(v);
		double length = v.size();
		double sumSquares = 0;
		
		for (int i = 0; i < v.size(); i++)
			sumSquares += Math.pow(v.get(i) - avg, 2);
		
		return sumSquares/(length-1);	
	}
	
	// returns the standard deviation of a vector
	public static double standardDeviation(List<Double> v){
		return Math.sqrt(variance(v));
	}
	
	// returns the cumulative normal distribution function (CNDF)
	// for a standard normal: N(0,1)
	public static double CNDF(double x)
	{
	    int neg = (x < 0d) ? 1 : 0;
	    if ( neg == 1) 
	        x *= -1d;
 
	    double k = (1d / ( 1d + 0.2316419 * x));
	    double y = (((( 1.330274429 * k - 1.821255978) * k + 1.781477937) *
	                   k - 0.356563782) * k + 0.319381530) * k;
	    y = 1.0 - 0.398942280401 * Math.exp(-0.5 * x * x) * y;
 
	    return (1d - neg) * y + neg * (1d - y);
	}
	
	// returns the implied daily volatility
	public static double getImpliedVolatility(int dtm, double stockPrice,
			double strikePrice, double interestRate, double optionPrice,
			String optionType){
		// initialize implied daily volatility
		double idv = 0.00001;
		
		if (optionType.equals("call")){ // it's a call
 
			// calculate initial values
			double denom = Math.log(stockPrice/strikePrice) + 
					((interestRate / 365) + Math.pow(idv, 2) / 2) * dtm;
			double durVol = idv * Math.sqrt(dtm);
			double dnd1 = CNDF(denom / durVol);
			double dnd2 = CNDF(denom / durVol - durVol);
			double tempCallPr = stockPrice * dnd1 -
					strikePrice * Math.exp(-interestRate * dtm / 365) * dnd2;
			
			// increment idv and loop until the call price converges
			// to the actual call price
			while (tempCallPr < optionPrice){
				idv += 0.00001;
				denom = Math.log(stockPrice/strikePrice) + 
						((interestRate / 365) + Math.pow(idv, 2) / 2) * dtm;
				durVol = idv * Math.sqrt(dtm);
				dnd1 = CNDF(denom / durVol);
				dnd2 = CNDF(denom / durVol - durVol);
				tempCallPr = stockPrice * dnd1 -
						strikePrice * Math.exp(-interestRate * dtm / 365) * dnd2;
			}
		} else{ // it's a put
			
			// calculate initial values
			double denom = Math.log(stockPrice/strikePrice) +
					((interestRate / 365) + Math.pow(idv, 2) / 2) * dtm;
			double durVol = idv * Math.sqrt(dtm);
			double dnd1 = CNDF(-denom / durVol);
			double dnd2 = CNDF(-denom / durVol + durVol);
			double tempCallPr = -stockPrice * dnd1 +
					strikePrice * Math.exp(-interestRate * dtm / 365) * dnd2;
			
			// increment idv and loop until the call price converges
			// to the actual call price
			while (tempCallPr < optionPrice){
				idv += 0.00001;
				denom = Math.log(stockPrice/strikePrice) +
						((interestRate / 365) + Math.pow(idv, 2) / 2) * dtm;
				durVol = idv * Math.sqrt(dtm);
				dnd1 = CNDF(-denom / durVol);
				dnd2 = CNDF(-denom / durVol + durVol);
				tempCallPr = -stockPrice * dnd1 +
						strikePrice * Math.exp(-interestRate * dtm / 365) * dnd2;
			}
		}
		
		return idv;
	}
	
	// returns the one-day time decay
	public static double getTimeDecay(int dtm, double stockPrice,
			double strikePrice, double interestRate, double optionPrice,
			String optionType, double idv){
		// assume no change in volatility or stock price
		// allow one day to pass
		dtm--;
		// compute the new option price
		double newOptPr = optionPrice;
		
		if (optionType.equals("call")){ // it's a call
 
			// calculate values from B-S model
			double denom = Math.log(stockPrice/strikePrice) + 
					((interestRate / 365) + Math.pow(idv, 2) / 2) * dtm;
			double durVol = idv * Math.sqrt(dtm);
			double dnd1 = CNDF(denom / durVol);
			double dnd2 = CNDF(denom / durVol - durVol);
			newOptPr = stockPrice * dnd1 -
					strikePrice * Math.exp(-interestRate * dtm / 365) * dnd2;
		} else{ // it's a put
			
			// calculate values from B-S model
			double denom = Math.log(stockPrice/strikePrice) +
					((interestRate / 365) + Math.pow(idv, 2) / 2) * dtm;
			double durVol = idv * Math.sqrt(dtm);
			double dnd1 = CNDF(-denom / durVol);
			double dnd2 = CNDF(-denom / durVol + durVol);
			newOptPr = -stockPrice * dnd1 +
					strikePrice * Math.exp(-interestRate * dtm / 365) * dnd2;
		}
		
		return optionPrice - newOptPr;
	}
}
