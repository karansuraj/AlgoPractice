class Solution {
    public String longestPalindrome(String s) {
        /* Seems like a dynamic programming problem we can use a 2D array to keep track of records
        T(i,j) = {
                    if(T(i+1, j-1) > 0 && s.charAt(i) == s.charAt(j))
                            T(i+1, j-1) + 2
                    else T(i,j) = 0
                }
        */
        if(s.length()==1) return s;
        int endInd;
        String out = ""; //Will be the longest palindrome substring found
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
        for(int subSize=2; subSize<s.length()-1; subSize++){ // subSize will define the size of substring-1
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
    
 
