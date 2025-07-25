//***************************************************************
//  Developer:    Tiffany Pham
//
//  Program #:    Four
//
//  File Name:    GenericSelectionSort.java
//
//  Course:       ITSE 2317 Intermediate Java Programming
//
///  Due Date:     7/21/25
///
//  Instructor:   Fred Kumi
//
//  Chapter:      19
//
//  Description:  This class provides a generic implementation of the
//                selection sort algorithm using Comparable. It includes
//                a method to initiate the sort.
//
//***************************************************************
import java.util.Arrays;
    //***************************************************************
    //  Class:       GenericSelectionSort
    //
    //  Description: Class contaning methods to to sort items in 
    //               Program4.java
    //
    //  Parameters: N/A
    //
    //  Returns:    N/A
    //***************************************************************

import java.util.Arrays;

public class GenericSelectionSort {

    //***************************************************************
    //  Method:      selectionSort
    //
    //  Description: Sorts an array using the selection sort algorithm.
    //
    //  Parameters: T[] list, String description
    //
    //  Returns:    N/A
    //***************************************************************

    private <T extends Comparable<T>> void selectionSort(T[] list) {
      for (int i = 0; i < list.length - 1; i++) {
          int minIndex = i;
          for (int j = i + 1; j < list.length; j++) {
              if (list[j].compareTo(list[minIndex]) < 0) {
                  minIndex = j;
              }
          }
  
          T temp = list[i];
          list[i] = list[minIndex];
          list[minIndex] = temp;
      }
  }

   //***************************************************************
    //  Method: callSelectionSort
    //
    //  Description: Calls the selectionSort method.
    //
    //  Parameters: T[] list, String description
    //
    //  Returns:    N/A
    //***************************************************************
    public <T extends Comparable<T>> void callSelectionSort(T[] list, String description) {
      selectionSort(list);
      System.out.println(description);
      System.out.println(Arrays.toString(list));
      System.out.println();
  }
}