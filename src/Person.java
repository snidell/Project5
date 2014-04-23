/*************************************************************************
 *  Compilation:  javac Person.java <br>
 *  Execution:    java Person <br>
 *************************************************************************/

/**
 * A simple data type Person for use in Project 3 (CSE 1325, Spring 2014)
 * <br>
 * Useful for creating Person objects to hold people info
 * 
 * @author Scott Nidell  (3/12/2014) (
 */
public abstract class Person implements Proj3Constants {
        
    
    private enum Gender{MALE,FEMALE};  
    String firstName;
    String lastName;
    Date dob;
    Gender gender;    
        
   public Person(String fn, String ln, String g,Date db) {
        lastName = ln;
        firstName=fn;
        if(!validGender(g))
          throw new RuntimeException("Employee 2: error not valid Gender type");
        gender= Gender.valueOf(g.toUpperCase());
        dob=db;
    }   
    
   /**
   * Checks to see if the String representation is a valid gender type
   * 
   * @param String to be validated
   * @return true if legal gender else false
   * 
   */ 
   private static boolean validGender(String g){
   
    if(g.toUpperCase().equals("MALE"))
      return true;
    if(g.toUpperCase().equals("FEMALE"))
      return true;
   
    return false;
 }
  
   /**
   * sets the first name
   * 
   * @param newName   new name to be updated
   * 
   */   
   public void changeFirst(String newName){
   
   this.firstName=newName;
 }

 /**
  * change the last name of customer
  * 
  * @param newName  new name to be updated            
  */
 public void changeLast(String newName){
   this.lastName=newName;
 }
 
 /**
  * Abstract class to be implmented in Employee and Customer
  * 
  * @return age of current person       
  */
 public abstract int age();
 
 /**
  * Prints out person format
  * @override overides object class
  * @return String that represents a Customer Object   
  */
 public String toString(){
    
   
   return "Person->"+firstName+" "+lastName+" Birthdate: "+dob+" Gender:"+gender;   
 }
}