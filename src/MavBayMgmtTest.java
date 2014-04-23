/**
 * Programmer:  Sharma Chakravarthy
 * Language: Java
 * date:        09/10/2013
 * Purpose: This program uses MayBayMgmtTest class to read data from a text file to initialize
 *   employees, items for sale, customers who bid, and actual bids
 *     
 *              It checks and recovers from some exceptions while reading the input file
 * 
 * USAGE:       You need to initialize your data structures (creation of objects) as the first step. 
 *              once the values are read into local variables, 
 *              it  is YOUR responsibility to add code at proper places to create objects and manage them!
 *
 *              filename is given as a command line argument (e.g, java MayBayMgmtTest dataFile-proj2.txt)
 *              for the naming convention used in this file. if you forget to give the data 
 *              file as a command line argument, it will prompt you for that. 
 *          
 *              you can remove or comment out print staatements once you are sure it is reading the input correctly
 *
 * MAKE SURE:   your program is completely case in-sensitive (for gender, employee type etc.)
 */

import java.io.*;
import java.util.Scanner;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;




/**
 * @param fileName
 *            as input data filename containing input items with  as item separators
 *  Note that multiple interfaces can be used with a class
 */

public class MavBayMgmtTest implements Proj3Constants, DateConstants {

 // introduce your (class and instance) attributes (if needed) for this test class.
    // As i have indicated, it is preferable to have a MavBayMgmt class for the enterprise
    // and use this ONLY as a driver or test class
    // that way, this class can be removed to make your system a library!!

    private static BufferedReader finput;   //for reading from a file
    private static Scanner cp;              //this is still command prompt
    private static PrintWriter foutput;     //for writing to a file
    

    //define other variables as needed

    //Note that we are using a DIFFERENT method for reading input file;
 /**
  * @param iFileName is the input data file name
  */  

    public static BufferedReader openReadFile(String iFileName){
        BufferedReader bf = null;
        try{
            bf = new BufferedReader(new FileReader(iFileName));
        }     
        catch(FileNotFoundException FNFE){    
          bf = null;
        }
       finally{
          return bf;
       }
    }

/**
  * @param oFileName is the input data file name
  */  
    
    public static PrintWriter openWriteFile(String oFileName){
        PrintWriter outputFile = null;
        try{
            outputFile = new PrintWriter(new FileWriter(oFileName));
        }     
        catch(IOException IOE){    
          outputFile = null;
        }
       finally{
          return outputFile;
       }
    }
     

