/*************************************************************************
 *  Compilation:  javac Item.java <br>
 *  Execution:    java Item <br>
 *************************************************************************/

/**
 * A simple data type Item for use in Project 2 (CSE 1325, Fall 2010)
 * <br>
 * Useful for creating Item objects to hold Infromation of an Item
 * Item ID, category, name, description, sale type, quanity, condition, minimum bid,
 * Increment, Reserve amount, Start date, number of days, seller ID, Feedback score
 * 
 * @author Scott Nidell 
 */
import java.io.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
public class Item implements Proj3Constants,DateConstants{
  
  private enum Condition{NEW,USED};
  private enum SaleType{FIX_PRICE,AUCTION,BOTH};
  
  private int itemID;
  private String category;
  private String name;
  private SaleType type;
  private int qty;
  private int qtySold=ZEROI; //qty sold increase after checkSold() is called
  private Condition condition;
  private double minStart;
  private double increment;
  private double reserveAmount;
  private DateTime startD;
  private int numDays;
  private int sellerID;
  private int feedback;
  private int shipID;
  private double finalValueFee=0;//tracks the final fee of items
  private String description;
  private ArrayList<Bid> bids = new ArrayList<Bid>();
  private DateTime endDate;
  private double insertionFee=0;//keeps track of insertion fee for item
  private double costCollected;//Keeps track of total cost insert+final value fee
  private NumberFormat dollars= NumberFormat.getCurrencyInstance(Locale.US);
  private boolean saleFinal=false;
  private double customerReturn=0;
  
  /**
  * Constructor: Default constructor that builds a Item
  * with default values.
  */
  
  public Item(){
   this.itemID=ZEROI;
   this.category=null;
   this.name=DEFAULT_ITEM;
   this.qty=ZEROI;
   this.condition= Condition.NEW;
   this.minStart= ZEROD;
   this.increment= ZEROD;
   this.reserveAmount=ZEROD;
   this.startD= new DateTime();
   this.numDays= ZEROI;
   this.sellerID=ZEROI;
   this.feedback=ZEROI;
   this.description=null;
  }
  
  /**
  * Constructs Item given data
  * 
  * @param id       id of the item
  * @param cat      category of item
  * @param n        name of item
  * @param ty       sale type of Item
  * @param qt       quantity of items to be sold
  * @param cond     condition in terms of  a string
  * @param minS     minimum start of auction
  * @param inc      increment of bids
  * @param reserveA amount of item
  * @param sd       Start Date
  * @param sid      Seller ID
  * @param fb       Seller Feedback
  * @param desc     Description of Item
  * 
  */
  public Item(int id, String cat, String n, String ty, int qt, String cond, double minS,
               double inc, double reserveA, String sd, int nd,int sid, int fb,String desc){
    
   
    this.itemID=id;
    this.category=cat;
    this.name=n;
    if(!isValidType(ty))
      throw new RuntimeException("Item 1: error not valid item type");
    this.type= SaleType.valueOf(ty.toUpperCase());
    this.qty=qt;
    if(!isValidCond(cond))
      throw new RuntimeException("Item 2: error not valid item condition");
    this.condition= condition.valueOf(cond.toUpperCase());
    this.minStart= minS;
    this.increment=inc;
    this.reserveAmount=reserveA;
    this.startD= new DateTime(sd);
    this.numDays=nd;
    this.sellerID=sid;
    this.feedback=fb;
    this.description=desc;
    this.endDate=new DateTime(sd);
    this.endDate.addDays(nd);
    costCollected= .01*minStart;//This calculates the insertion fee
    insertionFee= .01*minStart;//This calculates the insertion fee
  }
  
  /**
  * checks if a Sale type is valid
  * 
  * @return false if item does not fall in Enum sale type    
  * 
  */
  
  private static boolean isValidType(String t){
    
    if(t.toUpperCase().equals(FIXED)){
    	
     return true;
   }else if(t.toUpperCase().equals(AUCTION)){
     return true;
   }else if(t.toUpperCase().equals(BOTH_ITEM)){
     return true;
   }else
     return false; 
  }
  
  
  
  /**
  * checks if a condition type is valid
  * 
  * @return false if item does not fall in Enum condition    
  */
  private static boolean isValidCond(String c){
    
    if(c.toUpperCase().equals(NEW)){
     return true;
   }else if(c.toUpperCase().equals(USED)){
     return true;
   }
     return false;
  }
  /**
  * returns the final value feee
  * 
  * @return final fee 
  */
  public double getFinalValueFee() {
  return this.finalValueFee;
 }
    
