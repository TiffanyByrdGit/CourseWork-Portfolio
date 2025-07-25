
//*************************************************************
//
//  Developer:     Tiffany Pham
//
//  Program #:     One
//
//  File Name:     Triangle.java
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
//                 The area of a triangle is calculated in this program.
//******************************************************************


//***************************************************************
   //
   //  Class:        Triangle
   // 
   //  Description:  Inherits shape from TwoDimensionalShape. Caluculates 
   //                the area and returns the value. Outputs
   //                toString to return formatted output.
   //
   //  Parameters:   N/A
   //
   //  Returns:      Returns shape type, dimension, and area. 
   //
   //**************************************************************
   class Triangle extends TwoDimensionalShape {
    private double base;
    private double height;
 
    //***************************************************************
    //
    //  Constructor:  Triangle
    //
    //  Description:  Initializes the circle with the given radius.
    //
    //  Parameters:   double base & length
    //
    //  Returns:      N/A
    //
    //***************************************************************

    public Triangle (double base, double height) {
       this.base = base;
       this.height = height;
    }
 
    //***************************************************************
    //
    //  Method:       getArea
    //
    //  Description:  Calculates are of triangle.
    //
    //  Parameters:   N/A
    //
    //  Returns:      Returns area of triangle.
    //
    //*************************************************************** 

    @Override
    public double getArea(){
       return 0.5 * base * height;
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