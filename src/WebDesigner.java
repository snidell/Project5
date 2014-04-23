/*************************************************************************
 *  Compilation:  javac WebDesigner.java <br>
 *  Execution:    java WebDesigner <br>
 *  Created By: Scott Nidell March 15th 2014
 *  Committed on remote computer 4/1/2014
 *************************************************************************/

import java.text.*;
import java.util.*;
public class WebDesigner extends SalariedEmp{ 
  
  double overTimeRate;
  private NumberFormat dollars= NumberFormat.getCurrencyInstance(Locale.US);
  
  WebDesigner(String fn,String ln, Date db, String gender, Date hd, String rd,double base, double otr){
    
    super(fn,ln,db,gender,hd,rd,base); //Salaried will build then Person will build from salaried
    overTimeRate=otr;    
  }
  
  /**
  * Overrides abstract method in Employee class to calculate salary
  * 
  * @return age of current person       
  */
  public String computeSalary(int otHours){
    String dollarCost=dollars.format(basePay+overTimeRate*otHours);    
    return dollarCost; //basePay declared in Superclass Inheritance at work.
  }
  
  // return a string representation of this WebDesigner
 /**
  * replaces the default toString of Object class
     * @override
  */
  public String toString(){    
   
   return "Web Designer->"+firstName+" "+lastName+" Birthdate: "+dob+" Gender:"+gender+" ID: "+id+" HireDate: "+
     hireDate+" Release Date: "+releaseDate+" basePay: "+basePay;   
 }
  public String printCalc(int month){    
   
   return "    ID: "+id+"|Emp Type: Web Designer|"+" First Name: "+firstName+" Last "+lastName+"|Gender: "+gender
     +" Month: "+month;    
 }
  
  public static void main(String args[]){
    Date dob= new Date(8,28,1983);
    Date hd= new Date(8,20,2004);
    
    
    WebDesigner myWD= new WebDesigner("Scott","Nidell",dob,"Male",hd,"null",2200,48);
    System.out.println(myWD);
    System.out.println(myWD.computeSalary(1));
    System.out.println(myWD.computeSalary(1));
  }
}

