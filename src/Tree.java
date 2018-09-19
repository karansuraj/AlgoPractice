import java.util.*;

// Java program to construct binary tree from
// given array in level order fashion 
// Taken from geeksforgeeks to help solve LeetCode Tree problems
// https://www.geeksforgeeks.org/construct-complete-binary-tree-given-array/
public class Tree {
    Node root;

    // Tree Node 
    static class Node {
        int data;
        Node left, right;
        Node(int data)
        {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // Function to insert nodes in level order 
    public Node insertLevelOrder(int[] arr, Node root,
                                 int i)
    {
        // Base case for recursion 
        if (i < arr.length) {
            Node temp = new Node(arr[i]);
            root = temp;

            // insert left child 
            root.left = insertLevelOrder(arr, root.left,2 * i + 1);

            // insert right child 
            root.right = insertLevelOrder(arr, root.right,2 * i + 2);
        }
        return root;
    }

    // Function to print tree nodes in InOrder fashion 
    public void inOrder(Node root, ArrayList<Integer> arr)
    {
        if (root != null) {
            inOrder(root.left, arr);

            inOrder(root.right, arr);
            arr.add(root.data);
            //System.out.print(root.data + " ");
        }
    }

    //Recursive function that will search both trees and compare values along the way to check if trees are the same
    public boolean compareTrees(Node root1, Node root2, boolean isSame){ //false booleans will propagate up the recursion
        if (root1 != null && root2!= null){ //As long as both are not null
            if(root1.data==root2.data && isSame) isSame=true; //Check if values are same at the current node
            else isSame=false; //If one value is off, this will propagate up the recursion
            isSame = compareTrees(root1.left, root2.left, isSame);
            isSame = compareTrees(root1.right, root2.right, isSame);

        } else if (root1!= root2) isSame=false; //If root1 and root2 are different in any way, set output false
        return isSame;
    }

    // Driver program to test above function 
    public static void main(String args[])
    {
        Tree t2 = new Tree();
        int arr[] = { 1, 2, 3, 4, 5, 6, 6, 6, 6 };
        t2.root = t2.insertLevelOrder(arr, t2.root, 0);
        ArrayList<Integer> arr2d2 = new ArrayList<>();
        t2.inOrder(t2.root, arr2d2);
        for(Integer i: arr2d2) System.out.print(i+" ");
    }
} 