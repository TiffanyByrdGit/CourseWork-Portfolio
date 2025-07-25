//********************************************************************
//
//  Developer:    Tiffany Pham
//
//  Program #:    Three
//
//  File Name:    Client.java
//
//  Course:       COSC 4301 - Modern Programming
//
//  Instructor:   Prof. Fred Kumi 
//
//  Description:  Connects to the server, gets user input, receives results, 
//                continues loop or "Quit" the program and closes the connection.
//                Prints developer info.
//
//********************************************************************

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    
   //***************************************************************
   //
   //  Method:       Main
   // 
   //  Description:  Constructs startClient, connects to local host; 
   //                port 4301, gets user input, prompts to start program
   //                again or quit. Closes the connection.
   //
   //  Parameters:   String[] args
   //
   //  Returns:      Developer Info
   //
   //**************************************************************

   public static void main(String[] args) {
    String serverHostname = "127.0.0.1";
    int port = 4301;
    printDeveloperInfo();

    try (
        Socket socket = new Socket(serverHostname, port);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Scanner scanner = new Scanner(System.in);
    ) {
        System.out.print("Enter two positive integers separated by a space or comma (or type 'Quit' to exit): ");
        String input = scanner.nextLine();
        out.println(input);

        boolean keepReading = true;
        while (keepReading) {
            String line = in.readLine();
            if (line == null || line.equalsIgnoreCase("end")) {
                keepReading = false;
            } else {
                System.out.println(line);
            }
        }
    } catch (IOException e) {
        System.out.println("Client error: " + e.getMessage());
    }
}
//***************************************************************
//
//  Method:       printDeveloperInfo
// 
//  Description:  Displays developer information.
// 
//  Parameters:   None
// 
//  Returns:      Developer info
//
//***************************************************************
public static void printDeveloperInfo() {
    System.out.println("Developer:    Tiffany Pham");
    System.out.println("Program #:    Three");
    System.out.println("File Name:    Client.java");
    System.out.println("Course:       COSC 4301 - Modern Programming");
    System.out.println("Instructor:   Prof. Fred Kumi\n");

}
}