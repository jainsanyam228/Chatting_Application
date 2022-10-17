import java.util.ArrayList;  
  
public class MaxDistance {  
  
    //Represent a node of binary tree  
    public static class Node{  
        int data;  
        Node left;  
        Node right;  
  
        public Node(int data){  
            //Assign data to the new node, set left and right children to null  
            this.data = data;  
            this.left = null;  
            this.right = null;  
            }  
          }  
  
    //Represent the root of binary tree  
    public Node root;  
  
    int[] treeArray;  
    int index = 0;  
  
    public MaxDistance(){  
        root = null;  
    }  
  
    //calculateSize() will calculate size of tree  
    public int calculateSize(Node node)  
    {  
        int size = 0;  
        if (node == null)  
         return 0;  
        else {  
            size = calculateSize (node.left) + calculateSize (node.right) + 1;  
            return size;  
        }  
    }  
  
