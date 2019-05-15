/*
 * Program made by Jordan Germinal
 *
 * 
 */
//package bst;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Jordan Germinal
 */
 
 public class Hw01{
 
  public static void main(String[] args) throws FileNotFoundException, IOException {
      
      
   
      
        // TODO code application logic here
        
         BST tree = new BST();
          Node root = null;
          // if argument is less than 1 , then its a call on Complexity function 
  
       if(args.length>0)
      {  
          String arraystrings,arraystrings2,firstpart= "f",secondpart;
      String filename= args[0];
      // First reader for file 
      FileReader fr = new FileReader(filename);
      //Second reader to print contains  
       FileReader fr2 = new FileReader(filename);
    // The two required buffered Readers
        BufferedReader br2 = new BufferedReader(fr2);
       BufferedReader br = new BufferedReader(fr);
       
  
       
    int number=0;
    
        System.out.println(filename+" contains:");
       // Printing what is inside the file
         while((arraystrings2 = br2.readLine()) != null)
    {
        System.out.println(arraystrings2);
        
    }

   
    
   //Parsing over the txt file
    while((arraystrings = br.readLine()) != null)
    {
     //br = new BufferedReader(fr);
 // if string line contains more than 1 charactr then proceed 
  //  if
  int x =(arraystrings.length()!=1)?1:0; 
   
   if(x==1) {
// separate them by a space
String[] parts = arraystrings.split("\\s");
    
  // the two parts separated 1 and 2
firstpart = parts[0]; 
secondpart = parts[1]; 
 number = Integer.parseInt(secondpart);

    }


else if(x==0)
  {
      // if only one character in string then part 1 is the whole string
      firstpart =arraystrings;
   }


switch(firstpart){
case "i":
            root=tree.insert(root, number);
              continue;
            
case "s":
            
              
            tree.search(root, number);
            continue;
case "d":
            
           
            root=tree.Deletion(root, number);
           
           continue;
case "p":
            
          
            tree.Print(root);
              System.out.println();
            continue;
case"q":
          
          
          
          tree.printresults(root,tree);
       
            // The exit Statements 
           
        System.exit(0);
          
}
} 
br.close();

      }
  }
 
 
 
 
 
 }

  class BST {
        
    
      // Insert Function
    public  Node insert(Node root,int data)
    {
    // If root is null , Then the node is the root
    if (root==null)
    {    // instance of node 
        Node node = new Node();
      // assign it its data
       node.data=data;
       // assigning left and right to null
        node.left=null;
        node.right=null;
        // return null
        return node;
    }
    
   
    // if smaller than root go left
    if (data <root.data)
    root.left=insert(root.left,data);
    ///if greater go right 
    if(data> root.data)
        root.right=insert(root.right,data);
        // if equal to root insert right 
    if(data==root.data)
    {
    root.right=insert(root.right,data);
    }
    else 
    {}
    
    // return root 
     return root;
    }
    
   public void printresults(Node root,BST tree)
   {
  
         System.out.printf("left children:%11d\n",tree.numberofChild(root.left));
        System.out.printf("left depth:%14d\n",tree.Depth(root.left));
        System.out.printf("right children:%10d\n",tree.numberofChild(root.right));
        System.out.printf("right depth:%13d\n",tree.Depth(root.right));
        
          
   
   }
    
    //Function to Print
    public void Print(Node root)
    {
    
    if(root==null)
    
    {  
       
        }
        else{
        // print tree in order , left root right
    Print(root.left);
        System.out.print(" "+root.data);
        Print(root.right);
   
    
    }
    }
    
    public Node Deletion(Node root,int data)
    {
        // temp node to use for deletion 
        Node temp;
    if(root==null)
    {
        //System.out.println("100: NOT found");
         System.err.println(data+": NOT found");
        return null;
    }
    // less than go left
    else if(data<root.data)
    {   
        root.left=Deletion(root.left,data);
    }
    // greater than go right 
    else if(data>root.data)
    {
        root.right=Deletion(root.right,data);
    }
    else
    {
    // if both null then we didnt find anything so return 
    if(root.left== null && root.right== null)
    {
        root=null;
        //System.out.println(data+" Not Found");
    }
    // if node is only left child then child go up
    else if(root.right==null)
     {
     //assign temp vairable in order to not lose connect with the tree after freeing the node
     temp=root.left;
    root=temp;
     }
     //if only right vhild then right child goes up
    else if(root.left==null)
     {
     //assign temp vairable in order to not lose connect with the tree after freeing the node
     temp=root.right;
     root=temp;
         
     }
     else 
     {
     // in case we need to delete a node with 2 children, we get the mininum value on the right side 
         root.data=min(root.right);
         root.right=Deletion(root.right,root.data);
     
     }
    
    }
    
    // Return root
    
    return root;
    
    }
    
    
    public int min(Node root)
    {         
    while(root.left!=null)
    {   
        root=root.left;
    
    }
    return root.data;
    
    }
    // Funtion to count number of children 
    public int numberofChild(Node root)
    {  //if root is null return 0 , otherwise count number of chhildren 
    return (root==null)?0:(1+ numberofChild(root.left)+ numberofChild(root.right));
    
    }
    
    // Function to get depth of tree 
    public int Depth(Node root)
    {
        
    // If root is null return 
        if(root==null)
        return 0;
        // Compare the the righ and left depth , and use the bigger of the two 
        else
        {  int ld=Depth(root.left);
            int  rd=Depth(root.right);
             // return the bigger of the two
           return (ld>rd)?(1+ld):(1+rd);     
    }
    
    }
  // Function to search  
    public void search(Node root,int data)
    {
    if(root==null)
    {
        System.out.println(data+": NOT found");
       
       return;
    }
     
    if(data==root.data)
    {
        //Print if data is found
        System.out.println(data+": found");
    return;
    }
    // if both left and right  is null , then we didnt find it 
    if(root.right==null && root.left==null)
    { 
        System.out.println(data+": NOT found");
    
    return;
    }
    else 
    // Keep searching if not found
    {   
    if(data<root.data)
            search(root.left,data);
            else
        search(root.right,data);
    }
    }
}

// Node Class to use for every Node
class Node{

Node left;
Node right;
int data;
}
  