public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
    void appendToTail(int x){
        ListNode out = new ListNode(x);
        ListNode curr = this;
        while(curr.next!=null){
            curr = curr.next;
        }
        curr.next = out;
    }
}


