//import java.util.*;
public class Main {
    public static void main(String[] args){

        //testLongPalindromeSubstring();
        //testReverseInt();
        //System.out.println(Solution.romanToInt("III"));
        System.out.println(Solution.findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}));
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
