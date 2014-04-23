/*************************************************************************
 *  Compilation:  javac Enterprise.java <br>
 *  Execution:    java Enterprise <br>
 *************************************************************************/

/**
 * A simple data type Enterprise for use in Project 2 (CSE 1325, Fall 2010)
 * <br>
 * Useful for creating Enterprise Objects  to hold Infromation of a Customers, employees
 * bids, name of enterprise, 
 * 
 * @author Scott Nidell 
 */

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
public class Enterprise implements Proj3Constants, DateConstants{
  
 private String name; //name of enterprise
 private ArrayList<Item> items=new ArrayList<Item>(); //items currently up for auction
 private ArrayList<Item> soldItems=new ArrayList<Item>(); //items sold
 private ArrayList<SalariedEmp> employees= new ArrayList<SalariedEmp>(); //stores employees currently employed
 private ArrayList<SalariedEmp> releasedEmployees = new ArrayList<SalariedEmp>();//stores canned employees
 private ArrayList<Customer> customers= new ArrayList<Customer>(); //stores customers of company
 private NumberFormat dollars= NumberFormat.getCurrencyInstance(Locale.US); //to make money pretty
 private enum SaleType{FIX_PRICE,AUCTION,BOTH};
 private int numEmployees=ZEROI; //current number of employees
 private int totalItemsSold=0;  //items sold by enterprise
 private double totalFees; //total fees collected 
 private double revenue;
 
 
 
 /**
  * Constructor: Creates a enterprise object that holds customers, employees, items, and bids
  * 
  * @param nm The name of the Enterprise
  */
 Enterprise(String nm){
   this.name=nm; 
   items=new ArrayList<Item> ();
   employees= new ArrayList<SalariedEmp>();
   customers= new ArrayList<Customer>();  
   
 }
 /**
  * Adds an employee to ArrayList
  * 
  * @param e Employee to be added
  */
 public void addEmployee(SalariedEmp e){
   
  
  if(e.getReleaseDate()==null){
    numEmployees++;
    employees.add(e);
  }else{
    System.out.println("Employee has a non-null release date, employee has been added releaseEmployees");
    releasedEmployees.add(e);
  }
 }
 
 /**
  * Adds an item to the ArrayList
  * 
  * @param it Item to be added to the list
  */
 public boolean addItem(Item it){
  
   for(int i=0;i<items.size();i++){
     if(it.getItemID()==items.get(i).getItemID()){
       System.out.println("ID currently in use choose a unique ID");
       return false;
     }
   }
   items.add(it);
   return true;   
 } 
 
 /**
  * Adds customers to ArrayList
  * 
  * @param c The Customer to be added
  */
 public void addCustomer(Customer c){
  
   //check for redundant ID
   for(int i=0;i<customers.size();i++){
     if(c.getID()==customers.get(i).getID()){
       System.out.println("ID currently in use choose a unique ID");       
     }
   }
   customers.add(c); 
 }
 
 /**
  * Releases a employee if it is not already released or exists
  * 
  * @param eid Employees ID to be released
  */
 public void releaseEmployee(int eid, String rdate){   
   int flag=0;
   SalariedEmp temp;
   //check active employees 
   
   for(int i=0;i<employees.size();i++){       
      if(employees.get(i).getID()==eid){
        if(employees.get(i).getReleaseDate()==null){
          employees.get(i).release(rdate);
          System.out.println("Employee has been found and released: "+employees.get(i));
          temp=employees.get(i);
          releasedEmployees.add(temp);//move this employee to releasedEmployees
          employees.remove(i);//remove from active employees
          numEmployees--;
          return;
        }                
      }      
    }
      //Check released Employees this person may have been released already
      for(int i=0;i<releasedEmployees.size();i++){
        if(releasedEmployees.get(i).getID()==eid){
        System.out.println("Employee Has already been released");
         System.out.println(releasedEmployees.get(i));
         return; 
        }
      }
      //Finally if its not in either let the user know
      System.out.println("Employee ID not found");  
 }
 /**
  * Prints the employee info with length of service
  * @param foutput PrintWriter to create new lines in file
  */
 public void printLenghtOfService(SalariedEmp e, PrintWriter foutput){
   foutput.println("------Newly Added Employee----------");
   foutput.println(e+" Age: "+e.age()+" Length of Service (In Years) "+e.lengthOfService());
   foutput.println("\n");
 }
 
 /**
  * Prints the employee info with length of service
  * @param eid employee ID
  * @param foutput PrintWriter to create new lines in file
  */
 public void printLenghtOfService(int eid,PrintWriter foutput){
   if(eid==ZEROI){
    for(int i=0;i<employees.size();i++){
    foutput.println(employees.get(i)+" Age: "+employees.get(i).age()+" Length of Service "
                         +employees.get(i).lengthOfService());
    }
   }else{
    for(int i=0;i<employees.size();i++){
      if(eid==employees.get(i).getID()){
        foutput.println(employees.get(i)+" Age: "+employees.get(i).age()+" Length of Service "
                         +employees.get(i).lengthOfService());
      }
    }
   }
 }
 
 
 /**
  * Prints all current Employees
  * @param foutput PrintWriter to create new lines in file
  * 
  */
 public void printEmployees(PrintWriter foutput){
  foutput.println("\n"); 
   foutput.println("-----Employees currently employed"); 
  for(int i=0;i<employees.size();i++)
    foutput.println(employees.get(i));
   
 }
 
