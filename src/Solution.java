class Solution {
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

    public int reverse(int x) {
        /* Count the number of digits and the number of 10s places in the final output */
        if(x/10==0) return x;
        int out=0;
        int tenCt = 0; //Will be the number of digits in output
        int numDigits = 0; //Will be the number of digits in input
        //int x_temp = Math.abs(x); //Using absolute value to make operations easier
        boolean isNeg = (x<0);
        x = Math.abs(x);
        int x_temp = x;
        while(x_temp>0) {
            if(x_temp>0) tenCt++; //Number of digits in output could be less than input
            numDigits++;
            x_temp/=10;
        }
        int inPow,outPow;
        //System.out.println(tenCt + " " + numDigits);
        for(int i=0; i<tenCt; i++){ //Counting number of digits to add to out, up
            inPow = (int) (Math.pow(10,numDigits-1));
            outPow = (int) Math.pow(10,i);
            //System.out.println(inPow + " " + outPow);
            //numDigits - 1, because 10 is power of 1 of 10, but is 2 digits
            x_temp = x/inPow; // Divides to leave only top digit
            out+=x_temp*outPow; //Add top digit to lower digits of output
            x = x - x_temp*inPow; //Subtract top digit from input
            numDigits--;
            /*System.out.println(x + " " + x_temp + " " + out);
            System.out.println();*/
        }
        if(isNeg) out*=-1;
        return out;
        //char[] nChrs = x.toCharArray();
    }

    public String longestPalindrome(String s) {
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
    
 
