/*************************************************************************
 *  Compilation:  javac Customer.java <br>
 *  Execution:    java Customer <br>
 *************************************************************************/

/**
 * A simple data type Customer for use in Project 3 (CSE 1325, Spring 2014)
 * <br>
 * Useful for creating Customer objects to hold customer info
 * 
 * @author Scott Nidell  (3/12/2014) (
 */
import java.math.*;
import java.text.*;
import java.util.*;
public class Customer extends Person{       
    
    private enum Status{GOLD,SILVER};
    private enum Classification{SELLER,BUYER,BOTH};
    private int userID;
    private String address;
    private String zip;
    private String state;    
    private Status status;
    private Classification classification;
    private DecimalFormat three= new DecimalFormat("#000");    
    
    public Customer(int id,String firstName,String lastName,Date db,String gender, String ad,
                                              String st, String z) {
        super(firstName, lastName, gender,db);
        userID = id;
        address=ad;
        state=st;
        zip=z;
        status=status.SILVER;
        classification= classification.BOTH;
    }  
    
    /**
     * Change the address of the customer
     * 
     * @param newAddr the new address to be changed           
     */
   public void changeAddress(String newAddr){
   
   this.address=newAddr;
   }
 
   /**
    * Change the zip code of customer
    * 
    * @param newZip the Zipcode to change to         
    */
  public void changeZip(String newZip){
   
   this.zip=newZip;
  }
  
  /**
  * Change Silver/Gold status
  * 
  *       
  */
 public void changeStatus(){
   if(this.status.equals(Status.SILVER)){
    this.status=Status.GOLD;
   }else if(this.status.equals(Status.GOLD)){
    this.status=Status.SILVER;
   }
 }
 
 /**
  * Change seller buyer class
  * 
  * @param newClass String representation of new class    
  */
 public void changeClass(String newClass){
   
   if(newClass.toLowerCase().equals(BUYERCLASS)){
     this.classification = Classification.BUYER;
   }else if(newClass.equals(SELLERCLASS)){
     this.classification = Classification.SELLER;
   }else if(newClass.equals(BOTHCLASS))
     this.classification = Classification.BOTH;
   else
     System.out.println("Invalid selection");
 }
 /**
  * Get the Customer ID
  *      
  */
 public int getID(){
  return this.userID;   
 }
 
 /**
  * Change seller buyer class
  * @override overrides Person which overrides Object
  * @return String that represents a Customer Object   
  */
 public String toString(){  
   
  String customerIDF= three.format(this.userID);
   
   return "{ UserID: "+customerIDF+"| Name: "+super.firstName+" "+super.lastName+"| D.O.B.: "+super.dob+
     "| Address: "+ this.address+" "+this.zip+"| Sales Status: "+this.status+
     "| Customer Class: "+this.classification+" }";   
 }
 
 /**
  * Compares current date to date of birth and gets age of person
  * 
  * @return age of current person       
  */
 public int age(){
   int age=0;
   Date today= new Date();
   age= (dob.daysBetween(today))/DAY_YEAR;
   return age;
 }
 public static void main(String args []){
   
   
   
   Date date1= new Date(8,28,1983);
   Customer cust1= new Customer(47,"Scott","Nidell",date1,"male","5316 Stephanie Drive","Texas","76117");
   System.out.println(cust1);
   
   
   //Person p1= new Person("Sookie","Stackhouse","Female",date2); //should error out can instantiate abstract class
   //System.out.println(p1);
 }
 
  
 
       
}