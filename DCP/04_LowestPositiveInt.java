import java.util.*;


class Main {

  public static void main(String[] args) {
    // int[] arr = {0,1,4,2,3};
    // int[] arr = {2,0,1};
    // int[] arr = {7,8,9,11,12};
    // int[] arr = {1};
    // int[] arr = {4,3,-1,2};
    // int[] arr = {-1,4,2,1,9,10};
    // int[] arr = {1,2,2,1,3,1,0,4,0};
    // int[] arr = {2};
    // int[] arr = {2147483647,2147483646,2147483645,3,2,1,-1,0,-2147483648};
    int[] arr = {2147483647,100000,1,3,2,4,5,6,7,100001};

    /*


    */
    // printArr(arr);
    System.out.println(lowestPositive(arr));
    // printArr(arr);

  }

  public static int lowestPositive(int[] arr) {
    // Move 0s, negatives, and out of bounds to end
    int endInd = arr.length-1;
    //Decrement endInd while not a positive in bounds integer
    while(endInd>=0 && (arr[endInd] <=0 || arr[endInd] > arr.length)) endInd--;
    for(int i=0; i<endInd && endInd>=0; i++) {
      if(arr[i] <= 0 || arr[i] > arr.length) {
        swap(arr, endInd, i);
        endInd--;
      }
      while(endInd>=0 && (arr[endInd] <=0 || arr[endInd] > arr.length)) endInd--;
    }
    printArr(arr);

    // Just move all numbers to right place and find first index where number wrong
    for(int i=0; i<=endInd; i++) {
      int swapInd = arr[i]-1;
      if(swapInd>=0 && swapInd <= endInd && arr[swapInd] != arr[i]) {
        swap(arr, i, swapInd);
      }
    }
    printArr(arr);

    for(int j=0; j<=endInd;j++) {
      if(arr[j] != j+1) return j+1;
    }

    return endInd + 2;


    // //Move 0s and negatives to front
    // int startInd = 0;
    // for(int i=0; i<arr.length; i++) {
    //   if(arr[i] <= 0) {
    //     swap(arr, startInd, i);
    //     startInd++;
    //   }
    // }
    //
    // //Deal with rest of array
    // int posSize = arr.length-startInd;
    // for(int posInd=startInd; posInd<arr.length; posInd++) {
    //   int val = Math.abs(arr[posInd]);
    //   int actualInd = val-1+startInd;
    //   if(actualInd >= 0 && actualInd < arr.length && arr[actualInd] > 0) {
    //     arr[actualInd] *= -1;
    //   }
    // }
    //
    // for(int i=startInd; i<arr.length; i++) {
    //   if(arr[i] >= 0) {
    //     return i+1-startInd;
    //   }
    // }
    // return posSize+1;
  }

  public static void swap(int[] arr, int ind1, int ind2) {
    int temp = arr[ind1];
    arr[ind1] = arr[ind2];
    arr[ind2] = temp;
  }

  public static int getMinChild(int[] arr, int parent) {
    int child1 = 2*parent+1;
    int child2 = 2*parent+2;
    if(child1 >= arr.length || child2 >= arr.length) return child1;
    return arr[child1] < arr[child2] ? child1 : child2;
  }

  public static void minHeapify(int[] arr) {
    int child, temp;
    for(int parent = arr.length-1; parent>=0; parent--){
      child = getMinChild(arr, parent);
      while(child < arr.length && arr[child] < arr[parent]){
        //Swap
        temp = arr[child];
        arr[child] = arr[parent];
        arr[parent] = temp;
        //Shift parent ind to child in
        parent = child;
        child = getMinChild(arr, parent);
      }
    }
  }

  public static void printArr(int[] arr) {
    for(Integer i: arr) System.out.print(i+" ");
    System.out.println();
  }

  public static int lowestPositiveBad(int[] arr) {
    if(arr.length==0) return 1;
    int counter = 1;
    if(arr.length < 3) {
      Arrays.sort(arr);
      for(Integer i: arr) {
        if(counter==i) counter++;
      }
      return counter;
    }
    minHeapify(arr);
    printArr(arr);
    /*
                      -1
                  /         \
                1            2
              /   \       /
            4     9      10

    */
    //Check if array length is less than 3
    int max = Integer.MAX_VALUE;
    int parent = 0;
    int child1val = 2*parent+1 < arr.length ? arr[2*parent+1] : max;
    int child2val = 2*parent+2 < arr.length ? arr[2*parent+2] : max;
    int minChild = getMinChild(arr, 0);
    while(arr[0] != max || arr[1] != max || arr[2] != max) {

      while(!isLeaf(arr,parent) && (child1val != max || child2val != max)) {
        if(arr[parent] == counter) counter++;
        arr[parent] = max;
        minChild = getMinChild(arr, parent);
        parent = minChild;
        printArr(arr);
      }
      if(arr[parent] == counter) counter++;
      arr[parent] = max;
      parent = (parent-1)/2;
      child1val = 2*parent+1 < arr.length ? arr[2*parent+1] : max;
      child2val = 2*parent+2 < arr.length ? arr[2*parent+2] : max;
      minChild = getMinChild(arr, parent);
      printArr(arr);
    }
    return counter;
  }

  public static boolean isLeaf(int[] arr, int index) {
    return 2*index+1 >= arr.length;
  }


}
