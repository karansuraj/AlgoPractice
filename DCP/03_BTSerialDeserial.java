import java.util.*;
/*
Given the root to a binary tree, implement serialize(root), which serializes
the tree into a string, and deserialize(s), which deserializes string back to tree

class Node:
  def__init__(self, val, left=None, right=None):
    self.val = val
    self.left = left
    self.right = right

  node = Node('root', Node('left', Node('left.left')), Node('right'))
  assert deserialize(serialize(node)).left.left.val = 'left.left'

*/

class Node {
  String val;
  Node left, right;

  public Node(String val) {
    this.val = val;
    this.left = null;
    this.right = null;
  }
}

class Main {
  public static void main(String[] args) {
    Node node = new Node("root");
    node.left = new Node("left");
    node.left.left = new Node("left.left");
    node.right = new Node("right");
    System.out.println(serialize(node));
    System.out.println(serialize(deserialize(serialize(node))));
  }

  public static String serialize(Node root) {
      if(root == null) return "";
      StringBuilder sb = new StringBuilder();
      //Do a BFS, appending to sb, separating nodes by spaces. Put 'null' for nulls
      //Need to keep a string queue to make sure to capture nulls on right levels
      //Handle nulls like leaf nodes. strQ will have more vals than nodeQ
      //So must remove from strQ, add to sb, and check if null.
      //If not null, then do normal bfs ops.
      //
      int serialCount = 0;
      Queue<Node> nodeQ = new LinkedList<>();
      Queue<String> strQ = new LinkedList<>();
      nodeQ.add(root);
      strQ.add(root.val);
      Node curr;
      String currStr;
      while(nodeQ.size()>0) {
        //Pop from strQ
        currStr = strQ.remove();
        //Add currStr to sb with space
        sb.append(currStr+" ");
        serialCount++;

        //Check if !currStr.equals("null"")
        if(!currStr.equals("null")) {
          //Pop from nodeQ
          curr = nodeQ.remove();
          //if curr node.left is null, add "null" to strQ, else add node val
          if(curr.left == null) {
            strQ.add("null");
          } else {
            nodeQ.add(curr.left);
            strQ.add(curr.left.val);
          }

          //do same for curr node.right
          if(curr.right == null) {
            strQ.add("null");
          } else {
            nodeQ.add(curr.right);
            strQ.add(curr.right.val);
          }
      }
    }
    if((serialCount-1)%2 != 0) sb.append("null");
    return sb.toString();
  }

  public static Node deserialize(String tree) {
    if(tree.length() == 0) return null;
    //Parse string with split by space
    String[] nodes = tree.split(" ");
    //Reverse BFS with Q
    Queue<Node> nodeQ = new LinkedList<>();
    //Set root to new node of first val in nodes list
    Node root = new Node(nodes[0]);
    //Add new root node to nodeQ
    nodeQ.add(root);
    Node curr,left,right;
    //Loop through nodes from i=1 to nodes.length-1 with i+2
    for(int i=1; i<nodes.length-1; i+=2){
      //Pop from nodeQ to curr
      curr = nodeQ.remove();
      //Look in pairs from nodes list
      //if first not null
      if(!nodes[i].equals("null")) {
        //Create new node and add as left of curr Node
        left = new Node(nodes[i]);
        curr.left = left;
        //Add new node to queue
        nodeQ.add(left);
      }
      //Do same with second
      if(!nodes[i+1].equals("null")) {
        //Create new node and add as left of curr Node
        right = new Node(nodes[i+1]);
        curr.right = right;
        //Add new node to queue
        nodeQ.add(right);
      }
    }

    return root;
  }
}