 /**
  * Prints all past Employees
  * @param foutput PrintWriter to create new lines in file
  * 
  */
 public void printPastEmployees(PrintWriter foutput){
  foutput.println("\n"); 
   foutput.println("----Employees who have been terminated"); 
  for(int i=0;i<releasedEmployees.size();i++)
    foutput.println(releasedEmployees.get(i));
   
 }
 
 /**
  * Prints all Items of the Enterprise
  * 
  */
 public void printItems(){
  
   for(int i=0;i<items.size();i++)    
     System.out.println(items.get(i));
   }
 /**
  * Prints all Items of the Enterprise
  * 
  */
 public void printItemsSold(){
   System.out.println(" Size of Sold Item: "+soldItems.size());
   for(int i=0;i<soldItems.size();i++){
	  System.out.println(soldItems.get(i));
	  System.out.println("QTY SOLD: "+soldItems.get(i).getQtySold()+" Price Sold At: "+soldItems.get(i).getLastBid().getAmount());
	  System.out.println("Customer Return: "+soldItems.get(i).getCustomerReturn());
   }
     
   }
 
 /**
  * Prints all items given a lower bound range cost amount
  * 
  * @param val the lower bound range value
  */
 public void printItemsRange(int val){
   
   for(int i=0;i<items.size();i++){ 
     if(val<=items.get(i).getReserveAmount())
     System.out.println(items.get(i).printByReserve());
   }   
 }
  /**
  * Given Seller ID print all items associated with
  * seller ID
  * 
  * @param id Item Id to be printed bids
  * @param foutput PrintWriter to create new lines in file
  */
 public void printSellerItems(Item it, PrintWriter foutput){
   int occurence=ZEROI;
   foutput.println("\n----------");
   foutput.println("Current Items being sold by Seller:"+ it.getSellerID());
   for(int i=0;i<items.size();i++){
     if(items.get(i).getSellerID()==it.getSellerID()){
       occurence++;
       foutput.println("  Item "+occurence+"| "+items.get(i));
     }
   }
   foutput.println("\n\n");
   if(occurence==ZEROI){
     foutput.println("No items found for seller: "+it.getSellerID());
   }
 }
 
 /**
  * Prints all customers in the Enterprise
  *@param foutput PrintWriter to create new lines in file
  */
 public void printCustomers(PrintWriter foutput){
    foutput.println("----Printing All Current Customers----");
     for(int i=0;i<customers.size();i++)
       foutput.println(customers.get(i));
     foutput.println("\n\n");
 }
 
  /**
  * Adds a bid to item in the list
  * 
  * @param bid Bid to be added to Item List
  */
 public void addBid(Bid bid){
    
    int flag=0;
    for(int i=0;i<items.size();i++){
      if(items.get(i).getItemID()==bid.getItemID()){
        items.get(i).addBid(bid);
        flag++;
        return;
      }      
    }   
    if(flag==0)
      System.out.println("Item ID not found");
  }
 
  /**
  * Prints total Fees taken in dollar format
  * 
  */
 public void totalFees(){
    double totalCost=0;
    
    for(int i=0;i<items.size();i++){
     totalCost+= items.get(i).checkSold();
    }
    String dollarCost=dollars.format(totalCost);
    System.out.println("Total Fees collected: "+dollarCost);
  }
 
