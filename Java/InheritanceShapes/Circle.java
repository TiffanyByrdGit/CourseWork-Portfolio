
//*************************************************************
//
//  Developer:     Tiffany Pham
//
//  Program #:     One
//
//  File Name:     Circle.java
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
//                 The area of a circle is calculated in this program.
//******************************************************************



//***************************************************************
   //
   //  Class:       Circle
   // 
   //  Description:  Inherits shape from TwoDimensionalShape. Caluculates 
   //                the area and returns the value. Outputs
   //                toString to return formatted output.
   //
   //  Parameters:   N/A
   //
   //  Returns:      N/A 
   //
   //**************************************************************

   class Circle extends TwoDimensionalShape {
   
    private double radius; 
 
    //***************************************************************
    //
    //  Constructor:  Circle
    //
    //  Description:  Initializes the circle with the given radius.
    //
    //  Parameters:   double radius - the radius of the circle
    //
    //  Returns:      N/A
    //
    //***************************************************************
    
    public Circle(double radius) {
       this.radius = radius;
    }
 
    //***************************************************************
    //
    //  Method:       getArea
    //
    //  Description:  Calculates and returns area of circle. 
    //
    //  Parameters:   N/A
    //
    //  Returns:      double - calculated area
    //
    //***************************************************************

    @Override
    public double getArea() {
       return Math.PI * radius * radius;
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
          "...\nShape: %s\nDimension: Two-Dimensional\nArea: %.2f\n...\n",
          getClass().getSimpleName(),getArea());

       }
}
   