import java.util.*;

public class RandomListNode {
    int label;
    RandomListNode next, random;
    public RandomListNode(int x) {this.label = x;}

    public static RandomListNode copyRandomList(RandomListNode head){
        Map<RandomListNode, RandomListNode> visited = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode start = new RandomListNode(head.label);
        visited.put(head,start);
        RandomListNode res = start;
        RandomListNode beg = head;
        while(head.next!=null && !visited.containsKey(head.next)){
            start.next = new RandomListNode(head.next.label); //Add next node
            start = start.next;
            head = head.next;
            visited.put(head, start);
        }
        start = res;
        head = beg;
        while(head != null){
            //rando = ;
            start.random = visited.get(head.random);
            start = start.next;
            head = head.next;
        }
        //RandomListNode ho  = visited.get(new RandomListNode(1));


        return res;

    }

    public static void main(String args[]){
        RandomListNode head, n1, n2, n3, n4;
        head = new RandomListNode(1);
        n1 = new RandomListNode(2);
        n2 = new RandomListNode(3);
        n3 = new RandomListNode(4);
        n4 = new RandomListNode(5);

        head.next = n1;
        head.random = null;
        n1.next = n2;
        n1.random = n4;
        n2.next = n3;
        n2.random = head;
        n3.next = n4;
        n3.random = null;
        n4.next = null;
        n4.random = n3;

        //copyRandomList(head);

        RandomListNode head2 = new RandomListNode(1);
        RandomListNode m1 = new RandomListNode(2);
        head2.next = m1;
        head2.random = m1;
        m1.random = m1;
        copyRandomList(head2);

    }
}
