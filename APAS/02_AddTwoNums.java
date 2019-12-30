import java.util.*;

class Main {
  public static void main(String[] args) {
    LinkedList<Integer> num1 = iToLLFwd(443), num2 = iToLLFwd(564);
    // System.out.println(sumNumsForwardLL(num1, num2));

    num1 = iToLLRev(43);
    num2 = iToLLRev(964);

    System.out.println(sumNumsRevLL(num1, num2));

  }

  public static LinkedList<Integer> iToLLFwd(int num){
    LinkedList<Integer> ll = new LinkedList<>();
    while(num > 0){
      ll.addFirst(num%10);
      num /= 10;
    }
    return ll;
  }

  public static LinkedList<Integer> iToLLRev(int num){
    LinkedList<Integer> ll = new LinkedList<>();
    while(num > 0){
      ll.addLast(num%10);
      num /= 10;
    }
    return ll;
  }

  public static LinkedList<Integer> sumNumsRevLL(LinkedList<Integer> num1, LinkedList<Integer> num2) {
    //Iterate through both linked lists

    //Need to determine longer of 2 linked lists
    int n1Size = num1.size(), n2Size = num2.size();
    LinkedList<Integer> largeLL, smallLL;
    if(n1Size > n2Size) {
      largeLL = num1;
      smallLL = num2;
    } else {
      largeLL = num2;
      smallLL = num1;
    }

    //Init pointer indices for both lists and remainder variable set to 0
    int sInd = 0, lInd = 0, rem = 0, sum;

    //while the indices for both lists is  less than the size of those lists
    while(sInd < smallLL.size()) {
      //Sum values at pointer indices AND remainder
      sum = smallLL.get(sInd) + largeLL.get(lInd) + rem;
      //Set value at larger linked list index to sum mod 10
      largeLL.set(lInd, sum%10);
      //Set remainder to sum divided by 10 to propagate any remainders
      rem = sum/10;
      sInd++;
      lInd++;
    }
    //while remainder > 0 && loop until end of larger linked list
    while(lInd < largeLL.size() && rem > 0) {
      //sum value at larger ll pointer and remainder
      sum = largeLL.get(lInd) + rem;
      //Set value at larger linked list index to sum mod 10
      largeLL.set(lInd, sum%10);

      //set remainder to sum divided by 10 to propagate remainder
      rem = sum/10;
      lInd++;
    }

    //if remainder > 0 //Append to end of larger ll
    if(rem > 0) largeLL.addLast(rem);

    return largeLL;
  }


  public static int diff, size1, size2;
  public static LinkedList<Integer> finalLL;

  public static LinkedList<Integer> sumNumsForwardLL(LinkedList<Integer> num1, LinkedList<Integer> num2) {
    /*Handle basic situation where nums are same length and don't carry up a digit
      243 + 554 = 797

      What about when one number is longer than the other?
      43 + 244 = 287
         j
      4, 3
            i
      2, 4, 4

      What about when remainders need to be carried up?
      243 + 564 = 807
      43 + 264 = 307

      What about when remainders add on another digit at the top
      43 + 964 = 1007
      443 + 564 = 1007
    */

    //Easy solution is to dump both linked lists into arrays, sum digit by digit

    //First determine length of longer list and associate longer list with length
    size1 = listSize(num1);
    size2 = listSize(num2);
    diff = size1 - size2;


    if(size1 > size2) finalLL = num1;
    else finalLL = num2;

    //Rec function
    int remainder = recAdd(num1, num2, 0, 0);
    if(remainder > 0) {
      finalLL.addFirst(remainder);
    }
    return finalLL;
  }

  public static int listSize(LinkedList<Integer> ll) {
    int size = 0;
    while(size < ll.size()) {
      size++;
    }
    return size;
  }



  public static int recAdd(LinkedList<Integer> num1, LinkedList<Integer> num2, int i1, int i2){
    //Recursively drill down to the end of both linked lists
    // so we can start summing at the end there and carry any remainders up
    int sum = 0;
    if(i1 != num1.size()-1 && i2 != num2.size()-1) {
      //If the linked lists differ in size, we need to iterate until we get
      // to the end of both lists without getting out of bounds errors
      // this involves using a global variable using the size difference
      // between both linked lists
      if(diff < 0) {
        diff++;
        sum = recAdd(num1, num2, i1, i2+1);
      } else if(diff > 0) {
        diff--;
        sum = recAdd(num1, num2, i1+1, i2);
      } else {
        sum = recAdd(num1, num2, i1+1, i2+1);
      }
    }

    //Add values at this point in both lists

    //Different rules for size differences between linked lists
    if(size1 != size2) {
      //If linked lists are different sizes, the indices we're adding MUST
      // be different. Otherwise we are only propagating the remainder to
      // to the longer of the 2 linked lists
      if(i1 != i2) {
        sum += num1.get(i1) + num2.get(i2);
      } else{
        if(size1 > size2) sum += num1.get(i1);
        else sum += num2.get(i2);
      }
      //Set value appropriately at index for longer linked list
      if(size1 > size2) num1.set(i1, sum%10);
      else num2.set(i2, sum%10);
    } else {
      //Both linked lists are the same length, so we just get sum from
      // digits of both linked lists and get the mod 10 remainder of the sum
      // and set it arbitrariliy for one of the linked lists at the index
      sum += num1.get(i1) + num2.get(i2);
      num2.set(i2, sum%10);
    }
    //If there is a remainder from the sum, it will always be 1 and we can propagate that up
    return sum/10;
  }
}
