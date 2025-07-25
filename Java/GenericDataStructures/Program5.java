//********************************************************************
//
//  Developer:     Tiffany Pham 
//
//  Program #:     Five
//
//  File Name:     Program5.java
//
//  Course:        ITSE 2317 Intermediate Java Programming
//
//  Instructor:    Fred Kumi 
//
//  Chapter:       21
//
//  Description:   Evaluates postfix expression using a custom stack 
//                 and calling PostfixEval class.
//
//********************************************************************
import java.util.Scanner;

    //***************************************************************
    //
    //  Method:       Program5
    //
    //  Description:  Runs evaluator and displays developer info. 
    //
    //  Parameters:   N/A
    //
    //  Returns:      N/A
    //
    //***************************************************************
public class Program5 {                     

    public static void main(String[] args) {
        Program5 program = new Program5();
        program.developerInfo();
        program.runEvaluator();
        
    }                             
    
    //***************************************************************
    //
    //  Method:       runEvaluator
    //
    //  Description:  Calls run method in PostfixEval.java
    //
    //  Parameters:   N/A
    //
    //  Returns:      N/A
    //
    //***************************************************************

    public void runEvaluator() {           
        Scanner scanner = new Scanner(System.in);
        PostfixEval eval = new PostfixEval();

        System.out.print("Enter a postfix expression or 0 to quit: ");
        String input = scanner.nextLine();

        while (!input.trim().equals("0")) {    
            StringBuffer expression = new StringBuffer(input + ")");

            try {
                int result = eval.evaluatePostfixInput(expression);
                System.out.println("Result: " + result);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.print("\nEnter a postfix expression or 0 to quit: ");
            input = scanner.nextLine();
        } 

    }                                  

    //***************************************************************
    //
    //  Method:       developerInfo
    //
    //  Description:  Displays developer info.
    //
    //  Parameters:   N/A
    //
    //  Returns:      N/A
    //
    //***************************************************************
    public void developerInfo()
    {
       System.out.println("Name:     Tiffany Pham");
       System.out.println("Course:   ITSE 2317 Intermediate Java Programming");
       System.out.println("Program:  Five");
       System.out.println("Due Date:  7/22/2025\n");
    } // End of developerInfo                                  
    
}