//***************************************************************
//  Developer:    Tiffany Pham
//
//  Program #:    Four
//
//  File Name:    Program4.java
//
//  Course:       ITSE 2317 Intermediate Java Programming
//
///  Due Date:     7/20/25
///
//
//  Instructor:   Fred Kumi
//
//  Chapter:      8 & 9
//
//  Description:  Entry point for the program. Delegates work to 
//                CAI.
//
//***************************************************************

//***************************************************************
//  Class:       Program4
//
//  Description: Entry point for the program.
//
//  Parameters: N/A
//
//  Returns:    N/A
//***************************************************************

public class Program4 {
    //***************************************************************
    //  Method:      main
    //
    //  Description: Main method that starts the CAI engine.
    //
    //  Parameters: String[] args
    //
    //  Returns:    N/A
    //***************************************************************

    public static void main(String[] args) {
        CAI app = new CAI();
        app.developerInfo();
        app.start();
    }
}
