//*************************************************************
//
//  Developer:     Tiffany Pham
//
//  Program #:     One
//
//  File Name:     ThreeDimensionalShape.java
//
//  Course:        ITSE 2317 Java Programming (Intermediate)
//
//  Due Date:      6/23/2025
//
//  Instructor:    Prof. Fred Kumi 
//
//  Chapter:       10
//
//  Description:   This is an abstract class from the parent class, Shape. 
//                 This class extends to all 3d shapes listed in Program1.java
//                 Output includes shape type, name, volume, and area.
//
//******************************************************************



//***************************************************************
   //
   //  Class:       ThreeDimensionalShape
   // 
   //  Description:  Inherits from shape, calculates volume,
   //                overrides toString 
   //
   //  Parameters:   None
   //
   //  Returns:      Class, name, and area
   //
   //**************************************************************


   abstract class ThreeDimensionalShape extends Shape {
    
     //***************************************************************
    //
    //  Method:       getVolume
    //
    //  Description:  Abstract method to calculate volume of 3D shape.
    //
    //  Parameters:   None
    //
    //  Returns:      double - the calculated volume
    //
    //***************************************************************
    
        public abstract double getVolume();



   }
   