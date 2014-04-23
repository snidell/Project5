/*************************************************************************
 *  Compilation:  javac Bid.java <br>
 *  Execution:    java Bid <br>
 *  Created By: Scott Nidell 3/15/2014
 *************************************************************************/

/**
 * A simple data type Item for use in Project 2 (CSE 1325, Fall 2010)
 * <br>
 * Useful for creating Bid objects to hold Infromation of a Bid
 * user, item, bid Amount
 * 
 * @author Scott Nidell 
 */

import java.math.*;
import java.text.*;
import java.util.*;

public class Bid implements Proj3Constants,DateConstants{
  
  private int userID;
  private int itemID;
  private DateTime bidDate;
  private double bidAmount;
  private int bidQTY;
  private DecimalFormat three= new DecimalFormat("#000");
  
  
  
  /**
  * Constructor: Creates a bidding object
  * 
  * @param uID         User ID who is bidding
  * @param iID         Item id that the bid belongs to
  * @param bidDate     Date the bid was placed
  * @param bidAmt      Amount the bid will be placed for
  * @param qty         Quantity of items to be bid on
  * @exception RuntimeException
  *                if the date is invalid
  */
  public Bid(int uID,int iID, DateTime bidDate, double bidAmt,int qty){
    
    this.userID=uID;
    this.itemID=iID;
    this.bidDate= bidDate;
    this.bidAmount=bidAmt;
    this.bidQTY=qty;      
  }
  
  
  
/**
  * Getter for itemID
  * 
  * @return int itemIDs
  */
  public int getItemID(){
   
    return this.itemID;
  }  
  
  /**
  * Getter for BidAmount
  * 
  * @return double current bid amount
  */
  public double getBidAmount(){
   
    return this.bidAmount;
  }
  
  /**
  * Getter bid quanity
  * 
  * @return int quanity of bid
  */
  public int getBidQty(){
    
    return this.bidQTY;
  }
  
  /**
  * Getter for bid date
  * 
  * @return DateTime of the bid that is trying to be placed
  */
  public DateTime getBidDate(){
   
    return this.bidDate;
  }
  
  
  /**
  * Getter for bid year
  * 
  * @return returns year of bid
  */
  public int getYear(){
    
    return bidDate.getYear();
  }
  
  /**
  * Getter for bid amount
  * 
  * @return bid amount
  */
  public double getAmount(){
    
    return this.bidAmount;
  }
  /**
   * Getter for qty amount
   * 
   * @return quanity of bid
   */
  public int getQuantity(){
   return this.bidQTY;
  }
  /**
   * Getter for user ID amount
   * 
   * @return int for userID 
   */
  public int getUserID(){
	  return this.userID;
  }
    
  
  /**
  * Overloaded toString method
  * 
  * @return String that represents a bidding object
  */
  public String toString(){
    
    String formatID=three.format(this.userID);
   
    return "UserID: "+formatID+"|ItemID: "+itemID+"|Bid Date: "+this.bidDate+"|Bid Amount: "
      +this.bidAmount+"|Bid QTY: "+this.bidQTY;
  }
  
  public static void main(String []args){
    
  /********Various test cases******/
  int userID1=011;
  int itemID1=100;
  DateTime bidDate1= new DateTime("2-12-2014,00:00:45");
  double bidAmount1=200.00;
  int bidQTY1= 3;
  
  int userID2=012;
  int itemID2=120;
  DateTime bidDate2= new DateTime("2-13-2014,13:45:00");
  double bidAmount2=203.00;
  int bidQTY2= 3;
  
  int userID3=013;
  int itemID3=110;
  DateTime bidDate3= new DateTime("2-11-2014,11:00:00");
  double bidAmount3=900.00;
  int bidQTY3= 1;
  
  int userID4=014;
  int itemID4=190;
  DateTime bidDate4= new DateTime("2-11-2014,14:00:00");
  double bidAmount4=233.00;
  int bidQTY4= 9;
  
  Bid bid1= new Bid(userID1,itemID1,bidDate1,bidAmount1,bidQTY1);
  Bid bid2= new Bid(userID2,itemID2,bidDate2,bidAmount2,bidQTY2);
  Bid bid3= new Bid(userID3,itemID3,bidDate3,bidAmount3,bidQTY3);
  Bid bid4= new Bid(userID4,itemID4,bidDate4,bidAmount4,bidQTY4);
  
  System.out.println(bid1);
  System.out.println(bid2);
  System.out.println(bid3);
  System.out.println(bid4);
  

    
    
  }
  
}