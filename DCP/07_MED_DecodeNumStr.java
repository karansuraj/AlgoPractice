import java.util.*;


class Main {
  /*
  Given the mapping a=1, b=2,..,z=26 and encoded message, count num ways to decode message
  a  b  c  d  e  f  g  h  i  j  k  l  m  n  o  p  q  r  s  t  u  v  w  x  y  z
  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26

  '111' --> 3 because 'aaa', 'ka', 'ak'

  All messages are decodable

  input: Mapping (could be function or hashmap), String codedMsg
  output: int numWays

  - One type of decoding is only for mappings 1-9 chars (a,b,c,d,e,f,g,h,j)
  - Another is 1 & 2 digit mappings combined
  - Another is only 2 digit mappings, which could only be possible with even-sized strings

    Potential dynamic programming where we look at a smaller example and build on it
    - --> (1) way to get to decode a message with no digits
    1 --> (1) way, add 1 digit to no digits (which had 1 way)
        a
    1 1 --> (2) ways, adding 1 digit onto 1 digit (which had 1 way), adding 2 digits from no digits (if mappable) (which had 1 way)
        a a     k
    1 1 1 --> (3) add 1 digit onto 2 digits (which had 2 ways), adding 2 digits onto 1 digit (which had 1 way)
        a a a, k a     a k

    e.g. 2: '1223' --> 'abbc', 'lbc', 'avc', 'lw', 'abw'

    - --> (1) way to decode no digits
    1 --> (1) way: add 1/a to no digits (1)
        a
    1 2 --> (2) ways: add 2/b to 1 digit (1), add 12/l to no digits (1)
        a b     l
    1 2 2 --> (3) ways: add 2/b to 2 digits (2), add 22/v to 1 digit (1)
        a b b, l b      a v
    1 2 2 3 --> (5) ways: add 3/c to 3 digits(3), add 23/w to 2 digits (2)
        a b b c, l b c, a v c     a b w, l w

    e.g. 3: '1233' --> 'abcc', 'lcc', 'awc'
    - --> (1)
    1 --> (1) add 1/a to no digits (1)
      a
    1 2 --> (2) add 2/b to 1 digit (1), add 12/l to no digits (1)
      a b     l
    1 2 3 --> (3) add 3/c to 2 digits (2), add w/23 to 1 digit (1)
      a b c, l c     a w
    1 2 3 3 --> (3) add 3/c to 3 digits (3)
      a b c c, l c c, a w c

    Use array of size str.length+1
    for each index
      look back 1 in array and sum that value to current
      look back 2
        if str.substring(i-1, i+1) (last index to this index inclusive) is a valid mapping
          sum lookback 2 in array to current



     0 1 3 4 5 6
    [1 1 1 2 2 3]

    [0 0 0 0 0 0]


  */
  public static void main(String[] args) {
    String str1, str2, str3, str4;
    str1 = "111";
    str2 = "123";
    str3 = "1223";
    str4 = "1233";
    System.out.println(decodeStr(str1));
    System.out.println();
    System.out.println(decodeStr(str2));
    System.out.println();
    System.out.println(decodeStr(str3));
    System.out.println();
    System.out.println(decodeStr(str4));
    System.out.println();
    System.out.println(decodeStr("10011"));
    System.out.println(decodeStr("100"));
    System.out.println(decodeStr("1011"));
    System.out.println(decodeStr("10"));
    System.out.println(decodeStr("101"));
    System.out.println(decodeStr("01"));
  }

  public static int decodeStr(String str) {
    if(str.length()>0 && str.charAt(0) == '0') return 0;
    int[] T = new int[str.length()+1];
    //Base case, 1 way to decode empty string
    T[0] = 1;
    //Loop through array, doing lookbacks as necessary
    for(int i=1; i<T.length; i++) {
      //Lookback 1
      // System.out.println(str.charAt(i-1));
      if(str.charAt(i-1) !='0') T[i] += T[i-1];
      //Lookback 2
      if(i>1) {
        // if(str.charAt(i-1) == '0' &&
        //   !(str.charAt(i-2) == '2' || str.charAt(i-2) == '1')) return 0;
        //If last 2 chars is a valid mapping, add it
        String sub = str.substring(i-2, i);
        if(validMap(sub)) {
          T[i] += T[i-2];
        }
      }
    }
    return T[str.length()];
  }

  private static boolean validMap(String strval) {
    // System.out.println(strval);
    if(strval.length()>0 && strval.charAt(0)=='0') return false;
    int val = Integer.parseInt(strval);
    return val >= 1 && val <= 26;
  }
}
