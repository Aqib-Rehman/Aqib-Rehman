/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author Rox
 */

public class AvailableSectors {  
    //Represents the node of list.  
    public class Node{  
        int from;
        int to;
        Node next;  
        Node prev;  
        public Node(int from, int to) {  
            this.from = from;
            this.to=to;
            this.next = null;
            this.prev = null;
        }  
    }  
  
    //Declaring head and tail pointer as null.  
    public Node head = null;  
    public Node tail = null;  
  
    //This function will add the new node at the end of the list.  
    public void add(int from, int to){  
        //Create new node  
        Node newNode = new Node(from,to);  
        //Checks if the list is empty.  
        if(head == null) {  
             //If list is empty, both head and tail would point to new node.  
            head = newNode;  
            tail = newNode;  
            newNode.next = head;  
        }  
        else {  
            //tail will point to new node.  
            tail.next = newNode; 
            newNode.prev = tail;
            //New node will become new tail.  
            tail = newNode;  
            //Since, it is circular linked list tail will point to head.  
            tail.next = head;  
        }  
    }  
  
    //Displays all the nodes in the list  
    public void display() {  
        Node current = head;  
        if(head == null) {  
            System.out.println("List is empty");  
        }  
        else {  
            System.out.println("Nodes of the circular linked list: ");  
             do{  
                //Prints each node by incrementing pointer.  
                System.out.print("from: "+ current.from);  
                System.out.print("to:  "+ current.to);  
                current = current.next;  
            }while(current != head);  
            System.out.println();  
        }  
    }
    
     public int noOfCharsCanBeStored() {  
         int count=0;
        Node current = head;  
        if(head == null) {  
            count=0;  
        }  
        else {  
             
             do{  
                //Prints each node by incrementing pointer.  
                count = count + (current.to-current.from+1); // from range we can get no of chars as we have started from 0 so adding 1
                current = current.next;  
            }while(current != head);  
          
        } 
         return count;
    }
     
     
      public Node getNode() {  
        Node current = head;  
          
          // Check if node is only one node
        if (current.next == head) {
            head = null;
            tail=null;
         }         
              
                // If more than one node, check if
        // it is first node
        
        else{ 
             
            head = current.next;
            head.prev=null;
            tail.next=head;
        }
        
        current.next=null;
          return current;   
      }
           
    
    
}
