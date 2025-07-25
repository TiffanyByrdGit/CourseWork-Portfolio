//********************************************************************
//
//  Developer:    Tiffany Pham
//
//  Program #:    Three
//
//  File Name:    ServerProgram.java
//
//  Course:       COSC 4301 - Modern Programming
//
//  Instructor:   Prof. Fred Kumi 
//
//  Description:  TCP sever listening on port 4301 that spawns 
//                PrimeTasks threads to handle each connection. 
//
//********************************************************************
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerProgram {
    
   //***************************************************************
   //
   //  Method:       Main
   // 
   //  Description:  Constructs startServer whcih will starts the server,
   //                connects to port  4301 and handles incoming clients.
   //
   //  Parameters:   String[] args
   //
   //  Returns:      N/A
   //
   //*************************************************************

    public static void main(String[] args) {
        new ServerProgram().startServer();
    }

   //***************************************************************
   //
   //  Method:       startServer
   // 
   //  Description:  Starts server connection on port 4301 and 
   //                handles incoming clients. 
   //
   //  Parameters:   N/A
   //
   //  Returns:      N/A
   //
   //*************************************************************

   public void startServer() {
    int port = 4301;

    //server loop to spawn new thread if true or error
    try (ServerSocket serverSocket = new ServerSocket(port)) {
        System.out.println("Server is listening on port " + port + "...");
        
        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("New client is connected to the server.");
        
            Thread thread = new Thread(new PrimeTasks(clientSocket));
            thread.start();
        }
    } catch (IOException e) {
        System.out.println("Server error: " + e.getMessage());
    }
   }
}

