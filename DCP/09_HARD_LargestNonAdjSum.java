import java.util.*;

/*
Given a list of integers, write a function that returns the largest sum of non-adjacent numbers.
Numbers can be 0 or negative.

[2,4,6,1,5] --> 13   (2+6+5)
[5,1,1,5] --> 10  (5+5)

Follow-up: O(N) time, O(1) space

Approach:
- Start with small case [2,4]
  Try all possible combinations and choose largest of sums
  2 = 2
  4 = 4
  return Math.max(2,4)

  Larger example [2,4,6]
  2
  2+6 = 8
  4 = 4
  6
  return Math.max(2,8,4,6)

  [2,4,6,1]
  2
  2+6 = 8
  2+1 = 3
  4
  4+1 = 5
  6
  1
  return Math.max(2,8,3,4,6,1)

  [2,4,6,1,5]
  2
  2+6+5 = 13
  2+1 = 3
  4
  4+1 = 5
  6
  6+5 = 11
  1
  5

- Use recursion with global counter for max
- Keep sum var = value at index in arr passed into recursion --> recurse(int[] arr, int currInd)
  sum = arr[currInd]
- Loop over array from i = currInd + 2; i<arr.length
  sum += recurse(arr, i)
- After loop, Math.max global counter with sum

Could use dynamic programming with Table corresponding to array
- Each index in array represents max sum up to that index in original array
- Do lookbacks from 2 back, since that's the max possible at that point
- T[0] = arr[0], T[1] = arr[1], T[2] = Math.max(arr[2], arr[2]+T[2-2])
*/

class Main {
  public static void main(String[] args) {
    int[] arr = {2,4,6,1,5,8,7};
    int[] arr2 = {5,1,1,5};
    int[] arr3 = {2,8,5,1,5,8,7};
    // System.out.println()
    System.out.println(largestNonAdjSumRec(arr));
    // System.out.println(largestNonAdjSum(arr2));
  }

  private static int maxSum;
  private static ArrayList<Integer> path;

  private static void printArr(int[] arr) {
    for(Integer i: arr) System.out.print(i+" ");
    System.out.println();
  }

  public static int largestNonAdjSum(int[] arr) {
    /* NOT FINISHED */
    if(arr.length == 0) return 0;
    if(arr.length == 1) return arr[0];
    if(arr.length == 2) return Math.max(arr[0], arr[1]);
    int[] T = new int[arr.length];
    T[0] = arr[0];
    T[1] = arr[1];

    for(int i=2; i<arr.length; i++) {
      T[i] = Math.max(arr[i], arr[i] + T[i-2]);
    }
    printArr(T);
    return T[arr.length-1];

  }
  private static HashMap<String, Integer> cache;

  public static int largestNonAdjSumRec(int[] arr) {
    maxSum = 0;
    path = new ArrayList<Integer>();
    cache = new HashMap<>();
    for(int i=arr.length-1; i>=0; i-- ) {
      recurse(arr, i, arr[i]);
    }
    System.out.println(cache);
    return maxSum;
  }



  public static int recurse(int[] arr, int currInd, int sum) {
    String cacheKey = String.valueOf(currInd) + "," + String.valueOf(sum);
    if(cache.containsKey(cacheKey)) {
      System.out.println(cacheKey);
      return cache.get(cacheKey);
    }
    path.add(arr[currInd]);
    // System.out.println(path);
    for(int i=currInd-2; i>=0; i--) {
      recurse(arr, i, sum+arr[i]);
    }
    if(sum > maxSum) {
      System.out.println(path);
      maxSum = sum;
    }

    // System.out.println(sum);
    path.remove(path.size()-1);
    cache.put(cacheKey, sum);
    return sum;
  }
}
