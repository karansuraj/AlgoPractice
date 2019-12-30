import java.util.*;


class Main {
  public static void main(String[] args) {
    printArr(twoSum(new int[]{2,15,7,11}, 9));
  }

  public static void printArr(int[] arr) {
    for(Integer i: arr) System.out.print(i+" ");
    System.out.println();
  }

  private static HashMap<Integer, Integer> indMap;

  public static int[] twoSum(int[] arr, int target) {
    /* HASHMAP COMPLEMENT APPROACH; Time: O(N), Space O(N)
      Use Hashmap to store complement of each element when subtracting that element from target
      Key will be the complement, and the value will be the index where the complement was
      calculated. If we find the complement later in the array, we can return
      the value associated with the key in the map and also the index where we found
      the complement
    **/
    // HashMap<Integer, Integer> compMap = new HashMap<>();
    // for(int ind=0; ind<arr.length; ind++) {
    //   if(compMap.containsKey(arr[ind])) {
    //     return new int[]{compMap.get(arr[ind]), ind};
    //   }
    //   int complement = target - arr[ind];
    //   compMap.put(complement, ind);
    // }
    //
    // return new int[]{-1,-1};

    /*SORT + 2 POINTER APPROACH; Time: O(NLOGN), Space: O(N)
      ***DOES NOT WORK IF YOU DON'T KEEP TRACK OF INDS***
      Could sort array, then use 2 pointer approach
      left pointer starts at ind 0, right starts at end of array
      sum values at both pointers. if greater than target, shift right down
      if less than target, shift left up. do this while left < right pointer
    **/

    indMap = new HashMap<>();
    for(int i=0; i<arr.length; i++) indMap.put(i,i);
    heapSort(arr);

    int left = 0;
    int right = arr.length-1;
    int sum;
    while(left < right) {
      sum = arr[left] + arr[right];
      if(sum > target) right--;
      else if(sum < target) left++;
      else return new int[]{indMap.get(left), indMap.get(right)};
    }
    return new int[]{-1,-1};

  }

  public static void heapSort(int[] arr) {
    for(int i=arr.length-1; i>=0; i--) bubbleDown(arr, i, arr.length);
    for(int i=arr.length-1; i>0; i--) {swap(arr,0,i); bubbleDown(arr,0,i);}
  }

  public static void bubbleDown(int[] arr, int p, int size){
    int c = getChild(arr, p, size);
    while(c < size && arr[c] > arr[p]) {
      swap(arr, c, p);
      p = c;
      c = getChild(arr, p, size);
    }
  }

  public static int getChild(int[] arr, int p, int size){
    int c1 = 2*p+1, c2 = 2*p+2;
    if(c2 >= size || c1 >= size) return c1;
    return arr[c1] > arr[c2] ? c1 : c2;
  }

  public static void swap(int[] arr, int a, int b) {
    int temp = arr[a];
    arr[a] = arr[b];
    arr[b] = temp;

    temp = indMap.get(a);
    indMap.put(a, indMap.get(b));
    indMap.put(b, temp);
  }

  /*

  ::0 <--> 2::
  0: 0  --> 2
  2: 2  --> 7
  [2,15,7,11]
      |
      |
      v
  0: 2  --> 7
  2: 0  --> 2
  [7,15,2,11]


  ::0 <--> 3::
  0: 2  --> 7
  3: 3  --> 11
  [7,15,2,11]
      |
      |
      v
  0: 3  --> 11
  3: 2  --> 7
  [11,15,2,7]
  */
}
