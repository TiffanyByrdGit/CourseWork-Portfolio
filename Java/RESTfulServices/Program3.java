//***************************************************************
//
//  Developer:    Tiffany Pham
//
//  Program #:    3
//
//  File Name:    RestExample20.java
//
//  Course:       COSC 4301 Modern Programming 
//
//  Due Date:     7/13/2025
//
//  Instructor:   Prof. Fred Kumi 
//
//  Description:  Reads survey results into an array list to perform 
//                analysis: A-F.
//
//***************************************************************

import java.io.IOException;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;
import java.io.PrintWriter;
import java.io.FileWriter;



public class Program3
{
  Scanner input = null;
  PrintWriter writer = null;

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
        program3Obj.runProgram();
    }

    //***************************************************************
    //
    //  Method:       runProgram
    // 
    //  Description:  Coordinates all tasks outside main
    //
    //  Parameters:   None
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void runProgram() {
      try {
          writer = new PrintWriter(new FileWriter("Program3-Output.txt"));
          
          // Print developer info to console and file
          developerInfo();
  
          ProcessHouseHolds houseHoldsObj = new ProcessHouseHolds();
          ArrayList<HouseHolds> houseHoldsList = houseHoldsObj.readHouseHoldData();
  
          houseHoldsObj.solutionPartA(houseHoldsList, writer);
          houseHoldsObj.solutionPartB(houseHoldsList, writer);
          houseHoldsObj.solutionPartC(houseHoldsList, writer);
  
          Map<Integer, Double> povertyMap = houseHoldsObj.getPovertyData();
  
          houseHoldsObj.solutionPartD(houseHoldsList, povertyMap, writer);
          houseHoldsObj.solutionPartE(houseHoldsList, povertyMap, writer);
          houseHoldsObj.solutionPartF(houseHoldsList, povertyMap, writer);
  
          writer.close();
      } catch (IOException e) {
          System.err.println("Error creating output file.");
          e.printStackTrace();
      }
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
      //System.out.println("Current working dir: " + Paths.get("").toAbsolutePath());


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
   //  Parameters:  ArrayList<houseHolds> houseHoldsList
   //
   //  Returns:      N/A unless error
   //
   //**************************************************************

   public void readRecords(ArrayList<HouseHolds> houseHoldsList)
   {
    houseHoldsList.clear();

    try {
      while (input.hasNextLine()) {
        String line = input.nextLine();
        String[] data = line.trim().split("\\s+");


        int idNum = Integer.parseInt(data[0].trim());
        double annualIncome = Double.parseDouble(data[1].trim());
        int householdMembers = Integer.parseInt(data[2].trim());
        
        StringBuilder stateBuilder = new StringBuilder();
         for (int j = 3; j < data.length; j++) {
            stateBuilder.append(data[j]);
            if (j < data.length - 1) {
                stateBuilder.append(" ");
    }
}
String state = stateBuilder.toString().trim();

        houseHoldsList.add(new HouseHolds(idNum, annualIncome, householdMembers, state));
      }
    } catch (NoSuchElementException elementException) {
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
   public void developerInfo() {
      String info = """
          Name:     Tiffany Pham
          Course:   COSC 4301 Modern Programming
          Program:  Three
          Due Date:  7/13/2025
  
          """;
      System.out.print(info);
      if (writer != null) {
          writer.print(info);
      }
  }
}
  
