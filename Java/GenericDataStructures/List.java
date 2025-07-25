//********************************************************************
//
//  Developer:     Textbook Authors
//
//  Program #:     Five
//
//  File Name:     List.java
//
//  Course:        ITSE 2317 Intermediate Java Programming
//
//  Instructor:    Fred Kumi 
//
//  Chapter:       21
//
//  Description:   Fig. 21.3: List.java
//                 ListNode and List class declarations.
//
//********************************************************************

import java.util.NoSuchElementException;

// class List definition
public class List<E>
{
   private ListNode<E> firstNode;
   private ListNode<E> lastNode; 
   private String name; // string like "list" used in printing

   // constructor creates empty List with "list" as the name
   public List()
   {
	   this("list");
   }

   // constructor creates an empty List with a name
   public List(String listName)
   {
      name = listName;
      firstNode = lastNode = null;
   } 

   // insert item at front of List
   public void insertAtFront(E insertItem)
   {
      if (isEmpty()) { // firstNode and lastNode refer to same object
         firstNode = lastNode = new ListNode<E>(insertItem);
      } 
      else { // firstNode refers to new node
         firstNode = new ListNode<E>(insertItem, firstNode);
      } 
   } 

   // insert item at end of List
   public void insertAtBack(E insertItem)
   {
      if (isEmpty()) { // firstNode and lastNode refer to same object
         firstNode = lastNode = new ListNode<E>(insertItem);
      } 
      else { // lastNode's nextNode refers to new node
         lastNode = lastNode.nextNode = new ListNode<E>(insertItem);
      } 
   } 

   // remove first node from List
   public E removeFromFront() throws NoSuchElementException
   {
      if (isEmpty()) // throw exception if List is empty
	  { 
         throw new NoSuchElementException(name + " is empty");
      }

      E removedItem = firstNode.data; // retrieve data being removed

      // update references firstNode and lastNode 
      if (firstNode == lastNode)
	  {
         firstNode = lastNode = null;
      }
      else
	  {
         firstNode = firstNode.nextNode;
      }

      return removedItem; // return removed node data
   } 

   // remove last node from List
   public E removeFromBack() throws NoSuchElementException
   {
      if (isEmpty()) // throw exception if List is empty
	  { 
         throw new NoSuchElementException(name + " is empty");
      }

      E removedItem = lastNode.data; // retrieve data being removed

      // update references firstNode and lastNode
      if (firstNode == lastNode)
	  {
         firstNode = lastNode = null;
      }
      else
	  {  // locate new last node
         ListNode<E> current = firstNode;

         // loop while current node does not refer to lastNode
         while (current.nextNode != lastNode)
		 {
            current = current.nextNode;
         }
   
         lastNode = current; // current is new lastNode
         current.nextNode = null;
      } 

      return removedItem; // return removed node data
   } 

   // determine whether list is empty; returns true if so
   public boolean isEmpty()
   {
	   return firstNode == null;
   }

   // output list contents
   public void print()
   {
      if (isEmpty())
	  {
         System.out.printf("Empty %s%n", name);
         return;
      } 

      System.out.printf("The %s is: ", name);
      ListNode<E> current = firstNode;

      // while not at end of list, output current node's data
      while (current != null)
	  {
         System.out.printf("%s ", current.data);
         current = current.nextNode;
      } 

      System.out.println();
   } 
} 
