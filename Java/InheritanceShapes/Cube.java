//*************************************************************
//
//  Developer:     Tiffany Pham
//
//  Program #:     One
//
//  File Name:     Cube.java
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
//                 The area and volume of a cube is calculated in 
//                 this program.
//******************************************************************


//***************************************************************
   //
   //  Class:        Cube
   // 
   //  Description:  Inherits shape from ThreeDimensionalShape. Caluculates 
   //                the area and returns the value. Outputs 
   //                toString to return formatted output.
   //
   //  Parameters:   N/A
   //
   //  Returns:      Returns shape type, dimension, volume, and area. 
   //
   //**************************************************************
   class Cube extends ThreeDimensionalShape {
    private double edge;
 
    //***************************************************************
    //
    //  Constructor:  Cube
    //
    //  Description:  Calculates area of cube
    //
    //  Parameters:   double edge
    //
    //  Returns:      N/A
    //
    //***************************************************************

    public Cube (double edge) {
       this.edge = edge;
    }

    //***************************************************************
    //
    //  Method:       getVolume 
    //
    //  Description:  Calculates volume of cube.
    //
    //  Parameters:   N/A
    //
    //  Returns:      Returns volume of cube.
    //
    //*************************************************************** 
 
    public double getVolume() {
       return edge * edge * edge;
    }

    //***************************************************************
    //
    //  Method:       getArea
    //
    //  Description:  Calculates area of cube.
    //
    //  Parameters:   N/A
    //
    //  Returns:      Returns area of cube.
    //
    //*************************************************************** 
 
    @Override
    public double getArea() {
       return 6.0 * edge * edge;
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