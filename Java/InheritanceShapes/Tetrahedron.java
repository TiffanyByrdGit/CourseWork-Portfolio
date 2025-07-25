   
//*************************************************************
//
//  Developer:     Tiffany Pham
//
//  Program #:     One
//
//  File Name:     Tetrahedron.java
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
//                 The area and volume of a tetrahedron is calculated in 
//                 this program.
//******************************************************************
   
   
   
   //***************************************************************
   //
   //  Class:        Tetrahedron
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
   class Tetrahedron extends ThreeDimensionalShape {
    private double edge;
 
    //***************************************************************
    //
    //  Constructor:  Tetrahedron
    //
    //  Description:  Calculates area of Tetrahedron
    //
    //  Parameters:   double edge
    //
    //  Returns:      N/A
    //
    //***************************************************************

    public Tetrahedron(double edge) {
       this.edge = edge;
    }

    //***************************************************************
    //
    //  Method:       getVolume 
    //
    //  Description:  Calculates volume of Tetrahedron.
    //
    //  Parameters:   N/A
    //
    //  Returns:      Returns volume of Tetrahedron.
    //
    //***************************************************************
 
    public double getVolume() {
       return (edge * edge * edge) / (6 * Math.sqrt(2));
       }

    //***************************************************************
    //
    //  Method:       getArea
    //
    //  Description:  Calculates area of Tetrahedron
    //
    //  Parameters:   N/A
    //
    //  Returns:      Returns area of Tetrahedron.
    //
    //*************************************************************** 
 
    @Override
    public double getArea() {
       return Math.sqrt(3) * edge * edge;
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