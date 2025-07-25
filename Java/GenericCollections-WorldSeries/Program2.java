//*************************************************************
//
//  Developer:    Instructor
//
//  Program #:    Two
//
//  File Name:    Program2Template.java
//
//  Course:       ITSE 2317 Java Programming (Intermediate)
//
//  Due Date:     <Due Date>
//
//  Instructor:   Prof. Fred Kumi 
//
//  Chapter:      16
//
//  Description:  A program that prompts the user for a year in the
//                range of 1903 through 2024 and then displays the
//				  name of the team that won the World Series that
//				  year and the number of times that team has won
//                the World Series.
//
//***************************************************************

import java.util.Scanner;

public class Program2 {
    Scanner input = new Scanner(System.in);

    //***************************************************************
    //
    //  Method:       main
    // 
    //  Description:  The main function of the program
    //                Constructs myObject from Program2
    //                Constructs worldSeriesObject from WorldSeries
    //                Declares methods dev info, display msg, and user input
    //
    //  Parameters:   String array
    //
    //  Returns:      N/A 
    //
    //**************************************************************
	public static void main(String[] args)
	{
	   Program2 myObject = new Program2();
	   WorldSeries worldSeriesObject = new WorldSeries();
	   	   
	   myObject.developerInfo();
	   myObject.displayMessage();
       myObject.getUserInput(worldSeriesObject);
       // Call more methods from here
	   
	}
	
	//***************************************************************
    //
    //  Method:       getUserInput
    // 
    //  Description:  Gets user input, displays results 
    //                Offers sentinel to end program
    //
    //  Parameters:   WorldSeries worldSeriesObject
    //
    //  Returns:      year, invalid entry, or end of program
    //
    //**************************************************************
    public void getUserInput(WorldSeries worldSeriesObj)
    {
       int year;
	   
       System.out.print("Enter a year in the range 1903-2024: ");
    
       year = readInput();
       while (year != 0)
       {
           // Call showResults method
           worldSeriesObj.displayWinner(year);
		   
           System.out.print("Enter a year in the range 1903-2024, enter 0 to exit: ");
           year = readInput();
       }

       System.out.print("\nGood Bye!!!");
    }
	
	    
    //***************************************************************
    //
    //  Method:       readInput
    // 
    //  Description:  This method reads in the year from the user
    //
    //  Parameters:   None
    //
    //  Returns:      year 
    //
    //**************************************************************
    public int readInput()
    {   
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a year in the range 1903-2024: ");
        int year = scanner.nextInt();
        // Complete this method.

        return year;
    }	
	
    //***************************************************************
    //
    //  Method:       displayMessage
    //
    //  Description:  This method displays a message to the screen
    //
    //  Parameters:   None
    //
    //  Returns:      N/A
    //
    //***************************************************************
    public void displayMessage()
    {
       System.out.println("This program prompts the user for a year in the range of");
       System.out.println("1903 through 2024 and then displays the name of the team");
       System.out.println("that won the World Series that year and the number of times");
       System.out.println("that team has won the World Series.\n");
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
        System.out.println("Course:   ITSE 2317 Java Programming - Intermediate");
        System.out.println("Program:  Two");
	    System.out.println("Due Date: 6/23/2025\n");
    } // End of developerInfo

}// End of class Program2

