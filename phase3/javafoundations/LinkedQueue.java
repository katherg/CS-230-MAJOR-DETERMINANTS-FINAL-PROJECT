//********************************************************************
//  LinkedQueue.java       Java Foundations
//
//  Represents a linked implementation of a queue.
//********************************************************************

package javafoundations;

import javafoundations.exceptions.*;

public class LinkedQueue<T> implements Queue<T> {
 private int count;
 private LinearNode<T> front, rear;

 //-----------------------------------------------------------------
 //  Creates an empty queue.
 //-----------------------------------------------------------------
 public LinkedQueue() {
  count = 0;
  front = rear = null;
 }

 //-----------------------------------------------------------------
 //  Adds the specified element to the rear of this queue.
 //-----------------------------------------------------------------
 public void enqueue (T el) {
   //create a LinearNote out of the input element
  LinearNode<T> node = new LinearNode<T>(el);

  if (count == 0)
   front = node;
  else
   rear.setNext(node);

  rear = node;
  count++;
 }

 //-----------------------------------------------------------------
 //  The following methods are left as Programming Projects.
 //-----------------------------------------------------------------
 public T dequeue () throws EmptyCollectionException {
  T old = front.getElement();
  front = front.getNext();
  
  count --;
  
  return old;
 }
 
 public T first () throws EmptyCollectionException {
  return front.getElement();
 }
 
 
 public boolean isEmpty() {
   if (count == 0){
    return true; 
   }else {
    return false; 
   }
   
 }
 
 public int size() {
   return count;
  
 }
 
 public String toString() {

   String result = "<front of queue>\n";
      LinearNode current = front;

      while (current != null)
      {
         result += current.getElement() + "\n";
         current = current.getNext();
      }

      return result + "<rear of queue>";
 }
 
 public static void main(String[] args) {
 LinkedQueue<String> q = new LinkedQueue<String>();
 q.enqueue("hello");
 q.enqueue("hey");
 System.out.println(q.isEmpty());
 System.out.println(q);
 q.dequeue();
 System.out.println(q);
 }
 
}
