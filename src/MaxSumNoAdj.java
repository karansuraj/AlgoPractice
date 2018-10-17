/*package whatever //do not write package name here */

import java.util.*;
import java.lang.*;
import java.io.*;

public class MaxSumNoAdj {
    //This class is an implementation of the problem where a thief can't rob adjacent houses
    public static void main (String[] args) {
        /*Scanner a = new Scanner(System.in);
        int numTests = Integer.parseInt(a.nextLine());
        int testSize;
        String testStr;
        List<String> test;
        while(numTests>0){
            testSize = Integer.parseInt(a.nextLine());
            testStr = a.nextLine();
            test = Arrays.asList(testStr.split(" "));
            maxSumWoutAdj(test);
            numTests--;
        }*/
        List<Integer> t1out = maxSumWoutAdjList(new ArrayList<>(Arrays.asList(3,2,7,10)));
        List<Integer> t2out = maxSumWoutAdjList(new ArrayList<>(Arrays.asList(5,5,10,100,10,5)));

    }
    static List<Integer> maxSumWoutAdjList(List<Integer> list){
        List<Integer> T = new ArrayList<>();
        if(list.size()==0) return T;
        T.add(list.get(0));
        if(list.size()==1) return T;
        //T.add(list.get(1));
        T.add(Math.max(list.get(0), list.get(1)));

        for(int i=2; i<list.size(); i++){
            T.add(Math.max(list.get(i) + T.get(i-2), T.get(i-1)));
        }
        return T;
    }

    static Tree maxSumWoutAdjTree(Tree thiefTree){
        /*Scenario where instead of robbing houses in a list, rob down a tree
        Can steal from neighboring houses

         */
        return new Tree();
    }
}