  /**
  * creates new description for Item
  * 
  * @param d new description of the item  
  * 
  */
  public void setDescription(String d){
    
    this.description=d;    
  }
  
  /**
  * Change quantity of Item
  * 
  * @param q new quantity for the item
  *                
  */
  public void setQuanity(int q){
    
    this.qty=q;    
  }
  
   
  /**
  * Returns the Quantity sold of this Item
  *
  * 
  * @return 0 to Max of the quantity sold of this item
  */
  public int getQtySold(){
    
   return qtySold; 
  }
  
  /**
   *
   *adds quantity sold
   *@param numSold
   *
   */
  public void addQtySold(int numSold){
	  qtySold+=numSold;
  }
  
  /**
  * Returns the String representation of a SaleType object of this Item
  * 
  * @param b DateTime object
  * @return String of the SaleType 
  */
  public String getType(){
    String typeConversion=EMPTY_STRING;
        
    if(this.type==SaleType.FIX_PRICE){
      typeConversion=FIXED1;
    }else if(this.type==SaleType.AUCTION){
      typeConversion=AUCTION;     
    }else if(this.type==SaleType.BOTH){
      typeConversion=BOTH_ITEM;     
    }    
    return typeConversion;    
  }
  
   /**
  * Returns a arrayList of Bids used for calculating revenue
  * 
  * @return   returns array list of bids
  */
 public ArrayList getBids() {
  return this.bids;
 }
 
 /**
  *Returns reserve amount used for revenue calculation
  * 
  * @return   returns the reserve price of an item
  */
 public double getReserve() {
  return this.reserveAmount;
 }
 /**
  *Returns reserve amount used in rev calculation
  * 
  * @return   returns the reserve price
  */
 public void setFinalValueFee(double fee) {
  this.finalValueFee = fee;
 }
 
 /**
  * Gets quantity of items being sold
  * 
  * @return   returns the quantity of item
  */
 public int getQuantity() {
  return this.qty;
 }
 
 /**
  * Returns last bid of the bids array
  * 
  * @return  returns the last bid in the bids array list
  */
 public Bid getLastBid() {
  int lastIndex = (this.bids.size() - ONEI);
  return this.bids.get(lastIndex);
 }
 
 /**
  *returns start date of item 
  * 
  * @return the DateTime an items auction started
  */
 public DateTime getStartDate() {
  return this.startD;
 }
 /**
  *returns insertionFee collected
  * 
  * @return insertionFee
  */
 public double getInsertionFee() {
  return this.insertionFee;
 }
  
  /**
  * Adds a bid to the Arraylist
  * 
  * @param bid is the bid trying to be added
  */
  public void addBid(Bid bid){
    
	if(bids.size()>=MAX_BIDS){ //is bid less than 500?
      System.out.println("Max bids reached for this item");
      return;
    }else if(isValid(bid)){ //Is it valid?
      bids.add(bid);      
    }    
  }
  
  /**
  * Returns the ID of the Item
  *
  * @return Int of the current item ID
  */
  public int getItemID(){
   
    return this.itemID;    
  }
  /**
  * Returns the ID of the Seller
  *
  * @return Int of the current sellerID
  */
  public int getSellerID(){
     return this.sellerID;
  }
  
