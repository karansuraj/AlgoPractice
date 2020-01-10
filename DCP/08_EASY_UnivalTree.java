import java.util.*;

/*

A unival tree is a tree where all nodes under it have the same value.
Given the root to a binary tree, count the number of unival subtrees.
For example, the following tree has 5 unival subtrees:
      0
    /   \
   1     0
       /   \
      1     0
    /  \
   1   1


Approach:
- Leaves are unival subtrees
- At every level, check if left null, right null to see if leaf & use as base case
- Use global counter to count where unival found, including at base case
- Recurse on left and right. If returned values of left & right = curr.val counter++
  and return back the value up. Else return null
*/

class Node {
  int val;
  Node left, right;
  public Node(int val) {this.val = val;}
}

class Main {

  public static void main(String[] args) {
    Node sample = sampleConstruct();
    System.out.println(numUnivalTrees(sample));
  }

  private static Node sampleConstruct() {
    Node root = new Node(0);
    root.left = new Node(1);
    root.right = new Node(0);
    root.right.right = new Node(0);
    root.right.left = new Node(1);
    root.right.left.left = new Node(1);
    root.right.left.right = new Node(1);
    return root;
  }

  private static int counter;

  public static int numUnivalTrees(Node root) {
    counter = 0;
    recurse(root);
    return counter;
  }

  private static Integer recurse(Node root) {
    if(root == null) return null;
    
    if(root.left == null && root.right == null) {
      counter++;
      return root.val;
    }
    Integer leftRec = recurse(root.left);
    Integer rightRec = recurse(root.right);

    if(leftRec == null && rightRec != null && rightRec == root.val) {
      counter++;
      return root.val;
    } else if(leftRec !=null && rightRec == null && leftRec == root.val) {
      counter++;
      return root.val;
    } else if(leftRec != null && rightRec != null && leftRec == root.val && rightRec == root.val) {
      counter++;
      return root.val;
    } else {
      return null;
    }

  }
}
