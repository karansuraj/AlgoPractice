import java.util.*;

/*
Problem asked by Amazon

There exists a staircase with N steps, and you can climb up either 1 or 2 Steps
at a time. Given N, write a function that returns the number of unique ways
you can climb the staircase. The order of the steps matters.

e.g. if N = 4 --> 5:

- 1,1,1,1
- 2,1,1
- 1,2,1
- 1,1,2
- 2,2

What if instead of being able to climb 1 or 2 steps at a time you could climb
any number from a set of positive integers X? For example if X = {1,3,5}, you
could climb 1,3,5, steps at a time.

*/
class Main {
  private static HashMap<Integer, Integer> cache;
  public static void main(String[] args) {
    cache = new HashMap<>();
    System.out.println(uniqueWaysTab(4, new int[]{1,2}));
    // System.out.println(cache);
  }

  public static int uniqueWays(int N, int[] X) {
    //Could recurse in reverse for both N - 2 and N -1 cases
    //When we hit N == 0 we can return 1
    //If we hit N < 0 then we return 0
    //Could use cache to maximize potential
    if(cache.containsKey(N)) return cache.get(N);
    if(N == 0) return 1;
    if(N < 0) return 0;
    int result = 0;
    for(Integer x: X) {
      result += uniqueWays(N-x, X);
    }
    cache.put(N, result);
    return result;
  }

  public static int uniqueWaysTab(int N, int[] X) {
    //Could build up to it for all n = 0 to N, add value at N-x for all X
    //Store vals in table
    int[] T = new int[N+1];
    T[0] = 1;
    for(int n = 1; n <= N; n++) {

      for(Integer x: X) {
        if(x <= n) {
          T[n] += T[n-x];
        }
      }
    }
    return T[N];
  }
}
