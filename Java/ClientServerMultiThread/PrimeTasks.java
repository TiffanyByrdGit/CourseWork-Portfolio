//********************************************************************
//
//  Developer:    Tiffany Pham
//
//  Program #:    Three
//
//  File Name:    PrimeTaks.java
//
//  Course:       COSC 4301 - Modern Programming
//
//  Instructor:   Prof. Fred Kumi 
//
//  Description:  Implement runnable. Parses the string, validates input,
//                finds prime using PrimeTest.isPrine(), calculates sum, 
//                mean, and standard deviation, and sends response to the 
//                user. |Multi-thread handler|
//
//********************************************************************

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.DecimalFormat;

public class PrimeTasks implements Runnable {
    private Socket clientSocket;

   //***************************************************************
   //
   //  Constructor:  PrimeTask
   // 
   //  Description:  Initializes socket for client.   
   //
   //  Parameters:   N/A
   //
   //  Returns:      N/A
   //
   //**************************************************************

    public PrimeTasks(Socket socket) {
        this.clientSocket = socket;
    }
   //***************************************************************
   //
   //  Method:       run
   // 
   //  Description:  Runs one client input at a time, processes input,
   //                validates input, and returns message/results 
   //                depedning on input. 
   //
   //  Parameters:   N/A
   //
   //  Returns:      Error message or result(PrintWriter), and/or
   //                close connection, 
   //
   //**************************************************************

   public void run() {
    try (
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
    ) {
        //read input from client 
        String inputLine = in.readLine();

        //if loop to process or sends error message
        if (inputLine != null && !inputLine.equalsIgnoreCase("Quit")) {
            String validation = validateAndProcess(inputLine, out);
        
        if (!validation.isEmpty()) {
            out.println(validation);
        }
    }

    //inform client code is done running
    out.println("end");

    } catch (IOException e) {
        System.out.println("Error handling client: " + e.getMessage());
    } finally {
        try {
            //release connection 
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Error closing client socket.");
            }
        }
    }
   //***************************************************************
   //
   //  Method:       validateAndProcess
   // 
   //  Description:  Parses input, validates, calculates, and returns
   //                the results.  
   //
   //  Parameters:   String input | PrintWriter out 
   //
   //  Returns:      Results or error message.
   //
   //*************************************************************

   private String validateAndProcess(String input, PrintWriter out) {
    List<String> parts = splitInput(input); //split input 

    //validate input is two numbers with correcting spacing
    if(parts.size() != 2) {
        return "Invalid input. Please enter two positive integers separated by space or comma.";
    }
    //convert user input from string to int
    int start = parsePosInt(parts.get(0));
    int end = parsePosInt(parts.get(1));

    if (start == -1 || end == -1 || start >= end) {
        return "Invalid range: Integers must be positive and the first input must be less than the second input.";

    }

    //call prime method 
    List<Integer> primes = primesInRange(start, end);
    int sum = calculateSum(primes);
    double mean = calculateMean(primes);
    double stdDev = calculateStandardDev(primes, mean);

    //send result to client
    out.println("Prime numbers between " + start + " and " + end + ": " + primes.toString());
    out.println("Sum: " + sum);
    out.println("Mean: " + formatDouble(mean));
    out.println("Standard Deviation: " + formatDouble(stdDev));

    return ""; 

    }

   //***************************************************************
   //
   //  Method:       splitInput
   // 
   //  Description:  Splits input with comma or space  and returns it
   //                correctly into parts.
   //
   //  Parameters:   String input  
   //
   //  Returns:      N/A
   //
   //*************************************************************

   private List<String> splitInput(String input) {
    String[] rawString = input.trim().split("[,\\s]");
    List<String> parts = new ArrayList<>();
    for (int i = 0; i < rawString.length; i++) {
        parts.add(rawString[i]);
    }
    return parts;
   }

   //***************************************************************
   //
   //  Method:       parsePosInt
   // 
   //  Description:  Parses positive integer.
   //
   //  Parameters:   String s
   //
   //  Returns:      Error -1
   //
   //*************************************************************

   private int parsePosInt(String s) {
    try {
        int num = Integer.parseInt(s);
        if (num > 0) return num;
    } catch (NumberFormatException e) {
        }
        return -1;
   }

   //***************************************************************
   //
   //  Method:       primesInRange
   // 
   //  Description:  Calls isPrime to collect prime numbers in range
   //                from start to end.
   //
   //  Parameters:   int a | int b
   //
   //  Returns:      N/A
   //
   //*************************************************************

   private List<Integer> primesInRange(int a, int b) {
    List<Integer> primes = new ArrayList<>();
    int i = a;

    PrimeTest tester = new PrimeTest();

    while (i <= b) {
        if (tester.isPrime(i)) {
            primes.add(i);
        }
        i++;
    }
    return primes;
   }

   //***************************************************************
   //
   //  Method:       calculateSum
   // 
   //  Description:  Calculates sum from list of integers.
   //
   //  Parameters:   List<Integer> list
   //
   //  Returns:      N/A
   //
   //*************************************************************

   private int calculateSum(List<Integer> list) {
    int sum = 0;
    int i = 0;
    while (i < list.size()) {
        sum += list.get(i);
        i++;
    }
    return sum;
   }

   //***************************************************************
   //
   //  Method:       calculateMean
   // 
   //  Description:  Calculates mean from list of integers.
   //
   //  Parameters:   List<Integer> list | double mean
   //
   //  Returns:      N/A
   //
   //*************************************************************

   private double calculateMean(List<Integer> list) {
    if (list.size() == 0 ) return 0.0;
    return (double) calculateSum(list) / list.size();
   }

   //***************************************************************
   //
   //  Method:       calculateStandardDev
   // 
   //  Description:  Calculates standard deviation from calculated mean.
   //
   //  Parameters:   List<Integer> list | double mean
   //
   //  Returns:      N/A
   //
   //*************************************************************

   private double calculateStandardDev(List<Integer> list, double mean) {
    if (list.size() <= 1) return 0.0;
    double sumSquaredDiffs = 0.0;
    int i = 0;

    while (i < list.size()) {
        double diff = list.get(i) - mean;
        sumSquaredDiffs += diff * diff;
        i++;
    }
    return Math.sqrt(sumSquaredDiffs / (list.size() -1));
   }

   //***************************************************************
   //
   //  Method:       formatDouble
   // 
   //  Description:  Formats decimals with two decimal spaces. 
   //
   //  Parameters:   double value
   //
   //  Returns:      N/A
   //
   //*************************************************************

   private String formatDouble(double value) {
    DecimalFormat df = new DecimalFormat("#.##");
    return df.format(value);
   }

   }

