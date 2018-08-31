//import java.util.*;
public class Main {
    public static void main(String[] args){

        //testLongPalindromeSubstring();
        //testReverseInt();
        System.out.println(Solution.romanToInt("III"));
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
