/*************************************************************************
 *  Compilation:  javac SalariedEmp.java <br>
 *  Execution:    java SalariedEmp <br>
 *************************************************************************/

/**
 * A abstract data type SalariedEmp for use in Project 3 (CSE 1325, Spring 2014)
 * <br>
 * Useful for creating SalariedEmp objects to hold employee info
 * 
 * @author Scott Nidell  (3/12/2014) (
 */
public abstract class SalariedEmp extends Person implements Employee{
  
  int id=0;
  Date hireDate=null;  
  Date releaseDate=new Date();
  double basePay=0; 
  
  public SalariedEmp(String firstName,String lastName,Date db,String gender,Date hd, String rd, double base) {
        super(firstName, lastName, gender,db);
        
        hireDate=hd;
        if(rd.equals("null")){
          releaseDate=null;
         }else{   
          releaseDate= new Date(rd); 
         }
         basePay=base;        
    }    
  /**
  * Raises the base pay of current employee
  * 
  * @return true if the new salary is not higher than max else false    
  */
  public boolean raisePay(double in){
    double checkpay= (basePay+in);
    if(checkpay<=MAX_SALARY){
     basePay+=in;
     return true;
    }
    return false;   
  }
  /**
  * Returns the ID of the current employee
  *     
  */
  public int getID(){
    return id;
  }
  /**
  * Gets the release date of employee
  * 
  * @return Date of release    
  */
  public Date getReleaseDate(){
     return releaseDate;
  }
  /**
  * Releases current employee sets release to today
  *  
  */
  public void release(String rdate){
    if(rdate.equals("*")){
      releaseDate= new Date();
    }else{
      releaseDate= new Date(rdate);
    }
    
  }
  /**
  * Sets Employee ID
  *  
  */
  public void setID(int nID){
   
    id=nID;
  }
  
   /**
  * Calculates the years of service of current employee
  * 
  * @return years of service      
  */
  public int lengthOfService(){
   
    Date today= new Date();
    return (hireDate.daysBetween(today)/DAY_YEAR);
  }
  
  /**
  * Calculates the age of the current person
  * 
  * @return age of current person       
  */
 public int age(){
   int age=0;
   Date today= new Date();
   age= (dob.daysBetween(today))/DAY_YEAR;
   return age;
 }
 
 public abstract String printCalc(int month);
  
}