import java.util.*;

//import java.util.*;
public class Main {
    public static void main(String[] args){

        //testLongPalindromeSubstring();
        //testReverseInt();
        //System.out.println(Solution.romanToInt("III"));
        //testFindOfSortedArrays();
        //testMergeSortList();
        //testIntegerToWords();
        testTreeNode();
    }

    private static void testTreeNode(){
        Tree bin = new Tree();
        //int[] arr = new int[]{1,2,3,4,5,6,7,8,9};
        bin.root = bin.insertLevelOrder(new int[]{}, bin.root, 0);

        Tree bin2 = new Tree();
        bin2.root = bin2.insertLevelOrder(new int[]{}, bin2.root, 0);
        //ArrayList<Integer> arr2d2 = new ArrayList<>();
        //bin.inOrder(bin.root, arr2d2);
        //for(Integer i: arr2d2) System.out.print(i+" ");
        System.out.println(bin.compareTrees(bin.root, bin2.root, true));
    }

    private static void testIntegerToWords(){ //Test conversion of Integer to Words
        System.out.println(IntegerToWords.IntegerToWords(1011));
        System.out.println(IntegerToWords.IntegerToWords(1234567891));
        System.out.println(IntegerToWords.IntegerToWords(123));
    }
    private static void testLRU(){
        LRUCache tst = new LRUCache(10);
        tst.put(10,13);
        tst.put(3,17);
        tst.put(6,11);
        tst.put(10,15);

        //System.out.println(tst.put(10,13));
    }

    private static void testMergeSortList(){
        ListNode a = new ListNode(1);
        a.appendToTail(2);
        a.appendToTail(4);
        ListNode b = new ListNode(1);
        b.appendToTail(3);
        b.appendToTail(4);

        Solution.mergeTwoLists(a,b);
    }

    private static void testFindOfSortedArrays(){
        System.out.println(Solution.findMedianSortedArrays(new int[]{1,2}, new int[]{3,4})); //2.5
        System.out.println(Solution.findMedianSortedArrays(new int[]{1,2}, new int[]{1,2})); //1.5
        System.out.println(Solution.findMedianSortedArrays(new int[]{1,3}, new int[]{})); //2.0
        System.out.println(Solution.findMedianSortedArrays(new int[]{1,3}, new int[]{2})); //2.0
        System.out.println(Solution.findMedianSortedArrays(new int[]{1,2,3}, new int[]{4,5})); //3.0
        System.out.println(Solution.findMedianSortedArrays(new int[]{3,4}, new int[]{1,2,3})); //3.0
        System.out.println(Solution.findMedianSortedArrays(new int[]{2,2,2}, new int[]{2,2,2,2})); //2.0
        System.out.println(Solution.findMedianSortedArrays(new int[]{1}, new int[]{2,3,4})); //2.5
    }
    private static void testLongPalindromeSubstring(){
        Solution soln = new Solution();
        System.out.println(soln.longestPalindrome("ccc"));
    }

    private static void testReverseInt(){
        Solution soln = new Solution();
        System.out.println(soln.reverse(-123));
    }
}