  /**
  * Print all bids given an Item ID
  * 
  * @param id Item Id to be printed bids
  * @param foutput Printwriter to print to file
  */
 public void printBids(int id, PrintWriter foutput){
   
    for(int i=0;i<items.size();i++){
      
      if(items.get(i).getItemID()==id){
        items.get(i).printBids(foutput);
        return;
      }
    }
  }
 
/**
  * Checks if an item was sold
  * if sold it moves item to itemsSold ArrayList
  * //FIXED items calculation are done just need to put item into sold array
	  if(items.get(i).getType().toUpperCase().equals(FIXED1)){
		  if(items.get(i).getQtySold()>0){
			  items.get(i).setSaleFinal(true);
			  soldItems.add(items.get(i));
		  } 
	  }
  
  */// 
 
public void checkSold() {
	DateTime today= new DateTime();
  //loop through items
  for(int i=0; i<items.size(); i++) {
	  
	  //cost is calculated at bid time for FIXED items so just check Both or Auction items
	  if(items.get(i).getType().toUpperCase().equals(AUCTION)|| items.get(i).getType().toUpperCase().equals(BOTH_ITEM)){
	  //If last bid is not 95percent or higher of the reserve and time is up ie. today is After the end date
	      //if bids =zero than don't bother checking sold.
		  if(!(items.get(i).getNumBids()==ZEROI)){
		  if(!(items.get(i).getLastBid().getAmount()>=(.95*items.get(i).getReserve())) && today.isAfter(items.get(i).getEndDate())){
	    	  //This sale lost its chance to be sold auction is set to final and auction is closed
			  items.get(i).setSaleFinal(true);
	      }
		  //if the bid is greater than 95% of reserve AND today is After end of Auction AND the sale is not final yet
		  if(items.get(i).getLastBid().getAmount()>=(.95*items.get(i).getReserve()) &&today.isAfter(items.get(i).getEndDate())&&!items.get(i).getSaleFinal() ){
			  //now that the bids are valid take the finalCost amount to cost Collected
			  items.get(i).setCostCollected(.1*items.get(i).getLastBid().getAmount());
			  items.get(i).setSaleFinal(true);
			  items.get(i).setQuanity((items.get(i).getQuantity()-items.get(i).getLastBid().getBidQty())); //set QuanityAvail=previousQTY-bidQTY
			  items.get(i).addQtySold(items.get(i).getLastBid().getBidQty());//update QtySold			  
			  items.get(i).setCustomerReturn(items.get(i).getQtySold()*items.get(i).getLastBid().getAmount());//update the customer profit.			  
			  //move item to sold array
			  soldItems.add(items.get(i));
			  
		  } 
	  }
       
	  }else if(items.get(i).getType().toUpperCase().equals(FIXED1)&& items.get(i).getQtySold()>0 && !items.get(i).getSaleFinal()){
		  items.get(i).setSaleFinal(true);		  	  
		  soldItems.add(items.get(i));		  
	  }    
   }  
 }
 
/**
  * gets revenue after the items have been checked
  *  
  * @param foutput Print writer to print to file
  */
public void getRevenue(int year, String type,PrintWriter foutput){
  
  revenue = 0;
  double dInsertion=0.00;
  double dCostCollected=0.00;
  
  //if all sold items
  if(type.equals("*")){
	  foutput.println("---------ALL type Revenue---------");
	  for(int i=0;i<soldItems.size();i++){		  
		  //If the year is valid print that item.
		  if((soldItems.get(i).getStartDate().getDate().getYear())==year){			  
			  foutput.println("--------Revenue-------");
			  //Format the output
			  foutput.println(soldItems.get(i));
			  dInsertion=soldItems.get(i).getInsertionFee();
			  dCostCollected=soldItems.get(i).getCostCollected();
			  String sInsertion = dollars.format(dInsertion);
			  String sCostCollected=dollars.format(dCostCollected);
			  //Print Output
			  foutput.println("Insertion Fee "+sInsertion+ " totalCost "+sCostCollected);			  
			  revenue+= soldItems.get(i).getInsertionFee()+soldItems.get(i).getCostCollected();
			  String num = dollars.format(revenue);
			  foutput.println("Revenue: "+num);
		  }
	  }
  }
  //if fixed sold items
  if(type.toUpperCase().equals(FIXED1)){
	  foutput.println("---------FIXED type Revenue---------");
	  for(int i=0;i<soldItems.size();i++){
		  //if it is the right year and a fixed product print it.
		  if((soldItems.get(i).getStartDate().getDate().getYear())==year && soldItems.get(i).getType().toUpperCase().equals(FIXED1)){
			  foutput.println("--------Revenue-------");
			  //Format the output
			  foutput.println(soldItems.get(i));
			  dInsertion=soldItems.get(i).getInsertionFee();
			  dCostCollected=soldItems.get(i).getCostCollected();
			  String sInsertion = dollars.format(dInsertion);
			  String sCostCollected=dollars.format(dCostCollected);
			  //Print Output
			  foutput.println("Insertion Fee "+sInsertion+ " totalCost "+sCostCollected);			  
			  revenue+= soldItems.get(i).getInsertionFee()+soldItems.get(i).getCostCollected();
			  String num = dollars.format(revenue);
			  foutput.println("Revenue: "+num);
		  }
	  }
  }
  //if Auction Item
  if(type.toUpperCase().equals(AUCTION)){
	  foutput.println("---------Auction type Revenue---------");
	  for(int i=0;i<soldItems.size();i++){
		  if(soldItems.get(i).getStartDate().getDate().getYear()==year && soldItems.get(i).getType().toUpperCase().equals(AUCTION)){
			  foutput.println("--------Revenue-------");
			  //Format the output
			  foutput.println(soldItems.get(i));
			  dInsertion=soldItems.get(i).getInsertionFee();
			  dCostCollected=soldItems.get(i).getCostCollected();
			  String sInsertion = dollars.format(dInsertion);
			  String sCostCollected=dollars.format(dCostCollected);
			  //Print Output
			  foutput.println("Insertion Fee "+sInsertion+ " totalCost "+sCostCollected);			  
			  revenue+= soldItems.get(i).getInsertionFee()+soldItems.get(i).getCostCollected();
			  String num = dollars.format(revenue);
			  foutput.println("Revenue: "+num); 
		  }
	  }
  }
  
  if(type.toUpperCase().equals(BOTH_ITEM)){
	  foutput.println("---------BOTH type Revenue---------");
	  for(int i=0;i<soldItems.size();i++){
		  if(soldItems.get(i).getStartDate().getDate().getYear()==year && soldItems.get(i).getType().toUpperCase().equals(BOTH_ITEM)){
			  foutput.println("soldItems "+soldItems.get(i));
			  
			  foutput.println("--------Revenue-------");
			  //Format the output
			  foutput.println(soldItems.get(i));
			  dInsertion=soldItems.get(i).getInsertionFee();
			  dCostCollected=soldItems.get(i).getCostCollected();
			  String sInsertion = dollars.format(dInsertion);
			  String sCostCollected=dollars.format(dCostCollected);
			  //Print Output
			  foutput.println("Insertion Fee "+sInsertion+ " totalCost "+sCostCollected);			  
			  revenue+= soldItems.get(i).getInsertionFee()+soldItems.get(i).getCostCollected();
			  String num = dollars.format(revenue);
			  foutput.println("Revenue: "+num);
		  }
	  }
  }
 }
/**
  * Gets the items sold by person ID and that year
  *   
  * @param year  the year that items were sold
  * @param sid the seller ID
  * @param foutput Print writer to print to file
  */
  public String soldItemsBySeller(int year, int sid){
   String revDollars = dollars.format(revenue);
   revenue = 0;
   String returnString="";
   //Loop through items currently not sold
   returnString+="-------Items Not Sold by SellerID: "+sid+"--------\n";
   for(int i=0;i<items.size();i++){
	   //if it matches seller ID print it
	   if(sid==items.get(i).getSellerID()){
		   returnString+=items.get(i)+"\n";
	   }
   }
   //now find the items that were sold by that Seller
   returnString+="-------Items Sold by SellerID: "+sid+"--------\n";
   for(int i=0;i<soldItems.size();i++){
	   if(sid==soldItems.get(i).getSellerID()){
		   returnString+=soldItems.get(i)+"\n";
		   //add each items customer return to revenue for total
		   revenue+= soldItems.get(i).getCustomerReturn(); 
	   }	   
   }   
   returnString+="Revenue for ID: "+sid+" "+revDollars+"\n";
   return returnString;
  }
  

