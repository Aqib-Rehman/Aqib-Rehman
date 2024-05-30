/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.Scanner;
import static javaapplication1.DiskSimulation.NOOFSECTORS;

/**
 *
 * @author Rox
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    
    
       public static void main(String[] args) {
        // TODO code application logic here
         
         Scanner scan1 = new Scanner(System.in);
         int choice;
          
         
         DiskSimulation diskObj = new DiskSimulation(); // creating a disk
         
         
         do{
         System.out.println("enter 1 to add a file");
         System.out.println("enter 2 to delete a file");
         System.out.println("enter 3 to fragment");
        
         choice = scan1.nextInt();
         
         if(choice==1){
             
             diskObj.saveFile();// calling saveFile function on a drive
             
          }
         
         else if(choice==2){
             
             diskObj.deleteFile();// calling deleteFile function on a drive
             
          }
         
         else if(choice==3){
             
             diskObj.together();// calling deleteFile function on a drive
             
          }
         
         }while(true);
         
    }
       
    
}
