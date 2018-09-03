//import java.util.*;
public class Main {
    public static void main(String[] args){

        //testLongPalindromeSubstring();
        //testReverseInt();
        //System.out.println(Solution.romanToInt("III"));
        //testFindOfSortedArrays();
        testMergeSortList();
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