 /**
  * prints all Bids for all Items
  * @param foutput PrinterWriter to write to file
  */ 
 public void printAllBids(PrintWriter foutput){
   
    for(int i=0;i<items.size();i++){ 
      
        items.get(i).printBids(foutput);        
    }    
  }
 
 /**
  * Prints Main menu for User Interface
  *
  */
 public void printMenu(){
    System.out.println(this);
    System.out.println("Welcome to MavBay!");
    System.out.println("Main Menu:");
    System.out.println(  "1) List all employees");
    System.out.println(  "2) List items");
    System.out.println(  "3) List all customers");
    System.out.println(  "4) Display items sold");
    System.out.println(  "5) Display total fee collection");
    System.out.println(  "6) Display Items that received max bid");
    System.out.println(  "7) Release an employee");
    System.out.println(  "0) Exit Program");    
  }
 
  /**
  * Prints a sub menu for items
  * 
  */
  public void itemsMenu(){
    
    Scanner scan= new Scanner(System.in);
    String menuScan=EMPTY_STRING;
    
    System.out.println("Which items do you want to Display?");
    System.out.println("Type:* for all items");
    System.out.println("Give an amount and auction equal to or over that amount will be printed");
    
    menuScan=scan.next();
    int itemValue;
    
    if(menuScan.equals("*")){
      System.out.println("Printing All Items...");  
      this.printItems();
                 
    }else{
        try {
          itemValue = Integer.parseInt(menuScan);
          printItemsRange(itemValue);
          
          
        }catch (Exception e){       
          this.itemsMenu();
        }
    }
  }
  
  /**
  * Prints the Max Number of bids and the Item with that max bid
  * 
  */
  public void maxBid(){
   int maxBid=ZEROI;
   int index=ZEROI;
    for(int i=0;i<items.size();i++){     
      if(items.get(i).getNumBids()>maxBid){
        maxBid=items.get(i).getNumBids();
        index=i;
      }
    } 
   System.out.println("Item with most amount of Bids: "+items.get(index));
   System.out.println("Number of Bids: "+maxBid);
  }
  
