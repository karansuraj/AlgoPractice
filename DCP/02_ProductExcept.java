import java.util.*;

/*
Given an array of integers, return a new array such that each element at index
i of the new array is the product of all the numbers in the original array except
the one at i.

[1,2,3,4,5] --> [120,60,40,30,24]

[2*3*4*5, 1*3*4*5, 1*2*4*5, 1*2*3*5, 1*2*3*4]

[3,2,1] --> [2,3,6]

[2*1, 3*1, 3*2]

Note: What if you can't use division?

- Brute force method would be for every i, loop through all other elements
  and multiply by them by each other except i

- Another method could be to multiply all elements together and then at every
  i, divide by i and set val to that

- How can we do this differently?

  What if we allocate new space, setting everything to 1
  starting at index 1,
  set runningProduct *= arr[i-1] for all i
  and also set value to newArr[i] *=  runningProduct

  arr    [1    ,2    ,3     ,4    ,5]
  rP:           1   1*2   1*2*3  1*2*3*4
  newArr [1,    1,  1*2,  1*2*3, 1*2*3*4]

  do the same thing but in reverse?
  arr    [1        ,2        ,3         ,4        ,5       ]
  rP:     2*3*4*5   3*4*5     4*5        5
  newArr [2*3*4*5,1*3*4*5  ,1*2*4*5   ,1*2*3*5  ,1*2*3*4 ]

*/

class Main {
  public static void main(String[] args) {
    printArr(productExcept(new int[]{1,2,3,4,5}));
    printArr(productExcept(new int[]{3,2,1}));
  }

  public static void printArr(int[] arr) {
    for(Integer i: arr) System.out.print(i+" ");
    System.out.println();
  }

  public static int[] productExcept(int[] arr) {
    //Brute force
    // int[] newArr = new int[arr.length];
    // int temp;
    // for(int i=0; i<arr.length; i++) {
    //   temp = 1;
    //   for(int j=0; j<arr.length; j++) {
    //     if(i!=j) temp*=arr[j];
    //   }
    //   newArr[i] = temp;
    // }
    // return newArr;

    //Divide method
    //Need to account for case where there is a 0 in the arr, but lets assume not for now
    // int product = 1;
    // for(Integer i: arr) product*=i;
    // //Loop through all elements and divide product by i and set val at i to that val
    // for(int i=0; i<arr.length; i++) {
    //   arr[i] = product/arr[i];
    // }
    // return arr;

    //Double pass method, additional Space
    int[] nArr = new int[arr.length];
    //Populate array with 1s
    Arrays.fill(nArr, 1);
    //Init running product var and do first pass
    int rp = 1;
    for(int i=1; i<arr.length; i++) {
      rp *= arr[i-1];
      nArr[i] *= rp;
    }
    //Reset running product and do second pass
    rp = 1;
    for(int i = arr.length-2; i>=0; i--) {
      rp *= arr[i+1];
      nArr[i] *= rp;
    }

    return nArr;
  }

}
