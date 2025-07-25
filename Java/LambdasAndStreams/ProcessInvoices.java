//***************************************************************
//
//  Developer:    Tiffany Pham
//
//  Program #:    Three
//
//  File Name:    ProcessInvoices.java
//
//  Course:       ITSE 2317 Intermediate Java Programming
//
//  Due Date:     7/14/2025
//
//  Instructor:   Fred Kumi
//
//  Chapter:      17
//
//  Description:  Performs queries on the array Invoice objects
//                and displays results.  
//
//***************************************************************

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.AbstractMap;
import java.util.function.Predicate;


   //***************************************************************
   //
   //  Class:        ProcessInvoices
   // 
   //  Description:  Runs methods Solution Part A-F to accomplish
   //                the required quries.
   //
   //  Parameters:   N/A
   //
   //  Returns:      N/A
   //
   //**************************************************************

public class ProcessInvoices {
    
   //***************************************************************
   //
   //  Method:       solutionPartA
   // 
   //  Description:  Sort partDescription and displays results.
   //
   //  Parameters:   ArrayList<Invoice> invoiceList
   //
   //  Returns:      Sorted list of partDescription
   //
   //**************************************************************

   public void solutionPartA(ArrayList<Invoice> invoiceList) {
      System.out.println("Part A - Sorted by Part Description:");
      System.out.println("------------------------------------");  

      invoiceList.stream()
         .sorted(Comparator.comparing(Invoice::getPartDescription))
         .forEach(inv -> System.out.printf("Part #: %-3d Description: %-15s Quantity: %-3d Price: $%.2f%n",
                  inv.getPartNumber(),
                  inv.getPartDescription(),
                  inv.getQuantity(),
                  inv.getPrice()));

      System.out.println();
   }

   //***************************************************************
   //
   //  Method:       solutionPartB
   // 
   //  Description:  Sorts the Invoice objects by price, 
   //                then display the results.
   //
   //  Parameters:   ArrayList<Invoice> invoiceList
   //
   //  Returns:      Sorted list of price.
   //
   //**************************************************************

   public void solutionPartB(ArrayList<Invoice> invoiceList) {
    System.out.println("Part B - Sorted by Price Per Item:");
    System.out.println("----------------------------------");

    invoiceList.stream()
        .sorted(Comparator.comparing(Invoice::getPrice))
        .forEach(inv -> System.out.printf("Part #: %-3d Description: %-15s Quantity: %-3d Price: $%.2f%n",
        inv.getPartNumber(),
        inv.getPartDescription(),
        inv.getQuantity(),
        inv.getPrice()));

    System.out.println();
   }

   //***************************************************************
   //
   //  Method:       solutionPartC
   // 
   //  Description:  Map each Invoice to its partDescription and quantity, 
   //                sort the results by quantity, then display the results.
   //
   //  Parameters:   ArrayList<Invoice> invoiceList
   //
   //  Returns:      Mapped key-value pairs sorted by quantity.
   //
   //**************************************************************

   public void solutionPartC(ArrayList<Invoice> invoiceList) {
    System.out.println("Part C - Sorted by Quantity:");
    System.out.println("----------------------------");

    invoiceList.stream()
        .map(inv -> new AbstractMap.SimpleEntry<>(inv.getPartDescription(), inv.getQuantity()))
        .sorted(Map.Entry.comparingByValue())
        .forEach(entry -> System.out.printf("Description: %-15s  Quantity: %-3d%n", entry.getKey(), entry.getValue()));

    System.out.println();

   }

   //***************************************************************
   //
   //  Method:       solutionPartD
   // 
   //  Description:  Map each Invoice to its partDescription and the value of the
   //                Invoice and order the results by Invoice value.
   //
   //  Parameters:   ArrayList<Invoice> invoiceList
   //
   //  Returns:      Mapped key-value pairs sorted by Invoice value.
   //
   //**************************************************************

   public void solutionPartD(ArrayList<Invoice> invoiceList) {
    System.out.println("Part D - Sorted by Invoice Value:");
    System.out.println("---------------------------------");

    invoiceList.stream()
    .map(inv -> new AbstractMap.SimpleEntry<>(
        String.format("Part #: %-2d  Description: %-15s", inv.getPartNumber(), inv.getPartDescription()),
        inv.getQuantity() * inv.getPrice()
    ))
    
    .sorted(Map.Entry.comparingByValue())
    .forEach(entry -> System.out.printf("%s  Invoice Value: $%.2f%n", entry.getKey(), entry.getValue()));

    System.out.println();

      }

   //***************************************************************
   //
   //  Method:       solutionPartE
   // 
   //  Description:  Modifies SolutionPartD by selecting invoice values
   //                in range $200.00-$500.00
   //
   //  Parameters:   ArrayList<Invoice> invoiceList
   //
   //  Returns:      List of invoices ranging from $200-$500.
   //
   //**************************************************************

   public void solutionPartE(ArrayList<Invoice> invoiceList) {
    System.out.println("Part E - Sorted by Invoice Value ($200-$450):");
    System.out.println("---------------------------------------------");

    Predicate<Map.Entry<String, Double>> twoToFiveHundred = 
    entry -> entry.getValue() >= 200 && entry.getValue() <= 500;

    invoiceList.stream()
    .map(inv -> new AbstractMap.SimpleEntry<>(
        String.format("Part #: %-2d  Description: %-15s", inv.getPartNumber(), inv.getPartDescription()),
        inv.getQuantity() * inv.getPrice()
    ))
    
    .filter(twoToFiveHundred)
    .sorted(Map.Entry.comparingByValue())
    .forEach(entry -> System.out.printf("%s  Invoice Value: $%.2f%n", entry.getKey(), entry.getValue()));

    System.out.println();

   }

   //***************************************************************
   //
   //  Method:       solutionPartF
   // 
   //  Description:  Findf any one Invoice in which the partDescription 
   //                contains the word “Saw”
   //
   //  Parameters:   ArrayList<Invoice> invoiceList
   //
   //  Returns:      List of words containing 'Saw'.
   //
   //**************************************************************

   public void solutionPartF(ArrayList<Invoice> invoiceList) {
    System.out.printf("Part F - Invoices with the word 'Saw':\n");
    System.out.println("-------------------------------------");

    invoiceList.stream()
    .filter(inv -> inv.getPartDescription().toLowerCase().contains("saw"))
    .findFirst()
    .ifPresent(inv -> System.out.printf(
        "Part #: %-2d  Description: %-15s  Quantity: %-3d  Price: $%.2f%n",
        inv.getPartNumber(), inv.getPartDescription(), inv.getQuantity(), inv.getPrice()
    ));

    System.out.println();

   }
   
}
