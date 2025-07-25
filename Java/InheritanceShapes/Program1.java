//*************************************************************
//
//  Developer:     Instructor
//
//  Program #:     One
//
//  File Name:     Program1.java
//
//  Course:        ITSE 2317 Java Programming (Intermediate)
//
//  Due Date:      6/23/2025
//
//  Instructor:    Prof. Fred Kumi 
//
//  Chapter:       10
//
//  Description:   This program displays the attributes of each
//                 shape type, including type, dimension type,
//                 area, and volume if applicable. 
//
//                 You are allowed to modify only line 84. If you
//                 modify any other part of the class, you will
//                 not receive credit for this project.
//
//******************************************************************

public class Program1
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
   public static void main(String[] args)
   {
       // Variables
       double edge      = 2.5,
       length    = 2.5,
       radius    = 5.75,
     base	    = 7.95,
     height    = 10.33;
		int	  numShapes = 6;
		
	    // Create an object of the main class and use it to call
	    // the non-static developerInfo method
	    Program1 obj = new Program1();
	    obj.developerInfo();

	    // Create array of shape references to each concrete shape type
	    Shape[] shapes = new Shape[numShapes];
		
		shapes[0] = new Circle(radius);
		shapes[1] = new Square(length);
		shapes[2] = new Triangle(base, height);
		shapes[3] = new Sphere(radius);
		shapes[4] = new Cube(edge);
		shapes[5] = new Tetrahedron(edge);
		
		for (Shape currentShape : shapes)
		{
			System.out.println(currentShape);
		}
	} // End of the main method
	
   //***************************************************************
   //
   //  Method:       developerInfo (Non Static)
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
      System.out.println("Course:   ITSE 2317 Java Programming (Intermediate)");
      System.out.println("Project:  One");
	  System.out.println("Due Date: 6/23/2025\n");
   } // End of the developerInfo method
   
} // End of test class Program1