  /**
  * Checks to see if a Bid is Valid
  * 
  * @param b Bid object being validated
  * @return true DateTime,QTY,and TYPE correct
  */
  public  boolean isValid(Bid b){    
    
    //if bid QTY is not less than QTY available 
    if(!(b.getBidQty()<this.qty+1)){    	
    	System.out.println("Invalid bid quanity given "+b.getBidQty()+" available: "+this.qty);
    	return false;
    }    
    //then check to see if the date is within the start and end date 
    if(!(b.getBidDate().isBefore(this.endDate)&& b.getBidDate().isAfter(this.startD))){	       	 
	    System.out.println("Date not in range");
	    return false;        
	}        	 
    
    if(this.type==SaleType.FIX_PRICE){
    	return this.validFixed(b);
    }else if(this.type==SaleType.AUCTION){
    	return this.validAuction(b);
    }else if(this.type==SaleType.BOTH){
    	return this.validBoth(b);
    }
    System.out.println("Not valid bid Type");
    return false;
    
  }
  /**
   * 
   * @param b Bid being validated
   * @return boolean true if valid both bid
   * 
   */
  private boolean validBoth(Bid b){
	  int lastIndex=this.bids.size()-ONEI;
	  
	  
	  if(bids.size()==ZEROI){
		  //if the new bid amount is greater than or equal to minStart
		  if(b.getAmount()>=this.reserveAmount){
			  this.qty-=b.getBidQty();//because it is a valid item remove qty immediately
			  this.qtySold=b.getBidQty(); //add to sold itemsQty
			  //Calculate cost collected if bid is valid item is sold immediately
			  costCollected+= (.1*b.getBidAmount());
			  return true;
		  }
		  if(!(b.getAmount()>=minStart)){
			  System.out.println("Bid does not meet minimum bid ");
			  return false;
		  }else{
			  return true;
		  }
		  
	  }
	  
	      //There is a valid bid in bids array
	      //amount is greater than or equal to the reserve
		  if(b.getAmount()>=this.reserveAmount){
			  this.qty-=b.getBidQty();//because it is a valid item remove qty immediately
			  this.qtySold=b.getBidQty(); //add to sold itemsQty
			  //Calculate cost collected if bid is valid item is sold immediately
			  costCollected+= (.1*b.getBidAmount());
			  return true;
		  }else if(!(b.getAmount()>=bids.get(lastIndex).getAmount())){
			  System.out.println("Bid is not greater than previous bid");
			  return false;
		  }else if(!(b.getBidDate().isAfter(this.bids.get(lastIndex).getBidDate()))){
			  System.out.println("Bid date being added is before last valid bid");
			  return false;
		  }else{
			  return true;
			  //calc to total cost is done later with time involved
			  //does it meet reserve before time
		  }		  
	  
	  
	 
  }
  /**
   * Checks for valid AUCTION type
   *  
   * @param b bid being validated
   * @return true if valid auction item
   */
  private boolean validAuction(Bid b){
	  int lastIndex=this.bids.size()-ONEI;
	 
	  //if there are no bids
	  //System.out.println("size==ZERO? "+(bids.size()==ZEROI)+" Size: "+bids.size());
	  if(bids.size()==ZEROI){
		  //if the new bid amount is greater than or equal to minStart
		  if(!(b.getAmount()>=minStart)){
			  System.out.println("Bid does not meet minimum bid ");
			  return false;
		  }else{
			  return true;
		  }
		  
	  }else{//there is a current valid bid for item
		  //check if current bid amount is greater than last bid
		  if(!(b.getAmount()>=bids.get(lastIndex).getAmount())){
			  System.out.println("Bid is not greater than previous bid");
			  return false;
		  }else if(!(b.getBidDate().isAfter(this.bids.get(lastIndex).getBidDate()))){
			  System.out.println("Bid date being added is before last valid bid");
			  return false;
		  }else{
			  return true;
			  //calc to total cost is done later with time involved
			  //does it meet reserve before time
		  }		  
	  }
}

/**
   * Checks a for valid FIXED bid
   * @param b Current bid under validation
   * 
   * @return boolean false if not valid true if valid
   */
  private boolean validFixed(Bid b){   	  
	   
	//if the bidAmount is not greater than or equal to the reserve 
	if(!(b.getBidAmount()>=this.reserveAmount)){
	  System.out.println("Reserve not met. Invalid bid given: "+b.getBidAmount()+this.reserveAmount);
	  return false;	    	  
	} 
	  this.qty-=b.getBidQty();//because it is a valid item remove qty immediately
	  this.qtySold+=b.getBidQty(); //add to sold itemsQty
	  //Calculate cost collected if bid is valid item is sold immediately
	  costCollected+= (.1*b.getBidAmount());
	  customerReturn+= (b.getBidQty()*b.getBidAmount());//update customer revenue
	  return true;	  	  
  }
 
  
  
  /**
  * Prints all bids in the Current Item
  * 
  */
  public void printBids(PrintWriter foutput){
   
    foutput.println("ItemID: "+this.itemID+"|Description: "+this.description);
    foutput.println("   CurrentBids:");
    for(int i=0;i<bids.size();i++){
     foutput.println("   "+bids.get(i));
    }
  }
  /**
   * Prints all bids in the Current Item
   * 
   */
   public void printBid(){
    
     System.out.println("ItemID: "+this.itemID+"|Description: "+this.description);
     System.out.println("   CurrentBids:");
     for(int i=0;i<bids.size();i++){
    	 System.out.println("   "+bids.get(i));
     }
   }
  
