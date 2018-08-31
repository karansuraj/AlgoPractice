class Solution {
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
    
 
