
//*************************************************************
//
//  Developer:     Tiffany Pham
//
//  Program #:     One
//
//  File Name:     Square.java
//
//  Course:        ITSE 2317 Java Programming (Intermediate)
//
//  Due Date:      6/23/2025
//
//  Instructor:    Prof. Fred Kumi 
//
//  Chapter:       10
//
//  Description:   This is class from the the abstract class, 2d.
//                 The area of a square is calculated in this program.
//******************************************************************



//***************************************************************
   //
   //  Class:        Square
   // 
   //  Description:  Inherits shape from TwoDimensionalShape. Caluculates 
   //                the area and returns the value. Outputs
   //                toString to return formatted output.
   //
   //  Parameters:   N/A
   //
   //  Returns:      Returns shape type, dimension, and area of sqaure. 
   //
   //**************************************************************

   class Square extends TwoDimensionalShape {

    private double length; 
 
    //***************************************************************
    //
    //  Constructor:  Square
    //
    //  Description:  Initializes the circle with the given radius.
    //
    //  Parameters:   double length 
    //
    //  Returns:      N/A
    //
    //***************************************************************

    public Square (double length) {
       this.length = length;
    }
 
    //***************************************************************
    //
    //  Constructor:  getArea
    //
    //  Description:  Calculates are of square
    //
    //  Parameters:   N/A
    //
    //  Returns:      Returns area of sqaure
    //
    //*************************************************************** 


    @Override
    public double getArea() {
       return length * length; 
    }

        //***************************************************************
    //
    //  Method:       toString
    //
    //  Description:  Returns a formatted string.
    //
    //  Parameters:   N/A
    //
    //  Returns:      Returns shape type, dimension, and area of circle. 
    //
    //***************************************************************

    
    public String toString(){
       return String.format( 
          "Shape: %s\nDimension: Two-Dimensional\nArea: %.2f\n...\n",
          getClass().getSimpleName(),getArea());
    }
}
 