  /**
  * Returns the ReserveAmount
  * 
  * @param double reserve amount
  */
  public double getReserveAmount(){
    return this.reserveAmount;
  }
  
  /**
  * Gets the End Date of the given Item
  *
  * @return String representation of End Date
  */
  public DateTime getEndDate(){
    return this.endDate;
  }
  
  /**
  * Check to See if any items have been sold in the time lapsed
  *
  * @return cost of fees collected
  */
  public double checkSold(){
   int lastIndex=this.bids.size()-ONEI;
    DateTime today= new DateTime();//get todays date
    //if if today is after the current bid and the bid amount is more than reserve +/- 5%
   if(today.isAfter(this.endDate) && bids.get(lastIndex).getBidAmount()>= (this.reserveAmount*.95)){
     this.qty-=bids.get(lastIndex).getBidQty();//reduce available quantity for bid
     this.qtySold+=bids.get(lastIndex).getBidQty();   //increase sold items  
   }
    
   return this.calcCost(qtySold); //update fees
  }
  
  /**
  * Calculates the cost of bids processed in the Item
  * 
  * @param it quantity of of this item sold
  * @return Cost calculation of the quantity passed
  */
  public double calcCost(int it){
    int lastIndex=this.bids.size()-ONEI;
    double cost=ZEROI;
    double insertionFee= this.minStart*ONE_PERCENT;
    double finalValFee=  this.bids.get(lastIndex).getBidAmount()*TEN_PERCERNT;//fee if item is sold
    
    if(this.qtySold==ONEI){//fee for 1 item sold
      cost+= (SHIPPING_COST +finalValFee);      
    }else if(this.qtySold>ONEI){//fee for multiple items sold
      cost+= (SHIPPING_COST +finalValFee+((this.qtySold-ONEI)*MULTIPLE_FEE));
    }
      cost+=insertionFee;//everyone gets an insertion fee
      
   return cost; 
  }
  
  /**
  * Returns the number of bids in the current Item
  * 
  * @return int number of bids
  */
  public int getNumBids(){    
   return bids.size(); 
  }
  
  /**
  * A helper method that prints by Reserve price for easier reading
  *
  * @return String representation more easily read with reserveAmount in Front
  */
  public String printByReserve(){
   return "ReserveAmount: "+this.reserveAmount+" ID: "+this.itemID+" Description: "+this.description;
  }
  
  /**
  * A helper method that gets minStartBid
  *
  * @return minStart bid
  */
  public double getMinStart(){    
   return this.minStart; 
  }
  
  /**
   * 
   * return double cost collected so far
   * 
   */
  public double getCostCollected(){
	    
	  return this.costCollected; 
  }
  /**
   * 
   * @return cost collected for the item
   * 
   */
  public double getValCostCollected(){
	  return this.costCollected;	  
  }
  /**
   * 
   * Adds cost of final sale price from checkSold() method in enterprise
   * 
   * @return sets cost collected
   */
  
  public void setCostCollected(double nCost){
	  
	  this.costCollected=nCost;
  }
  
  public boolean getSaleFinal(){  
  
	  return this.saleFinal;
  }
  /**
   * Sets the saleFinal key
   * 
   * @param saleFinal new value of saleFinal
   */
public void setSaleFinal(Boolean saleFinal) {
	this.saleFinal = saleFinal;
}

/**
  * Prints the current attributes of the object
  *   
  * @return string that describes the Item object.
  *                
  */
public void setCustomerReturn(double cr){
	
	customerReturn= cr;
}
/** * 
 * 
 * @return customers revenue
 */
public double getCustomerReturn(){
	return customerReturn;
}
/**
 * 
 * 
 * @return string rep of the description
 */
public String getDescription(){
	return this.description;
}

  public String toString(){
    
    return "SellerID "+this.sellerID+"{ID: "+this.itemID+"| Name: "+this.name+"| Description: "+this.description+"| QTY: "
      +this.qty+"|Condition: "+this.condition+" Auction TYPE: "+this.type+"|Min Start: "+this.minStart+"|Seller Feedback: "+this.feedback+"|StartDate: "+this.startD;   
  }
  
