class Solution {
    static int maxSubArray(int[] nums) { //Given an integer array, find contiguous subarray which has the largest sum and return sum
        /* Dynamic programming soln, greatest sum including current element
            If T[i-1] >= 0, T[i] = T[i-1] + nums[i]
            Else T[i] = nums[i]
        */
        int[] T = new int[nums.length];
        T[0] = nums[0]; //Base case
        int max = T[0];
        for(int i=1; i<nums.length; i++){
            if(T[i-1] >=0) T[i] = T[i-1] + nums[i];
            else T[i] = nums[i];
            if(T[i]>max) max = T[i];
        }
        return max;
    }

    static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //Return individual lists if either one of the inputs is null
        if(l1==null) return l2;
        if(l2==null) return l1;
        //Init new ListNode with minimum
        ListNode out = null; //Moving pointer
        if(l1.val<=l2.val) {
            out = new ListNode(l1.val);
            l1 = l1.next;
        } else {
            out = new ListNode(l2.val);
            l2 = l2.next;
        }
        ListNode finalOut = out; //Final output pointing to head of LL
        System.out.println(out.val);
        while(l1!=null || l2!=null){
            if(l1==null){
                while(l2!=null){
                    out.next = l2; //Assigning l2 to next node
                    out = out.next; //Moving to next node
                    l2 = l2.next; //Iterating on L2 LL
                }
            } else if(l2==null){
                while(l1!=null){ //Similar to l2 steps above
                    out.next = l1;
                    out = out.next;
                    l1 = l1.next;
                }
            } else{
                if(l1.val <= l2.val){
                    //if(out==null) out = l1;
                    out.next = l1;
                    //out.next = null;
                    l1 = l1.next;
                } else {
                    //if(out==null) out = l2;
                    out.next = l2;
                    l2 = l2.next;
                }

                out = out.next;
                System.out.println(out.val);
                //out  = null;
                //System.out.println(out.val);
            }
        }
        return finalOut;
    }

    static double findMedianSortedArraysOld(int[] nums1, int[] nums2) {
        if(nums1.length==0) nums1 = nums2;
        if(nums2.length==0) nums2 = nums1;
        double newArrLen = (double) (nums1.length + nums2.length);
        double midpoint = newArrLen/2.0-0.5; //defining the midpoint of both arrays (0.5 less than midpoint for even sized arrays)
        int i=0, n1Ind=0, n2Ind=0; //Index for each integer array
        double out, left=0.0, right=0.0; //Declare output var and left and right of median (when avg is necessary)
        while(i<midpoint){ //While we have not reached the midpoint of both input arrays
            if(nums1[n1Ind] <= nums2[n2Ind]){ //Inserting element from 1st array if it's less than or equal to 2nd
                left = (double)nums1[n1Ind];
                right = (double)nums2[n2Ind];
                n1Ind++;
            } else { //Inserting element from 2nd array if it's less than or equal to 1st
                left = (double)nums2[n2Ind];
                right = (double)nums1[n1Ind];
                n2Ind++;
            }
            i++;
        }
        if(newArrLen % 2 != 0) out = (double) Math.max(left, right); //If there are an odd number of indices
        else out =  (left+right)/2.0;
        return out;
    }
    static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length==0) nums1 = nums2;
        if(nums2.length==0) nums2 = nums1;
        double newArrLen = (double) (nums1.length + nums2.length);
        double midpoint = newArrLen/2.0-0.5; //defining the midpoint of both arrays (0.5 more than midpoint for even sized arrays)
        int[] newArr = new int[(int)midpoint+2]; //Creating new array of same size as length of 2 smaller arrays
        double out;
        int i=0, n1Ind=0, n2Ind=0; //Index for each integer array
        while(i<=midpoint+1){ //While we have not reached the midpoint of both input arrays
            if(n1Ind==nums1.length) { //1st array is already finished, so only add on elems from 2nd array until midpoint
                newArr[i] = nums2[n2Ind];
                n2Ind++;
            }
            else if(n2Ind==nums2.length) { //2nd array is already finished, so only add on elems from 1st array until midpoint
                newArr[i] = nums1[n1Ind];
                n1Ind++;
            }
            else{
                if(nums1[n1Ind] <= nums2[n2Ind]){ //Inserting element from 1st array if it's less than or equal to 2nd
                    newArr[i] = nums1[n1Ind];
                    n1Ind++;
                } else { //Inserting element from 2nd array if it's less than or equal to 1st
                    newArr[i] = nums2[n2Ind];
                    n2Ind++;
                }
            }
            i++;
        }
        if(newArrLen % 2 != 0) out = (double) newArr[(int) midpoint]; //If there are an odd number of indices, take midpoint
        else out =  ((double) (newArr[(int) midpoint] + newArr[(int) (midpoint+0.5)]))/2.0; //If even indices, avg midpoint
        return out;
    }
    static int romanToInt(String s) {
        int out = 0;
        int currRom, nextRom;
        for(int i=0; i<s.length(); i++){ //Loop through chars of string adding to output
            currRom = Rom(s.charAt(i)); //Current roman numeral
            if(i+1<s.length()){ //since we are looking ahead one character, need to make sure that we don't null point
                nextRom = Rom(s.charAt(i+1)); //Next roman numeral
                if(currRom < nextRom) {
                    out += (nextRom-currRom); // for a case like IX, where that's 10-1
                    i++; //Skipping ahead 2 indices (next loop will iterate 1), since we've accounted for these 2
                }
                else if(currRom >= nextRom) out+= currRom; //if greater than next character, we add this roman numeral and move on
            } else out+= currRom;
        }
        return out;
    }
    static int Rom(char a){ //Create individual roman numeral to integer converter
        int out;
        switch(a){
            case 'M': out = 1000;
                break;
            case 'D': out = 500;
                break;
            case 'C': out = 100;
                break;
            case 'L': out = 50;
                break;
            case 'X': out = 10;
                break;
            case 'V': out = 5;
                break;
            case 'I': out = 1;
                break;
            default: out = 0;
                break;
        }
        return out;
    }
    int reverse(int x) {
        /* Count the number of digits and the number of 10s places in the final output */
        if(x/10==0) return x;
        int out=0;
        int numDigitsOut; //Will be the number of digits in output
        int numDigitsIn = 0; //Will be the number of digits in input
        long long_x = (long) Math.abs(x); //Using absolute value to make operations easier
        long x_temp = long_x;
        while(x_temp>0) {
            numDigitsIn++;
            x_temp/=10;
        }
        long inPow,outPow;
        numDigitsOut = numDigitsIn;
        for(int i=0; i<numDigitsOut; i++){ //Counting number of digits to add to out, up
            inPow = (long) (Math.pow(10,numDigitsIn-1)); //Power of 10 of top digit
            outPow = (long) Math.pow(10,i); //Power of 10 of digit we are adding to output
            //numDigits - 1, because 10 is power of 1 of 10, but is 2 digits
            x_temp = long_x/inPow; // Divides to leave only top digit
            if(out + x_temp*outPow > Integer.MAX_VALUE) return 0; // if the result overflows int type, return 0
            out+=x_temp*outPow; //Add top digit to lower digits of output
            long_x = long_x - x_temp*inPow; //Subtract top digit from input
            numDigitsIn--;
        }
        if(x<0) out*=-1;
        return out;
    }

    String longestPalindrome(String s) {
        /* Seems like a dynamic programming problem we can use a 2D array to keep track of records
        T(i,j) = {
                    if(T(i+1, j-1) > 0 && s.charAt(i) == s.charAt(j))
                            T(i+1, j-1) + 2
                    else T(i,j) = 0
                }
        */
        if(s.length()==0) return s; //Empty string should return empty string
        int endInd;
        String out = s.substring(0,1); //Will be the longest palindrome substring found, currently the 1st char
        int[][] T = new int[s.length()][s.length()];
        for(int m=0; m<s.length(); m++) { //Creating all base cases
            T[m][0] = 0; //You cannot have a substring where the first ind is greater than second
            T[m][m] = 1; //Every single character by itself is a palindrome
            if(m<s.length()-1){ //Doing the first pass checking every substring of 2 chars to see if palindrome
                if(s.charAt(m) == s.charAt(m+1)) {
                    T[m][m+1] = 2;
                    out = s.substring(m,m+2); //Encountered a real palindrome, so populate the output with it
                }
                else T[m][m+1] = 0;
            }
        }
        int maxSub = 1;
        for(int subSize=2; subSize<s.length(); subSize++){ // subSize will define the size of substring-1
            for(int startInd=0; startInd+subSize<s.length(); startInd++) {
                endInd = startInd + subSize; //start at the 2nd index
                //Check if inner substring is a palindrome, and whether the overall substring is a palindrome
                if ((T[startInd + 1][endInd - 1] > 0) && (s.charAt(startInd) == s.charAt(endInd))) {
                    T[startInd][endInd] = T[startInd + 1][endInd - 1] + 2;
                }
                else T[startInd][endInd] = 0; //The substring is not a palindrome
                if (T[startInd][endInd] > maxSub) {
                    maxSub = T[startInd][endInd];
                    out = s.substring(startInd, endInd+1);
                }
            }
        }
        return out;
    }

}
    
 