 /**
  * @param takes
  *           2 fineNames as command line argument. prompts if either is not given
  */
 public static void main(String[] args) {

  // declare variables used for input handling
        String enterprisename = DEFAULT_ENTERPRISE_NAME;
        String inputLine = EMPTY_STRING, ifName = EMPTY_STRING, ofName = EMPTY_STRING;
        int empID=0;

  // determine if input file is provided

  cp = new Scanner(System.in);
  if (args.length < 1) {
   System.out.println("Input Data file name was not supplied");
   System.out.printf("Please type input data file name: ");
   ifName = cp.nextLine();
  } 
        else if (args.length < 2){
            ifName =  args[ZEROI];
            System.out.println("Output file name was not supplied");       
            System.out.printf("Please type output file name: ");
            ofName = cp.nextLine();
        } else {
            ifName = args[ZEROI];
            ofName = args[ONEI];
            }

  // See HOW RECOVERY is done (will cover in a few weeks)

  finput = openReadFile(ifName);
  while (finput == null) {
   System.out.printf("Error: input data FILE %s %s", ifName,
     " does not exist.\nEnter correct INPUT data file name: ");
   ifName = cp.nextLine();
   finput = openReadFile(ifName);
  }
        foutput = openWriteFile(ofName);
        while (foutput == null){
   System.out.printf("Error: OUTPUT FILE %s %s",  ofName,  
                        " does not exist.\nEnter correct OUTPUT FILE name: ");
            ofName = cp.nextLine();
            foutput = openWriteFile(ofName);
  }  

        System.out.printf("Input data File: %s\nOutput File: %s\n\n", ifName, ofName);
        foutput.printf("Input data File: %s\nOutput File: %s\n\n", ifName, ofName); 

  /* GET MayBay DETAILS */
  // start reading from data file
  // get name
  try {
   inputLine = finput.readLine();
            System.out.println(inputLine);
   while (inputLine.charAt(BASE_INDEX) == '/'){
    inputLine = finput.readLine();
                System.out.println(inputLine);
            }
   String enterpriseName = inputLine;
   System.out.printf("\n%s %s \n", "Enterprise name is: ",
     enterpriseName);

            //MENU PROCESSING STARTS HERE!!!!
   System.out.printf("\nStarting Menu Processing: \n\n");
            foutput.println("\nStarting Menu Processing: \n\n");
          
          //read each line as before and process according to the menu number
            inputLine = finput.readLine();
            System.out.println(inputLine);
            foutput.println(inputLine);
   while (inputLine.charAt(BASE_INDEX) == '/'){
    inputLine = finput.readLine();
                System.out.println(inputLine);
                foutput.println(inputLine);
            }
            
            Scanner scan= new Scanner(System.in);
            String menuScan=EMPTY_STRING;
            Enterprise myEnt= new Enterprise(enterpriseName);
            
            
            while ( (!inputLine.toLowerCase().equals("end"))){
    String[] chopMenuLine = inputLine.split("!");

            switch (Integer.parseInt(chopMenuLine[ZEROI])){
            
            case 10: //for processing project 2 commands
                     // you can copy project 2 code here if you wish
                     //or make it a method processProj2Commands()  (a better alternative) and call
                     //it from here       
                    
             
                    while(!menuScan.equals("0")){
                      myEnt.printMenu();
                      menuScan=scan.next();
                    if(menuScan.equals("1")){
                      System.out.println("Listing All Employees...");
                      myEnt.printEmployees(foutput);                 
                     
                    }else if(menuScan.equals("2")){
                       myEnt.itemsMenu();                 
                     
                     }else if(menuScan.equals("3")){
                      System.out.println("Printing All Users...");
                      myEnt.printCustomers(foutput);
                 
                     }else if(menuScan.equals("4")){                 
                      myEnt.soldItemsMenu("*",2014);
                 
                     }else if(menuScan.equals("5")){
                      myEnt.totalFees();               
                   
                     }else if(menuScan.equals("6")){
                      myEnt.maxBid();
                 
                     }else if(menuScan.equals("7")){
                      myEnt.releaseEmpMenu();
                 
                     }else if(menuScan.equals("0")){
                      System.out.println("Exiting System"); 
                      System.exit(CLEAN_EXIT);
                     }else{
                      System.out.println("Invalid Selection\n"); 
                     }
                    }
                     break;
            case 11: // process new item information
                   
                     // get fields of a item from one line of input
                     // if you want to make it modular, you can create a
                     // method proceddHireEmp() and move the code there.
                     // you will have to adjust the visibility of variables!!
                    
                    int  itemId = Integer.parseInt(chopMenuLine[ONEI]);
                    String  itemCategory = chopMenuLine[TWOI];
                    String itemName = chopMenuLine[THREEI];
                    String itemSaleType = chopMenuLine[FOURI];
                    int itemQty = Integer.parseInt(chopMenuLine[FIVEI]);
                    String itemCondition = chopMenuLine[SIXI];
                    double itemMinStartBid = Double.parseDouble(chopMenuLine[SEVENI]);
                    double itemBidIncrement = Double.parseDouble(chopMenuLine[EIGHTI]);
                    double itemReserveAmt = Double.parseDouble(chopMenuLine[NINEI]);
                    String itemAuctionStartDate = chopMenuLine[TENI];
                    int    itemAuctionDays      = Integer.parseInt(chopMenuLine[ELEVENI]);
                    int    itemSellerId      = Integer.parseInt(chopMenuLine[TWELVEI]);
                    int    sellerFeedbackScore      = Integer.parseInt(chopMenuLine[THIRTEENI]);
                    String  itemDescription     = chopMenuLine[FOURTEENI];

                    System.out.printf("[%d, %s, %s, %s, %d, %s,  %f, %f, %f, %s, %d, %d, %d, %s]\n",
                        itemId,itemCategory,itemName,itemSaleType, itemQty, itemCondition,
                        itemMinStartBid,itemBidIncrement, itemReserveAmt, itemAuctionStartDate,
                        itemAuctionDays, itemSellerId, sellerFeedbackScore, itemDescription);
                    // add code here to create item object 
                    Item newItem= new Item(itemId,itemCategory,itemName,itemSaleType,itemQty,itemCondition,
                             itemMinStartBid,itemBidIncrement,itemReserveAmt,itemAuctionStartDate,itemAuctionDays,
                                   itemSellerId,sellerFeedbackScore,itemDescription);                    
                    
                    //Item ID is checked for redundancey in this method.
                    myEnt.addItem(newItem);
                    //List of Items put on sale by that seller so far
                    myEnt.printSellerItems(newItem,foutput);
                    
                    // convert strings to enum as needed //this is done in constructor
        
                    break;
            case 12:// process new customer information
                
                    int userId = Integer.parseInt(chopMenuLine[ONEI]);
                    String userFName = chopMenuLine[TWOI];
                    String userLName = chopMenuLine[THREEI];
                    String userDob = chopMenuLine[FOURI];
                    String userGender = chopMenuLine[FIVEI].toLowerCase();
                    String userAddress = chopMenuLine[SIXI];
                    String userState = chopMenuLine[SEVENI].toUpperCase();
                    String userZipcode = chopMenuLine[EIGHTI];
                
                    Date dobUser = new Date(userDob); //how to construct a Data object!
                   /* System.out.printf("{%d, %10s, %10s, %10s, %7s, %20s, %6s, %6s} \n",
                        userId, userFName, userLName,dobUser,  userGender, userAddress, 
                        userState, userZipcode);*/
                    // create a user object as appropriate
                    //Customer(int id,String firstName,String lastName,Date db,String gender, String ad,String st, String z)
                    //Gender Checked in constructor
                    Customer newCust= new Customer(userId,userFName,userLName,dobUser,userGender,userAddress,userState,
                                                   userZipcode);
                    //User ID redundancy checked when added.
                    myEnt.addCustomer(newCust);
                    //Output list of Customers So far
                    myEnt.printCustomers(foutput);
                     break;
            case 13://process hiring a new employee
            
                  String empType = chopMenuLine[ONEI].toUpperCase();
                  String empFName = chopMenuLine[TWOI];
                  String empLName = chopMenuLine[THREEI];
                  String empBDate = chopMenuLine[FOURI];
                  String empGender = chopMenuLine[FIVEI].toLowerCase();
                  String empHireDate = chopMenuLine[SIXI];
                  String empReleaseDate = chopMenuLine[SEVENI];
                  double empBaseSalary = Double.parseDouble(chopMenuLine[EIGHTI]);
                  double empRate = Double.parseDouble(chopMenuLine[NINEI]);

        // add code here: in order to convert a date string to a Date object,
        // invoke the appropriate constructor of the Date class (shown below)
        Date dob = new Date(empBDate); // dob is a local variable
        Date hd = new Date(empHireDate); // dob is a local variable

        /*System.out.printf("(%6s, %10s, %6s, %12s, %12s,  %10.2f, %4s, %12s)\n",
         empFName, empLName, empGender, empHireDate,empReleaseDate, empBaseSalary, empType, dob); */
         
        try{
        if(empType.toUpperCase().equals(WEBDESIGNER)){
          //WebDesigner(String fn,String ln, Date db, String gender, Date hd, String rd,double base, double otr)
          WebDesigner wd= new WebDesigner(empFName,empLName,dob,empGender,hd,empReleaseDate,empBaseSalary,empRate);          
          empID++;
          wd.setID(empID);
          myEnt.addEmployee(wd);
          //Outputs Emp ID along with other details including age and length of Service
          myEnt.printLenghtOfService(wd,foutput);          
          
        }else if(empType.toUpperCase().equals(ACCOUNTANT )){
          Accountant acct= new Accountant(empFName,empLName,dob,empGender,hd,empReleaseDate,empBaseSalary,empRate);
          empID++;
          acct.setID(empID);
          myEnt.addEmployee(acct);
           //Outputs Emp ID along with other details including age and length of Service
          myEnt.printLenghtOfService(acct,foutput);
        }else if(empType.toUpperCase().equals(CUSTOMER_SUPPORT)){
          CSupport cs= new CSupport(empFName,empLName,dob,empGender,hd,empReleaseDate,empBaseSalary,empRate);
          empID++;
          cs.setID(empID);
          myEnt.addEmployee(cs);
          //Outputs Emp ID along with other details including age and length of Service
          myEnt.printLenghtOfService(cs,foutput);
        }else{
          System.out.println("Invalid Employee Type");
        }
        }catch(RuntimeException RE){
          System.out.println("Bad Gender Type. Employee not added");
        }        
                    break;
   
            case 14:// accept a new bid
                         
                int bitemId = Integer.parseInt(chopMenuLine[ONEI]);
                int buserId = Integer.parseInt(chopMenuLine[TWOI]);
                String bidDateTime = chopMenuLine[THREEI];
                double bidAmount = Double.parseDouble(chopMenuLine[FOURI]);
                int bidQty = Integer.parseInt(chopMenuLine[FIVEI]);                
                //code added
                DateTime bidTime= new DateTime(bidDateTime);
                Bid bid= new Bid(buserId,bitemId,bidTime,bidAmount,bidQty);                
                //add bid checks for validity
                myEnt.addBid(bid);
                //prints current bids on item so far
                myEnt.printBids(bitemId,foutput);
                    break;
            case 15:// terminate an employee
              
                int rempID= Integer.parseInt(chopMenuLine[ONEI]);
                String termDate = chopMenuLine[TWOI];
                //Release employee
                myEnt.releaseEmployee(rempID, termDate);                
                //print current employees
                myEnt.printEmployees(foutput);
                //print fired employees
                myEnt.printPastEmployees(foutput);
                    break;
            case 16://compute monthly salary
                int eid= Integer.parseInt(chopMenuLine[ONEI]);
                int month = Integer.parseInt(chopMenuLine[TWOI]);
                int otr = Integer.parseInt(chopMenuLine[THREEI]);
                myEnt.getSalary(eid,month,otr,foutput);
                
                    break;
                    
            case 17:// compute length of service
              
                int eidl= Integer.parseInt(chopMenuLine[ONEI]);
                myEnt.printLenghtOfService(eidl,foutput);
                
                    break;
            case 18: // compute revenue
                
                String aType = chopMenuLine[ONEI];
                int bidYear = Integer.parseInt(chopMenuLine[TWOI]);
                myEnt.checkSold();
                myEnt.getRevenue(bidYear,aType,foutput);
                
                
                    break;
            case 19: // generate seller report
                int sid = Integer.parseInt(chopMenuLine[ONEI]);//sellerID
                int syear = Integer.parseInt(chopMenuLine[TWOI]);//Year
                foutput.println(myEnt.soldItemsBySeller(syear,sid));
                    break;
            case 0: //process exit
            	try {
        		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        		        if ("Nimbus".equals(info.getName())) {
        		            UIManager.setLookAndFeel(info.getClassName());
        		            break;
        		        }
        		    }
        		} catch (Exception e) {
        		    // If Nimbus is not available, fall back to cross-platform
        		    try {
        		        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        		    } catch (Exception ex) {
        		        
        		    }
        		}
            	
              MainMenu myMenu= new MainMenu(myEnt); //Start of GUI
             
              //System.out.println(myEnt.sGetSoldItemBids(0));
              
                    break;
            default: System.out.printf("unknown command: %s: SKIPPED\n", inputLine);
                    foutput.printf("unknown command: %s: SKIPPED\n", inputLine);
                    break;       
                     
            } 
            inputLine = finput.readLine();
            System.out.println(inputLine);
            foutput.println(inputLine);
   while (inputLine.charAt(BASE_INDEX) == '/'){
    inputLine = finput.readLine();
                System.out.println(inputLine);
                foutput.println(inputLine);
            }
        } 
         System.out.printf("Finished processing all commands. bye!"); 
         foutput.printf("Finished processing all commands. bye!");      
          // DO NOT REMOVE or DISTURB the REST OF THE CODE 

  } //try
        catch(NullPointerException NPE){
            System.out.println("null pointer exception: " + "\nPlease correct " + NPE.getMessage());
          } 
        catch (NumberFormatException NFE) {
   System.out.println("I/O Error in File: " + ifName + "\ncheck for: "
     + NFE.getMessage() + " and correct it in: " + inputLine);
    } 
        catch (RuntimeException RE) {
   System.out.println("Invalid Data error in File: " + ifName
     + "\nPlease correct " + RE.getMessage() + " in the file!" + inputLine);
    }
        catch(IOException IOE){
            System.out.println("input/output Data error in File: " + ifName + "\nPlease correct " + IOE.getMessage() + " in the file!" + inputLine);
          } 
        
        finally {
    foutput.close();
    }
 }//main
} // MavBayMgmtTest
