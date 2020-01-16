import java.util.*;

/*
Given an integer k and a string s, find the length of the longest substring that
contains at most k distinct characters.

e.g. s = "abcba" & k=2 --> "bcb"

Could we use sliding window? Left index starts at beginning, right index increments.
We keep a set or map that keeps a count of letters. If we get to a point that the size
of the hashmap is greater than k. We check the length of the substring between
left and right index (minus 1) pointer and see if that substring is the longest
substring yet, and capture if so. Then we shift the left pointer, decrementing
the letter counts found, and also removing a key once a count gets to 0. Once
a count gets to 0, we will now have a hashmap of size k. Now we start over
shifting the right pointer while size of hashmap <= k and repeat until r == string end index
Do a while loop around right index being less than end index. Will handle the rest of the stuff
in the loop. After the loop, in case the substring between l & r at the end is the longest,
do another substring check

abcba
k=2

0  1  2  3  4
   l
a  b  c  b  a
            r

Map:
  size: 3, b: 2, c:1, a: 1

Result: bcb


What about if there are 2 possible results? Return first or both? Will return first
vvqrr
k=2 --> vvq

vvqrr
k=3 --> vvqrr

vvqrr
k=0 --> ""


Steps:
1. Init l=0, r=0, maxSub = "", counts map <Character, Integer>
2. while r < str.length()
  a. If map doesn't contain char, add char with count 0
  b. Get char count from map and increment by 1
  c. while map size > k
    - if substring(l, r) (r exclusive) > maxSub.length(), maxSub = substring(l, r) (r exclusive)
    - Decrement count of char at l in str by 1
    - if count for char at l is 0, remove char from map
    - Increment l
  d. increment r
3. if substring(l, r) (r exclusive) > maxSub.length(), maxSub = substring(l, r) (r exclusive)
4. return maxSub

*/

class Main {
  public static void main(String[] args) {
    // TODO: Test code on leetcode!
    System.out.println(maxSubDistinct("abcba", 2));
    System.out.println(maxSubDistinct("vvqrr", 2));
    System.out.println(maxSubDistinct("vvqrr", 3));
    System.out.println(maxSubDistinct("vvqrr", 1));
    System.out.println(maxSubDistinct("vvqrr", 0));
    System.out.println(maxSubDistinct("vvqqqrr", 1));
  }

  public static String maxSubDistinct(String str, int k) {
    int l=0, r=0;
    String maxSub = "", currSub = "";
    char currChar;
    HashMap<Character, Integer> counts = new HashMap<>();
    while(r < str.length()) {
      currChar = str.charAt(r);
      if(!counts.containsKey(currChar)) {
        counts.put(currChar, 0);
      }
      counts.put(currChar, counts.get(currChar)+1);

      while(counts.size() > k) {
        currSub = str.substring(l,r);
        if(currSub.length() > maxSub.length()) {
          maxSub = currSub;
        }
        currChar = str.charAt(l);
        counts.put(currChar, counts.get(currChar)-1);
        if(counts.get(currChar) == 0) {
          counts.remove(currChar);
        }
        l++;
      }
      r++;
    }
    currSub = str.substring(l,r);
    if(currSub.length() > maxSub.length()) {
      maxSub = currSub;
    }
    return maxSub;
  }
}