 /**
  * Sub Menu to release and Employee
  *
  */
  public void releaseEmpMenu(){
   
   Scanner scan= new Scanner(System.in);
   String menuScan=EMPTY_STRING;
   int empIDSelection=ZEROI;
   
   System.out.println("Please enter an Emplyee ID to release");
   menuScan=scan.next();
   try {
          empIDSelection = Integer.parseInt(menuScan);
          this.releaseEmployee(empIDSelection, "*");
          
        }catch (Exception e){       
          System.out.println("Invalid input");
          this.releaseEmpMenu();
        }
    
  }
  
  
 
  /**
  * Prints items sold for proj2
  *
  */
  public void soldItemsMenu(String type, int year){
   
   
   System.out.println("1");
   //for(int i=0;i<items.size();i++){//time has eslapsed check to see if anything new has been sold
     //items.get(i).checkSold(); 
   //}   
   System.out.println("2");
   if(type.equals("*")){
     
     for(int i=0;i<items.size();i++){    
       if(this.items.get(i).getQtySold()>=ONEI){//if an item has been sold print it
         System.out.println(this.items.get(i));
       }
     }
     
   }else if(type.toUpperCase().equals(FIXED)){
     for(int i=0;i<items.size();i++){    
       if(this.items.get(i).getQtySold()>=ONEI && this.items.get(i).getType().equals(FIXED)){
         //if the item has been sold and its a Fixed Price. Print it.
         System.out.println(this.items.get(i));
       }
     }
     
   }else if(type.toUpperCase().equals(AUCTION)){
     for(int i=0;i<items.size();i++){    
       if(this.items.get(i).getQtySold()>=ONEI && this.items.get(i).getType().equals(AUCTION)){
       //if the item has been sold and its a Auction Price. Print it.
        System.out.println(items.get(i));
        }
      }     
   }else if(type.toUpperCase().equals(BOTH_ITEM)){
     for(int i=0;i<items.size();i++){
       if(this.items.get(i).getQtySold()>=ONEI && this.items.get(i).getType().equals(BOTH_ITEM)){
         System.out.println(items.get(i));
       }       
     }    
   }else{
    System.out.println("Invalid selection");    
   }
  }
  /**
  * Given Employee ID and overtime rate calculate salary of given Employee
  * 
  * @param eid Employee ID
  * @param otr overTime rate 
  */
  public void getSalary(int eid, int otr,int month,PrintWriter foutput){
    foutput.println("-----Calculating Salary");
    for(int i=0;i<employees.size();i++){       
      if(employees.get(i).getID()==eid){
        foutput.println(employees.get(i).printCalc(month)+"Monthly Salary: "+employees.get(i).computeSalary(otr));
        
        }                
      }    
  }
  
  public String getName(){
	  return name;
  }
                         
  /**
  * A String method that represents a Enterprise Object
  * 
  * @return Returns a string of Enterprise Object
  */
  public String toString(){
    
    return "Enterprise: "+name+"Employees: "+numEmployees;     
  } 
  
  
  
  
 /*****************Added for GUI interaction**********************/
  /**
   * 
   * 
   * 
   * @return String[] of Emp ID First and Last Name of employee by index
   */
  public String [] empList(ArrayList<SalariedEmp> emp){
	  String temp="";
	  String[] stringedEmpList= new String[emp.size()];
	  for(int i=0;i<stringedEmpList.length;i++){
		  temp= Integer.toString(emp.get(i).getID())+"    "+emp.get(i).firstName+" "+emp.get(i).lastName;
		  stringedEmpList[i]=temp;
	  }	  
	  return stringedEmpList;
  }
  /**
   * 
   * 
   * 
   * @return String[] of Item ID First and Description
   */
  public String [] itemList(ArrayList<Item> it){
	  String temp="";
	  String[] stringedItemList= new String[it.size()];
	  for(int i=0;i<stringedItemList.length;i++){
		  temp= Integer.toString(it.get(i).getItemID())+"    "+it.get(i).getDescription();
		  stringedItemList[i]=temp;
	  }	  
	  return stringedItemList;
  }
  
  /**   
   * 
   * @return String representation of most tenure employees
   */
  public String empLength(){
	  String temp="";
	  int lengthOfService=-1;
	  
	  for(int i=0;i<employees.size();i++){
		  //Get the greatest length of service
		  if(employees.get(i).lengthOfService()>lengthOfService){
			  lengthOfService=employees.get(i).lengthOfService();
		  }
	  }
	  for(int i=0;i<employees.size();i++){
		  if(employees.get(i).lengthOfService()==lengthOfService){
			  temp+= "ID: "+employees.get(i).getID()+" "+employees.get(i).firstName+" "
		  +employees.get(i).lastName+" Years Served "+employees.get(i).lengthOfService()+ "\n";
		  }
	  }
	  System.out.println("Employees: \n"+temp);
	  return temp;
  }
  
  /**
   * 
   * @return returns current employed employees
   */
  public ArrayList<SalariedEmp> getEmployees(){
	  return employees;
  }
  
