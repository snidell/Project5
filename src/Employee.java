/*****************************************************************************
 @author Sharma Chakravarthy
 LANGUAGE   : Java version 6
 OS         : Windows win 7 Ultimate
 PLATFORM   : PC
 Compiler   : javac
 
 CONCEPTS   : classes and methods
 PURPOSE    : defines an interface
******************************************************************************/

public interface Employee {  

 /**
  * Abstract class that needs to be overridden in WD, CS, and ACCT classes
  * calculates salary of unique objects
  * 
  * @param salaryParameter unique to the class its created from
  * @return age of current person       
  */
 public String computeSalary(int salaryParameter); //this cannot be static computed in Emp Type Class (ACCT,WD,CS)
 /**
  * 
  * Calculates the years of service of current employee
  * 
  * @return years of service      
  */
 public int lengthOfService();//computer in Salaried Employee Class
 /**
  * 
  * Formatted output for requirement 16
  * 
  * @return formated string for convention 16 output
  * 
  */
 public String printCalc(int month);
 
}