  public static void main(String []args){
    
    
     /*  
    //Testing bids FIXED Auction
    int itemID= 003;
    String itemCat= "Antique";
    String itName="Camera";
    String itemType= "FIX_PRICE";
    int itemQty= 5;
    String condition= "USED";
    double minStart= 30.00;
    double bidInc= 5.00;
    double reserveAmt= 400.00;
    String startD= "2-6-2014,21:49:00"; //Feb2-2014 9:49pm
    int days= 7;
    int sellerID= 200;
    int feedback=1000;
    String desc= "Old Ass Camera"; 
    
    Item myItem3= new Item(itemID,itemCat,itName,itemType,itemQty,condition,minStart,bidInc,reserveAmt,startD,days,sellerID,feedback,desc);
    
    System.out.println(myItem3);
    */
    /*****Adding FIXED bids to item*/////
    //Adding bid info
	  /*
    int userID1=011;
    int itemID1=003;
    DateTime bidDate1= new DateTime("2-12-2014,00:00:45");//Feb12-2014 midnight+45mins
    double bidAmount1=200.00;//too low of a bid
    int bidQTY1= 3;
    
    int userID2=011;
    int itemID2=003;
    DateTime bidDate2= new DateTime("2-11-2014,13:45:00");//Feb13-2014 1:45
    double bidAmount2=400.00;//first valid bid--new avail qty is 2
    int bidQTY2= 3;
    
    int userID3=011;
    int itemID3=003;;
    DateTime bidDate3= new DateTime("2-11-2014,11:00:00");//Feb-11-2014 11am
    double bidAmount3=400.00;
    int bidQTY3= 1; //second valid bid-- new avail qty is 1
    
    int userID4=011;
    int itemID4=003;
    DateTime bidDate4= new DateTime("2-11-2014,14:00:00");//Feb-11-2014 2pm
    double bidAmount4=233.00;
    int bidQTY4= 9;//too many
    
    Bid bid1= new Bid(userID1,itemID1,bidDate1,bidAmount1,bidQTY1);//invalid
    Bid bid2= new Bid(userID2,itemID2,bidDate2,bidAmount2,bidQTY2);//valid
    Bid bid3= new Bid(userID3,itemID3,bidDate3,bidAmount3,bidQTY3);//valid
    Bid bid4= new Bid(userID4,itemID4,bidDate4,bidAmount4,bidQTY4);//invalid
    
    System.out.println("Adding bid 1");
    myItem3.addBid(bid1);
    System.out.println("Cost: "+myItem3.getCostCollected());
    System.out.println("Adding bid 2");
    myItem3.addBid(bid2);
    System.out.println("Cost: "+myItem3.getCostCollected());
    System.out.println("Adding bid 3");
    myItem3.addBid(bid3);
    System.out.println("Cost: "+myItem3.getCostCollected());
    System.out.println("Adding bid 4");
    myItem3.addBid(bid4);
    System.out.println("Cost: "+myItem3.getCostCollected());
    
    System.out.println("*******TESTING AUCTION******* \n\n");
    
    */
    /*****Adding Auction Bids to item*/////
	  /*
    int itemID02= 004;
    String itemCat2= "Antique";
    String itName2="Camera";
    String itemType2= "AUCTION";
    int itemQty2= 5;
    String condition2= "USED";
    double minStart2= 30.00;
    double bidInc2= 5.00;
    double reserveAmt2= 400.00;
    String startD2= "2-6-2014,21:49:00"; //Feb6-2014 9:49pm
    int days2= 7;
    int sellerID2= 200;
    int feedback2=1000;
    String desc2= "Old Ass Camera"; 
    
    Item myItem4= new Item(itemID02,itemCat2,itName2,itemType2,itemQty2,condition2,minStart2,bidInc2,reserveAmt2,startD2,days2,sellerID2,feedback2,desc2);
    //Adding bid info
    int userID5=011;
    int itemID5=004;
    DateTime bidDate5= new DateTime("2-12-2014,00:00:45");//Feb12-2014 midnight+45mins
    double bidAmount5=20.00;//Doesn't meet minStart
    int bidQTY5= 3;
    
    int userID6=011;
    int itemID6=004;
    DateTime bidDate6= new DateTime("2-11-2014,13:45:00");//Feb11-2014 1:45
    double bidAmount6=220.00;//first valid bid
    int bidQTY6= 3;
    
    int userID7=011;
    int itemID7=004;
    DateTime bidDate7= new DateTime("2-11-2014,11:00:00");//Feb-11-2014 11am //Time before last valid bid invalid
    double bidAmount7=400.00;
    int bidQTY7= 1; //second valid bid-- new avail qty is 1
    
    int userID8=011;
    int itemID8=004;
    DateTime bidDate8= new DateTime("2-11-2014,14:00:00");//Feb-11-2014 2pm
    double bidAmount8=233.00;
    int bidQTY8= 1;//too many
    
    Bid bid5= new Bid(userID5,itemID5,bidDate5,bidAmount5,bidQTY5);
    Bid bid6= new Bid(userID6,itemID6,bidDate6,bidAmount6,bidQTY6);
    Bid bid7= new Bid(userID7,itemID7,bidDate7,bidAmount7,bidQTY7);
    Bid bid8= new Bid(userID8,itemID8,bidDate8,bidAmount8,bidQTY8);
    
    System.out.println("Adding bid 1");
    myItem4.addBid(bid5);
    myItem4.printBid();
    System.out.println("Adding bid 2");
    myItem4.addBid(bid6);
    myItem4.printBid();
    System.out.println("Adding bid 3");
    myItem4.addBid(bid7);
    myItem4.printBid();
    System.out.println("Adding bid 4");
    myItem4.addBid(bid8);
    myItem4.printBid();*/
    
    System.out.println("\n\n ******Adding BOTH bid******");
    
    /*****Adding BOTH Bids to item*/////
    int itemID03= 004;
    String itemCat3= "Antique";
    String itName3="Camera";
    String itemType3= "BOTH";
    int itemQty3= 5;
    String condition3= "USED";
    double minStart3= 30.00;
    double bidInc3= 5.00;
    double reserveAmt3= 400.00;
    String startD3= "2-6-2014,21:49:00"; //Feb6-2014 9:49pm
    int days3= 7;
    int sellerID3= 200;
    int feedback3=1000;
    String desc3= "Old Ass Camera"; 
    
    Item myItem5= new Item(itemID03,itemCat3,itName3,itemType3,itemQty3,condition3,minStart3,bidInc3,reserveAmt3,startD3,days3,sellerID3,feedback3,desc3);
    //Adding bid info
    int userID9=011;
    int itemID9=004;
    DateTime bidDate9= new DateTime("2-12-2014,00:00:45");//Feb12-2014 midnight+45mins
    double bidAmount9=20.00;//Doesn't meet minStart
    int bidQTY9= 3;
    
    int userID10=011;
    int itemID10=004;
    DateTime bidDate10= new DateTime("2-11-2014,13:45:00");//Feb11-2014 1:45
    double bidAmount10=220.00;//first valid bid
    int bidQTY10= 3;
    
    int userID11=011;
    int itemID11=004;
    DateTime bidDate11= new DateTime("2-11-2014,11:00:00");//Feb-11-2014 11am //Time before last valid bid invalid
    double bidAmount11=400.00;
    int bidQTY11= 1; //second valid bid-- new avail qty is 1
    
    int userID12=011;
    int itemID12=004;
    DateTime bidDate12= new DateTime("2-11-2014,14:00:00");//Feb-11-2014 2pm
    double bidAmount12=233.00;
    int bidQTY12= 1;//too many
    
    Bid bid9= new Bid(userID9,itemID9,bidDate9,bidAmount9,bidQTY9);
    Bid bid10= new Bid(userID10,itemID10,bidDate10,bidAmount10,bidQTY10);
    Bid bid11= new Bid(userID11,itemID11,bidDate11,bidAmount11,bidQTY11);
    Bid bid12= new Bid(userID12,itemID12,bidDate12,bidAmount12,bidQTY12);
    
    System.out.println("Cost: "+myItem5.getCostCollected());
    System.out.println("Adding bid 9");
    myItem5.addBid(bid9);
    System.out.println("Cost: after bid 9 "+myItem5.getCostCollected());
    myItem5.printBid();
    System.out.println("Adding bid 10");
    myItem5.addBid(bid10);
    System.out.println("Cost: after bid 10 "+myItem5.getCostCollected());
    myItem5.printBid();
    System.out.println("Adding bid 11");
    myItem5.addBid(bid11);
    System.out.println("Cost: after bid 11"+myItem5.getCostCollected());
    myItem5.printBid();
    System.out.println("Adding bid 12");
    myItem5.addBid(bid12);
    System.out.println("Cost: after bid 12"+myItem5.getCostCollected());
    myItem5.printBid();
    
  }
  
}