  /**
   * 
   * @return returns released employed employees
   */
  public ArrayList<SalariedEmp> getPastEmployees(){
	  return releasedEmployees;
  }
  
  /**
   * 
   * @return returns current employed employees
   */
  public ArrayList<Customer> getCustomers(){
	  return customers;
  }
  /**
   * 
   * 
   * @return Arraylist of current items not sold yet
   */
  public ArrayList<Item> getItems(){
	  return this.items;
  }
  
  /**
   * 
   * 
   * @return Arraylist of sold items
   */
  public ArrayList<Item> getItemsSold(){
	  return this.soldItems;
  }
 
  
  /**
   * 
   * 
   * @param index   index of the employee in the current Employees
   * @return string  the String value of that employee.
   * 
   */  
  public String sEmpInfo(int index){
	  
	  return this.employees.get(index).toString();
  }
  
  /**
   * 
   * 
   * @param index   index of the employee in the current Employees
   * @return string  the String value of that employee.
   * 
   */  
  public String sRelEmpInfo(int index){
	  
	  return this.releasedEmployees.get(index).toString();
  }
  
  /**
   *
   * @param String year to be calculated
   */
  public String sYearRevenue(String year){
	  String temp="";
	  revenue = 0;
	  String syear="";
	  double dInsertion=0.00;
	  double dCostCollected=0.00;
	  temp+="--------Revenue-------\n";
	  if(year.equals("All")){
		  for(int i=0;i<soldItems.size();i++){		  
			//If the year is valid print that item.
			 syear=Integer.toString(soldItems.get(i).getStartDate().getDate().getYear());  
			//Format the output
			temp+=soldItems.get(i)+"\n";
			dInsertion=soldItems.get(i).getInsertionFee();
			dCostCollected=soldItems.get(i).getCostCollected();
			String sInsertion = dollars.format(dInsertion);
			String sCostCollected=dollars.format(dCostCollected);
			//Print Output
			temp+="Insertion Fee "+sInsertion+ " totalCost "+sCostCollected+"\n";			  
			revenue+= soldItems.get(i).getInsertionFee()+soldItems.get(i).getCostCollected();
				
		  }
		  String num = dollars.format(revenue);
		  temp+="\nRevenue: "+num;
		  return temp;
	  }
	  
	  
	  for(int i=0;i<soldItems.size();i++){		  
		  //If the year is valid print that item.
		  syear=Integer.toString(soldItems.get(i).getStartDate().getDate().getYear());
		  if(syear.equals(year)){			  
			  
			  //Format the output
			  temp+=soldItems.get(i)+"\n";
			  dInsertion=soldItems.get(i).getInsertionFee();
			  dCostCollected=soldItems.get(i).getCostCollected();
			  String sInsertion = dollars.format(dInsertion);
			  String sCostCollected=dollars.format(dCostCollected);
			  //Print Output
			  temp+="Insertion Fee "+sInsertion+ " totalCost "+sCostCollected+"\n";			  
			  revenue+= soldItems.get(i).getInsertionFee()+soldItems.get(i).getCostCollected();
			  
			  
		  }
	  }
	  String num = dollars.format(revenue);
	  temp+="\nRevenue: "+num;
	  return temp;
  }
  
