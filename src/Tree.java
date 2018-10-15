import java.util.*;

// Java program to construct binary tree from
// given array in level order fashion 
// Node class, insertLevelOrder & inOrder functions taken from geeksforgeeks to help solve LeetCode Tree problems
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

    /*Code below is by Karan Suraj*/
    //Recursive function that will search both trees and compare values along the way to check if trees are the same
    //Call this function initially with "isSame" set to true
    public boolean compareTrees(Node root1, Node root2, boolean isSame){ //false booleans will propagate up the recursion
        if (root1 != null && root2!= null){ //As long as both are not null
            if(root1.data==root2.data && isSame) isSame=true; //Check if values are same at the current node
            else isSame=false; //If one value is off, this will propagate up the recursion
            isSame = compareTrees(root1.left, root2.left, isSame);
            isSame = compareTrees(root1.right, root2.right, isSame);

        } else if (root1!= root2) isSame=false; //If root1 and root2 are different in any way, set output false
        return isSame;
    }

    //Recursive function that'll dive into a tree and find the maximum level depth
    public int maxDepthDFS(Node root, int count){
        if(root!=null){
            count++; //Increment count for current node
            count = Math.max(maxDepthDFS(root.left, count),maxDepthDFS(root.right, count)); //Choose max count from left or right side
        }
        return count;

    }
    public boolean isBalanced(Node root) {
        return heightBal(root, 0)>-1;
    }
    public int heightBal(Node root, int count){
        int leftCnt, rightCnt; //Variables to get height count from left & right subtrees
        if(count==-1) return count;
        if(root!=null){
            count++;
            leftCnt = heightBal(root.left, count);
            rightCnt = heightBal(root.right, count);
            if((leftCnt-rightCnt) > 1) count=-1; //Checks if height difference between either subtree is greater than 1
            else count = Math.max(leftCnt, rightCnt); //If not different, just increment size of
        }
        return count;
    }

    // Driver program to test above function
    public static void main(String args[])
    {
        Tree bin1 = new Tree();
        bin1.root = new Tree.Node(1);
        bin1.root.right = new Tree.Node(2);
        bin1.root.right.right = new Tree.Node(3);
        System.out.println(bin1.isBalanced(bin1.root));
        /*Tree t2 = new Tree();
        int arr[] = { 1, 2, 3, 4, 5, 6, 6, 6, 6 };
        t2.root = t2.insertLevelOrder(arr, t2.root, 0);
        ArrayList<Integer> arr2d2 = new ArrayList<>();
        t2.inOrder(t2.root, arr2d2);
        for(Integer i: arr2d2) System.out.print(i+" ");*/
    }
} 