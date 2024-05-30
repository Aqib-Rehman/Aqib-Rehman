/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Rox
 */
public class DiskSimulation {
    
    final static int NOOFSECTORS = 3;
    final static int SIZEOFSECTOR = 20;
    public static AvailableSectors availableSectorsObj;//static so can be access in all classes and its common for all
    //         creating 1 d array of given size
    char disk[] = new char[NOOFSECTORS*SIZEOFSECTOR];
    Files filesObj;
    public DiskSimulation(){ // constructor to add sectors in a pool
        
         availableSectorsObj = new AvailableSectors();
         int range=0;
//         in the start all sectors are available so creating nodes of available sectors.
         for (int i=0;i<NOOFSECTORS;i++){
          availableSectorsObj.add(range, range+SIZEOFSECTOR-1);
         range+=SIZEOFSECTOR;
          }
         System.out.println("all available sectors are created");
         filesObj = new Files(); // creating a file object
    }
    
   public void saveFile(){
       Scanner scan1 = new Scanner(System.in);
       Scanner scan2 = new Scanner(System.in);
          String fileName, fileContents;
         int noOfChars;
         
         //local varaiables related to file data
        System.out.println("enter file name");
             fileName = scan2.next();
              System.out.println("enter No. of Characters");
             noOfChars = scan1.nextInt();
             System.out.println("enter file contents");
             fileContents = scan2.next();
             int fileContentsLength = fileContents.length();
            
             while(fileContentsLength!=noOfChars){ // loop if entered contents length is not match with no. of characters
                 
             System.out.println("entered contents length is not match with no. of characters");
             System.out.println("enter file contents again");
             fileContents = scan2.next();
             fileContentsLength = fileContents.length();
             }
             
             
            ArrayList<Integer> indexesRange =  filesObj.save(fileName, noOfChars); // assiging sectors
            
            if(indexesRange.get(0)==-1){
                    System.out.println("Disk is full");
                }
            else if(indexesRange.get(0)==-2){
                     System.out.println("sorry file can not be stored. Low storage is available");
                }
            else{
           
                int indexOfContents=0;
            for(int i =0;i<indexesRange.size();i++){
                // getting range from arraylist  
                int from = indexesRange.get(i);
                i++;
                int to = indexesRange.get(i);
                
                for(int j =from; j<to && indexOfContents<fileContents.length(); j++){ // looping on it
                    disk[j] = fileContents.charAt(indexOfContents++);// adding contenets in a file
                }
                
            }
            }
             
   }
   
   public void deleteFile(){ 
       Scanner scan2 = new Scanner(System.in);
          String fileName;
       System.out.println("enter file name to delete");
             fileName = scan2.next();
             
             
             int deleted =  filesObj.delete(fileName);
             if(deleted==1){
                 
 System.out.println(fileName + " found and deleted");
             }
             else if(deleted==-1){
                 
            System.out.println(fileName + " not found");
             }
       
   }
   
   public void together(){
       
      int ans = filesObj.together();
      if (ans ==-1)
             System.out.println("List is empty");  
      else{
          System.out.println("Fragmentation process is completed");
      }
              
   }
   
   
     
    
}
