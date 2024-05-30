/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.ArrayList;
import java.util.List;
import static javaapplication1.DiskSimulation.availableSectorsObj;

/**
 *
 * @author Rox
 */
public class Files {    
    //Represent a node of the singly linked list    
    class Node{  
        String fileName;
        int noOfChars;
        AvailableSectors.Node contentsNode;  
        Node next;    
            
        public Node(String fileName,int noOfChars) {    
            this.fileName = fileName;    
            this.noOfChars = noOfChars;    
            this.contentsNode = null;    
            this.next = null;    
        }    
    }    
     
    //Represent the head  of the singly linked list    
    public Node head = null;     
        
    //save() will add a new node to the list    
    public ArrayList save(String fileName,int noOfChars) {  
        ArrayList<Integer> availableRanges = new ArrayList();// to store disk index in which contents will be stored
        
           //Create a new node    
        Node newNode = new Node( fileName, noOfChars);    
        
        int availableStorage = availableSectorsObj.noOfCharsCanBeStored();
        if(availableStorage==0){ // check for full storage
            availableRanges.add(-1);
            
        }
       else if(availableStorage<noOfChars){ // check for less storage
           availableRanges.add(-2);
        }
        else{
           int allotedChars=0;
           
           while(allotedChars<noOfChars){ // loop for assigining more then 1 sector if file needs more sectors
               
               
               AvailableSectors.Node temp;
               temp =  availableSectorsObj.getNode(); //getting a available node
               availableRanges.add(temp.from);
               availableRanges.add(temp.to);
               
               allotedChars = allotedChars +  (temp.to-temp.from+1); //adding range of a sector retrieved from a sectors pool
             
//               adding a new sector with the exisiting linked list
             AvailableSectors.Node current = newNode.contentsNode;    
            
             //if its a first sector
        if(current == null) {    
            newNode.contentsNode = temp; 
            temp.next = newNode.contentsNode;
        } 
        else{
        while(current.next != null) {  // finding a last node  
             current = current.next;    
        }    
          current.next= temp;
          temp.next = newNode.contentsNode;
               
        }      
               
               
           }
           
      
           

//Checks if the list is empty    
        if(head == null) {    
            //If list is empty,   head  will point to new node    
            head = newNode;     
        }    
        else {    
            //newNode will be added after last node such that last node next will point to newNode    
           Node current2 = head; 
           while(current2.next != null) {    
                   
            current2 = current2.next;    
        }  
           current2.next= newNode;
             
        }   
        
        }
        return availableRanges;
    }
    
    
    public int delete(String fileName){
         
        int flag=0; // for checking file found or not
        AvailableSectors.Node crnt = null; // for freeing sectors
        
       
        // Storing head node
        Node currNode = head, prev = null;
  
        // If head node itself holds the key to be deleted
   if (currNode != null && currNode.fileName.equals(fileName)) {
            crnt = head.contentsNode;
            head = currNode.next; // traversing
            
            flag=1; 
    }
   else{
        // If not in head
        while (currNode != null && !currNode.fileName.equals(fileName)) {  
            
            prev = currNode; // tracking previous node for linking
            currNode = currNode.next;
        }
  
        if (currNode != null) { // means file is present 
            prev.next = currNode.next;
            crnt = currNode.contentsNode;
            flag=1;
        } 
        else if (currNode == null) {  // means file is not present 
              
            flag=-1;
        }
        
   }
        
        //freeing the sectors and adding back to pool
        if(flag==1){
            if(crnt.equals(currNode.contentsNode)){
                availableSectorsObj.add(crnt.from, crnt.to);// adding node to available sectors
            }
            else{
             while(crnt != currNode.contentsNode) {    
            availableSectorsObj.add(crnt.from, crnt.to);// adding node to available sectors
            crnt = crnt.next;     // moving on next node
        }  
            }
        }
        
     return flag;   
        
    }
        
    //display() will display all the nodes present in the list    
    public void display() {    
        //Node current will point to head    
        Node current = head;    
            
        if(head == null) {    
            System.out.println("List is empty");    
            return;    
        }    
        System.out.println("Nodes of singly linked list: ");    
        while(current != null) {    
            //Prints each node by incrementing pointer    
            System.out.print(current.fileName + " ");    
            System.out.print(current.noOfChars + " ");         
            current = current.next;    
        }    
        System.out.println();    
    }    
    
    public int together(){
       
         
         Node fileCurrent = head;    
            
        if(head == null) {    
            
            return -1;    
        }    
        
        
        
        while(fileCurrent != null) {    // traversing file nodes
            
            
           AvailableSectors.Node allocatedNode= fileCurrent.contentsNode; // getting file allocated sectors
           AvailableSectors.Node freeNode  = availableSectorsObj.head;
           
           
             while(allocatedNode != fileCurrent.contentsNode) { // traversing allocated nodes
                 
                  while(freeNode != availableSectorsObj.head) { // traversing free nodes
                 
                 if(allocatedNode.from>freeNode.from){ // means a sector before assigned one is empty and can be used
//                   swapping ranges
                     int tempFrom = allocatedNode.from;
                     int tempTo = allocatedNode.to;
                     allocatedNode.from = freeNode.from;
                     allocatedNode.to = freeNode.to;
                     freeNode.from = tempFrom;
                     freeNode.to = tempTo;
                 }
                 
                 
                 freeNode = freeNode.next;
             }
                 
                 
                 allocatedNode = allocatedNode.next;
             }
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            fileCurrent = fileCurrent.next;    
        }    
        
    return 1;    
   }
   
    
}
