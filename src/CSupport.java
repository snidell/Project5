/*************************************************************************
 *  Compilation:  javac CSupport.java <br>
 *  Execution:    java CSupport <br>
 *************************************************************************/

/**
 * A simple data type CSupport for use in Project 3 (CSE 1325, Spring 2014)
 * <br>
 * Useful for creating CSupport objects to hold employee info
 * 
 * @author Scott Nidell  (3/12/2014) (
 */
import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
public class CSupport extends SalariedEmp{ 
  
   double minsRate;
   private NumberFormat dollars= NumberFormat.getCurrencyInstance(Locale.US);
  
  CSupport(String fn,String ln, Date db, String gender, Date hd, String rd,double base, double mr){
    
    super(fn,ln,db,gender,hd,rd,base); //Salaried will build then Person will build from salaried
    minsRate=mr;    
  }
  
  /**
  * Overrides abvstract method in SalariedEmployee to calculate salary
  * 
  * @return age of current person       
  */
  public String computeSalary(int timeOnPhone){
    String dollarCost=dollars.format(basePay+minsRate*timeOnPhone);
    
    return dollarCost; //basePay declared in Superclass Inheritance at work.
  }
  
  /**
  * Overrides objects String method
  * 
  * @return String Represents CSupport    
  */
  public String toString(){    
	 
   return "Customer Support->"+firstName+" "+lastName+" Birthdate: "+dob+" Gender:"+gender+" ID: "+id+" HireDate: "+
     hireDate+" Release Date: "+releaseDate+" basePay: "+basePay;   
 }
  public String printCalc(int month){    
   
   return "    ID: "+id+"|Emp Type: CSupport |"+" First Name: "+firstName+" Last "+lastName+"|Gender: "+gender
     +" Month: "+month;  
 }
}