  /**
   * 
   * 
   * 
   * @return String[] of Emp ID First and Last Name of employee by index
   */
  public String [] custList(ArrayList<Customer> cust){
	  String temp="";
	  String[] stringedEmpList= new String[cust.size()];
	  for(int i=0;i<stringedEmpList.length;i++){
		  temp= Integer.toString(cust.get(i).getID())+"    "+cust.get(i).firstName+" "+cust.get(i).lastName;
		  stringedEmpList[i]=temp;
	  }	  
	  return stringedEmpList;
  }
  /**
   * 
   * @param int index of customer
   * @param String year of bid
   * @return String string representation of bids per customer
   */
  public String sCustBids(int index, String year){
	  String temp="";
	  ArrayList<Bid> tempBid=new ArrayList<Bid>();
	  String sYear="";
	  temp+= "-------Bids for Customer for:"+customers.get(index).firstName+" "+customers.get(index).lastName+"\n";
	  //If all is selected print all bids by UserID
	  if(year.equals("All")){
		  for(int i=0;i<items.size();i++){
			  tempBid=items.get(i).getBids();
			  for(int j=0;j<tempBid.size();j++){
				  sYear=Integer.toString(tempBid.get(j).getBidDate().getDate().getYear());
				   //if the year of the bid and the userID of the bid are equal print it
				  if(tempBid.get(j).getUserID()==customers.get(index).getID()){
					temp+= tempBid.get(j)+"\n";  
				  }
			  }
		  } 
		  return temp;
	  }
	  //if not all print by uID and Year
	  for(int i=0;i<items.size();i++){
		  tempBid=items.get(i).getBids();
		  for(int j=0;j<tempBid.size();j++){
			  sYear=Integer.toString(tempBid.get(j).getBidDate().getDate().getYear());
			   //if the year of the bid and the userID of the bid are equal print it
			  if(sYear.equals(year) && tempBid.get(j).getUserID()==customers.get(index).getID()){
				temp+= tempBid.get(j)+"\n";  
			  }
		  }
	  }
	  
	  return temp;
  }
  /**
   * 
   * @return returns string with max buyer and min buyer
   */
  public String sCustMM(){
	  double topRev=0;//keep track of max Rev
	  double lowRev=0;//keep track of low rev
	  double temp=0;//keeps track of current uID revenue
	  int index=0;//keep track of current minMax index
	  
	  
	  String returnString="";
	  
	  for(int i=0;i<customers.size();i++){
		  for(int j=0;j<soldItems.size();j++){
			  if(customers.get(i).getID()==soldItems.get(j).getSellerID()){
				  temp+=soldItems.get(i).getCustomerReturn();
			  }
		  }
		  if(temp>=topRev){
			  index=i;//store ID
			  topRev=temp;//store rev of that ID
		  }
	  }
	  String topRevDollars = dollars.format(topRev);
	  returnString+="----Buyer For All Years\n";
	  returnString+="---Max Buyer: "+customers.get(index).firstName+" "+customers.get(index).lastName+"---\n";
	  returnString+="Revenue: "+" "+topRevDollars+"\n";
	  
	  //Now calculate lower bounds
	  lowRev=temp;//reset cost
	  temp=0;
	  index=0;//reset index
	  
	  for(int i=0;i<customers.size();i++){
		  for(int j=0;j<soldItems.size();j++){
			  if(customers.get(i).getID()==soldItems.get(j).getSellerID()){
				  temp+=soldItems.get(i).getCustomerReturn();
			  }
		  }
		  if(temp<=lowRev){
			  index=i;//store ID
			  lowRev=temp;//store rev of that ID
		  }
	  }
	  String lowRevDollars =dollars.format(lowRev);
	  returnString+="---Min Buyer: "+customers.get(index).firstName+" "+customers.get(index).lastName+"---\n";
	  returnString+="Revenue: "+" "+lowRevDollars+"\n";
	  
	  return returnString;
  }
  /**
   * 
   * @param year year that needs to be filtered.
   * @return //if(customers.get(i).getID()==soldItems.get(i).getSellerID()&& soldItems.get(i).getEndDate().toString().equals(year)
   */
  public String sCustMM(String year){
	  double topRev=0;//keep track of max Rev
	  double lowRev=0;//keep track of low rev
	  double temp=0;//keeps track of current uID revenue
	  int index=0;//keep track of current minMax index
	  String sYear="";
	  
	  
	  String returnString="";
	  
	  for(int i=0;i<customers.size();i++){
		  for(int j=0;j<soldItems.size();j++){
			  sYear=Integer.toString(soldItems.get(i).getEndDate().getYear());
			  if(customers.get(i).getID()==soldItems.get(j).getSellerID()&& sYear.equals(year)){
				  temp+=soldItems.get(i).getCustomerReturn();				  
			  }
		  }
		  
		  if(temp>=topRev){
			  index=i;//store ID
			  topRev=temp;//store rev of that ID
		  }
	  }
	  String topRevDollars = dollars.format(topRev);
	  returnString+="----Buyer For Year"+year+"\n";
	  returnString+="---Max Buyer: "+customers.get(index).firstName+" "+customers.get(index).lastName+"---\n";
	  returnString+="Revenue: "+" "+topRevDollars+"\n";
	  
	  //Now calculate lower bounds
	  lowRev=temp;//reset cost
	  temp=0;
	  index=0;//reset index	  
	  
	  for(int i=0;i<customers.size();i++){
		  for(int j=0;j<soldItems.size();j++){
			  sYear=Integer.toString(soldItems.get(i).getEndDate().getYear());
			  if(customers.get(i).getID()==soldItems.get(j).getSellerID()&& sYear.equals(year)){
				  temp+=soldItems.get(i).getCustomerReturn();				  
			  }
		  }		  
		  if(temp<=lowRev){
			  index=i;//store ID
			  lowRev=temp;//store rev of that ID
		  }
	  }
	  String lowRevDollars =dollars.format(lowRev);
	  returnString+="---Min Buyer: "+customers.get(index).firstName+" "+customers.get(index).lastName+"---\n";
	  returnString+="Revenue: "+" "+lowRevDollars+"\n";
	  
	  return returnString;
  }
  
