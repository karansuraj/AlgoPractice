import java.util.*;

/*
Implement an XOR Linked List (a more memory efficient doubly linked list):
- Instead of next/prev for each node, there is a 'both' which is the xor of next and prev node
- Methods
  - add(element) --> adds element to end
  - get(index) --> returns node at index

    4 <--> 5 <--> 6 <--> 7 --> null

*/


class Main {
  public static void main(String[] args) {
    /*
    XOR

    1 0 1 1 0 1
    1 1 1 0 1 1

    0 1 0 1 1 0

    */
    XORLL ll = new XORLL();
    ll.add(2); // 2 --> null
  }
}

class ListNode {
  int val;
  ListNode both;

  public ListNode(int val, ListNode prev) {
    this.val = val;
    this.both = prev;
    if(prev!=null) {
      // prev.both = prev.both this;
    }
  }
}

class XORLL {
  ListNode head, tail;
  int size = 0;

  public void add(int element) {
    //Handle for empty ll
    if(head==null) {
      this.head = new ListNode(element, null);
      this.tail = this.head;
      this.size++;
    }
    //If head is tail, we handle for adding next element and XORing head with null
    // (since next elem will make head and tail distinct in 2 elem ll)
    else if(head == tail) {
      ListNode newElem = new ListNode(element, head);
      this.head.both = newElem;
      this.tail.both = this.head;
      this.size++;
    }
    //If size of linked list is already 2, we append to end and change tail to new elem
    else if(this.size >= 2) {
      ListNode newElem = new ListNode(element, tail);
      // return 0;
    }
  }

  public ListNode get(int index) {

    return null;
  }

}
