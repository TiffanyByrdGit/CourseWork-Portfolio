//***************************************************************
//
//  Developer:    Tiffany Pham
//
//  Program #:    Three
//
//  File Name:    Program3Template.java
//
//  Course:       ITSE 2317 Intermediate Java Programming
//
//  Due Date:     <Due Date>
//
//  Instructor:   Fred Kumi
//
//  Chapter:      17
//
//  Description:  Program3 test class used to demonstrate the use
//                of streams. This class will read data from the
//                invoive file, Program3.txt and perform all
//                requirements in the assignment.
//
//                Do not modifiy the main method of this class. If you
//                do, you will not receive credit for this program. 
//
//***************************************************************

import java.io.IOException;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayList;

public class Program3
{
   Scanner input = null;
   
   //***************************************************************
   //
   //  Method:       main
   // 
   //  Description:  The main method of the program
   //
   //  Parameters:   String array
   //
   //  Returns:      N/A 
   //
   //**************************************************************
   public static void main(String[] args) 
   {	   
      Program3 program3Obj = new Program3();
      ProcessInvoices invoiceObj = new ProcessInvoices();
      
      ArrayList<Invoice> invoiceList = new ArrayList<>();
      
	  program3Obj.developerInfo();
	  
	  program3Obj.openFile();
	  program3Obj.readRecords(invoiceList);

      invoiceObj.solutionPartA(invoiceList);
      invoiceObj.solutionPartB(invoiceList);
      invoiceObj.solutionPartC(invoiceList);
      invoiceObj.solutionPartD(invoiceList);
      invoiceObj.solutionPartE(invoiceList);
      invoiceObj.solutionPartF(invoiceList);
   }
	
   //***************************************************************
   //
   //  Method:       openFile
   // 
   //  Description:  Opens the invoice file, Program3.txt
   //
   //  Parameters:   None
   //
   //  Returns:      N/A 
   //
   //**************************************************************
   public void openFile()
   {
      try
      {
         input = new Scanner(Paths.get("Program3.txt")); 
      } 
      catch (IOException ioException)
      {
         System.err.println("Error opening file. Terminating.");
         System.exit(1);
      }
   }
 
   //***************************************************************
   //
   //  Method:       readRecords
   // 
   //  Description:  Read from the text file, create invoice object,
   //                and add it to invoiceList.
   //
   //  Parameters:  ArrayList<Invoice> invoiceList
   //
   //  Returns:      N/A unless error
   //
   //**************************************************************
   public void readRecords(ArrayList<Invoice> invoiceList)
   {
      invoiceList.clear();

      try {
         while (input.hasNext()) {
            String line = input.nextLine();
            String[] parts = line.split(",");

            int partNumber = Integer.parseInt(parts[0].trim());
            String partDescription = parts[1].trim();
            int quantity = Integer.parseInt(parts[2].trim());
            double price = Double.parseDouble(parts[3].trim());

            invoiceList.add(new Invoice(partNumber, partDescription, quantity, price));
         }
      }  catch (NoSuchElementException elementException) {
         System.err.println("Error reading file!");
         
      }  catch (IllegalStateException starException) {
         System.err.println("Error reading from file... Terminating!");
      } finally {
         if (input != null) input.close();
      }
   }

   //***************************************************************
   //
   //  Method:       developerInfo
   // 
   //  Description:  The developer information method of the program
   //
   //  Parameters:   None
   //
   //  Returns:      N/A 
   //
   //**************************************************************
   public void developerInfo()
   {
      System.out.println("Name:     Tiffany Pham");
      System.out.println("Course:   ITSE 2317 Intermediate Java Programming");
      System.out.println("Program:  Three");
	  System.out.println("Due Date:  7/14/2025\n");
   } // End of developerInfo
}