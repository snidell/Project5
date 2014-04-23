/*************************************************************************
 *  Compilation:  javac Accountant.java <br>
 *  Execution:    java Accountant <br>
 *************************************************************************/

/**
 * A simple data type Accountant for use in Project 3 (CSE 1325, Spring 2014)
 * <br>
 * Useful for creating Accountant objects to hold employee info
 * 
 * @author Scott Nidell  (3/12/2014) (
 */
import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
public class Accountant extends SalariedEmp{ 
   
   double hourlyRate;
   private NumberFormat dollars= NumberFormat.getCurrencyInstance(Locale.US);
  
  Accountant(String fn,String ln, Date db, String gender, Date hd, String rd,double base, double hr){
    
    super(fn,ln,db,gender,hd,rd,base); //Salaried will build then Person will build from salaried
    hourlyRate=hr;    
  }
  
  /**
  * Overrides abstract method in SalariedEmployee to calculate salary
  * 
  * @return age of current person       
  */
  public String computeSalary(int numHours){
    String dollarCost=dollars.format(basePay+hourlyRate*numHours);
    
    return dollarCost;
  }
  /**
  * Overrides objects String method
  * 
  * @return String Represdent     
  */  
  public String toString(){    
	 
   return "Accountant->"+firstName+" "+lastName+" Birthdate: "+dob+" Gender:"+gender+" ID: "+id+" HireDate: "+
     hireDate+" Release Date: "+releaseDate+" basePay: "+basePay;   
 }
  public String printCalc(int month){    
   
   return "    ID: "+id+"|Emp Type: Accountant |"+" First Name: "+firstName+" Last "+lastName+"|Gender: "+gender
     +" Month: "+month; 
 }
}