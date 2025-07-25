
//*************************************************************
//
//  Developer:     Tiffany Pham
//
//  Program #:     One
//
//  File Name:     Sphere.java
//
//  Course:        ITSE 2317 Java Programming (Intermediate)
//
//  Due Date:      6/23/2025
//
//  Instructor:    Prof. Fred Kumi 
//
//  Chapter:       10
//
//  Description:   This is class from the the abstract class, 3d.
//                 The area and volume of a sphere is calculated in 
//                 this program.
//******************************************************************


   //***************************************************************
   //
   //  Class:         Sphere
   // 
   //  Description:  Inherits shape from ThreeDimensionShape. Caluculates 
   //                the area and returns the value. Outputs 
   //                toString to return formatted output.
   //
   //  Parameters:   N/A
   //
   //  Returns:      Returns shape type, dimension, volume, and area. 
   //
   //**************************************************************
   class Sphere extends ThreeDimensionalShape {
    private double radius;
 
    //***************************************************************
    //
    //  Constructor:  Sphere
    //
    //  Description:  Calculates area of sphere
    //
    //  Parameters:   double radius
    //
    //  Returns:      N/A
    //
    //***************************************************************

    public Sphere (double radius) {
       this.radius = radius;
    }
 
    //***************************************************************
    //
    //  Method:       getVolume 
    //
    //  Description:  Calculates volume of sphere.
    //
    //  Parameters:   N/A
    //
    //  Returns:      Returns volume of sphere.
    //
    //*************************************************************** 

    public double getVolume() {
       return (4.0 / 3.0) * Math.PI * radius * radius * radius;
    }

    //***************************************************************
    //
    //  Method:       getArea
    //
    //  Description:  Calculates area of sphere.
    //
    //  Parameters:   N/A
    //
    //  Returns:      Returns area of sphere.
    //
    //*************************************************************** 
 
    @Override
    public double getArea(){
       return 4 * Math.PI * radius * radius; 
    }
 
        //***************************************************************
    //
    //  Method:       toString
    //
    //  Description:  Returns a fomatted string. 
    //
    //  Parameters:   N/A
    //
    //  Returns:      Returns shape type, dimension, volume, and area
    //
    //*************************************************************** 
 
    
    public String toString(){
       return String.format( 
          "Shape: %s\nDimension: Three-Dimensional\nArea: %.2f\nVolume: %.2f\n...\n",
          getClass().getSimpleName(),getArea(), getVolume());
    }
    
 }