  public String getCustomerName(int sid){
	  String returnString="";
	  for(int i=0;i<customers.size();i++){
		  if(customers.get(i).getID()==sid){
			  return customers.get(i).firstName+" "+customers.get(i).lastName;
		  }
	  }
	  return returnString;
  }
  /**
   *  
   * @param index index of the item needed
   * @return bid info on that bid.
   */
  public String sGetItemBids(int index){
	  ArrayList<Bid> tempBid=new ArrayList<Bid>();
	  tempBid= items.get(index).getBids();
	  String returnString="";
	  returnString+="Customer Who owns this producet: "+this.getCustomerName(items.get(index).getSellerID())+"\n";	  
	  returnString+="-------Item Details------\n";
	  returnString+= items.get(index)+"\n";
	  returnString+= "-------Bids for above item (notSold)---\n";
	  for(int i=0;i<tempBid.size();i++){
		  returnString+=tempBid.get(i)+"\n";
	  }
	  return returnString;
  }
  
  /**
   * @param index index of the item needed
   * @return bid info of item that is sold including revenue
   */
  public String sGetSoldItemBids(int index){
	  ArrayList<Bid> tempBid=new ArrayList<Bid>();
	  tempBid= soldItems.get(index).getBids();
	  String returnString="";
	  returnString+="Customer Who owns this producet: "+this.getCustomerName(soldItems.get(index).getSellerID())+"\n";	  
	  returnString+="-------Item Details------\n";
	  returnString+= soldItems.get(index)+"\n";
	  returnString+= "-------Bids for above item (Sold)---\n";
	  for(int i=0;i<tempBid.size();i++){
		  returnString+=tempBid.get(i)+"\n";
	  }
	  String dollarCost=dollars.format(soldItems.get(index).getCustomerReturn());
	  returnString+="Customer Return for Item: "+dollarCost;
	  return returnString;
  }
  

 public static void main(String [] args){

   /***************Create EMPLOYEES***************/ 
 //Accountant(String fn,String ln, Date db, String gender, Date hd, String rd,double base, double hr)
 Date db1= new Date(3,14,1969);
 Date hd1= new Date(1,12,1999);
 Accountant acct1= new Accountant("Bill","Nye",db1,"Male",hd1,"08-01-2010",2100,21);
 Date db2= new Date(7,7,1977);
 Date hd2= new Date(3,15,2002);
 Accountant acct2= new Accountant("Soookie","Stackhouse",db2,"FeMale",hd2,"null",2800,21);
 
 Date db3= new Date(8,28,1983);
 Date hd3= new Date(2,3,2017);
 WebDesigner wd1= new WebDesigner("Scott","Nidell",db3,"Male",hd3,"null",2800,49);
 Date db4= new Date(1,7,1998);
 Date hd4= new Date(9,9,2009);
 WebDesigner wd2= new WebDesigner("Jessica","Hamby",db4,"FeMale",hd4,"09-02-2011",1100,22);
 
 Date db5= new Date(8,01,1988);
 Date hd5= new Date(1,3,2012);
 CSupport cs1= new CSupport("Dan","Peachtree",db5,"Male",hd5,"null",900,12);
 Date db6= new Date(1,7,1998);
 Date hd6= new Date(9,9,2009);
 CSupport cs2= new CSupport("Haley","Berry",db6,"FeMale",hd6,"null",900,12);
 
  /***********************Adding Employees to Enterprise****************/
 Enterprise myent= new Enterprise("Yo dog");  
 myent.addEmployee(acct1);
 myent.addEmployee(acct2);
 myent.addEmployee(wd1);
 myent.addEmployee(wd2);
 myent.addEmployee(cs1);
 myent.addEmployee(cs2);
 //myent.printEmployees();
 System.out.println(myent);
 
  /***********************Create Bids******************/
  int userID1=011;
  int itemID1=003;
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
  
  /******************Create Items************************************/
  
    int itemID01= 003;
    String itemCat1= "Antique";
    String itName1="Camera";
    String itemType1= "FIX_PRICE";
    int itemQty1= 5;
    String condition1= "USED";
    double minStart1= 30.00;
    double bidInc1= 5.00;
    double reserveAmt1= 400.00;
    String startD1= "1-1-2014,21:49:00";
    int days1= 71;
    int sellerID1= 200;
    int feedback1=1000;
    String desc1= "Old Ass Camera"; 
    
    Item myItem1= new Item(itemID01,itemCat1,itName1,itemType1,itemQty1,condition1,minStart1,bidInc1,reserveAmt1,
                           startD1,days1,sellerID1,feedback1,desc1);
    
    
    int itemID02= 004;
    String itemCat2= "Antique";
    String itName2="Camera";
    String itemType2= "AUCTION";
    int itemQty2= 1;
    String condition2= "NEW";
    double minStart2= 35.00;
    double bidInc2= 5.00;
    double reserveAmt2= 450.00;
    String startD2= "1-5-2010,21:49:00";
    int days2= 2;
    int sellerID2= 200;
    int feedback2=1000;
    String desc2= "Old Ass Camera"; 
    
    Item myItem2= new Item(itemID02,itemCat2,itName2,itemType2,itemQty2,condition2,minStart2,bidInc2,reserveAmt2,
                           startD2,days2,sellerID2,feedback2,desc2);
 }
 
}
 
 
 
 
 
 
 
 
 
 