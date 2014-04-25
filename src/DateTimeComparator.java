/*************************************************************************
 *  Compilation:  javac DateTimeComparator.java <br>
 *  Execution:    java DateTimeComparator <br>
 *************************************************************************/

/**
 * A simple comparator for bids to compare bid Dates in descending order (most recent bid
 * to latest bid)
 * 
 * @author Scott Nidell
 * 
 */
import java.util.Comparator;


public class DateTimeComparator implements Comparator <Bid>{
	
	/**
	 * 
	 * 
	 * @param b1 first bid calling compare
	 * @param b2 second bid being compare to first bid
	 * @return 0 if this DateTime is the same as DateTime b <br>
     *         negative integer if this DateTime is earlier than DateTime b <br>
     *         positive integer if this DateTime is after DateTime b
	 */
	
	public int compare(Bid b1, Bid b2){
		
		return -b1.getBidDate().compareTo(b2.getBidDate());
	}

}
