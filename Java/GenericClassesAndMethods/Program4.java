//********************************************************************
//
//  Developer:     Instructor
//
//  Program #:     Four
//
//  File Name:     Program4.java
//
//  Course:        ITSE 2317 Intermediate Java Programming
//
//  Due Date:      7/14/2025
//
//  Instructor:    Fred Kumi 
//
//  Chapter:       20
//
//  Description:   This program puts values into an array, sorts
//                 the values with a Generic Selection Sort into
//                 ascending order, and prints the resulting array.
//
//                 Please do NOT modify this Class apart from line 92.
//                 If you do, you will not receive credit for this program.
// 
//********************************************************************

public class Program4
{
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
    public static void main(String args[])
    {
	    Program4 object = new Program4();
	    GenericSelectionSort sortObject = new GenericSelectionSort();
	   
	    object.developerInfo();
	    object.sortGenericArray(sortObject);
    }
      
    //***************************************************************
    //
    //  Method:       sortGenericArray
    // 
    //  Description:  This method calls the callSelectionSort 
    //                method Sort.
    //
    //  Parameters:   GenericSelectionSort Object
    //
    //  Returns:      N/A 
    //
    //**************************************************************
    public void sortGenericArray(GenericSelectionSort sortObject)
    {
        // Initialize Integer array
        Integer[] integerArray = {9, 3, 6, 4, 8, 10, 1, 7, 5, 2};
        sortObject.callSelectionSort(integerArray, "Integer");
   	
        // Initialize Double array
        Double[] doubleArray = {9.9, 4.4, 5.5, 7.7, 1.1, 8.8, 3.3, 6.6, 2.2};
        sortObject.callSelectionSort(doubleArray, "Double"); 

        // Initialize Character array
    	Character[] characterArray = {'T', 'E', 'S', 'T', 'I', 'N', 'G'};
    	sortObject.callSelectionSort(characterArray, "Character");
	    
        // Initialize String array
	    String[] stringArray = {"Hearts", "Diamonds", "Clubs", "Spades"};
	    sortObject.callSelectionSort(stringArray, "String");
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
        System.out.println("Program:  Four");
        System.out.println("Due Date: 7/21/2025\n");
    } // End of developerInfo
}
