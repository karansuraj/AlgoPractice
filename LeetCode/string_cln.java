import java.util.*;

public class string_cln {

    public static String answer(String chunk, String word){
        String first,second;
        int lastInd = chunk.lastIndexOf(word);
        while(lastInd!=-1){
            first = chunk.substring(0, lastInd);
            second = chunk.substring(lastInd+word.length(), chunk.length());
            chunk = first+second;
            lastInd = chunk.lastIndexOf(word);
        }
        return chunk;
    }


    public static String answer2(String chunk, String word) {
        int minLen = Integer.MAX_VALUE;
        String first, second, combined;
        List<Integer> wrdInds = new ArrayList<>();
        List<String> cleanedStrs = new ArrayList<>();
        int fndInd = word.indexOf(word);
        while (fndInd >= 0) {
            wrdInds.add(fndInd);
            fndInd = chunk.indexOf(word, fndInd+1);
        }
        for(int j=wrdInds.size()-1; j>=0; j--) {
            combined = chunk;
            fndInd = wrdInds.get(j); //combined.lastIndexOf(word);
            while (fndInd >= 0) {
                first = combined.substring(0, fndInd);
                second = combined.substring(fndInd + word.length(), combined.length());
                combined = first + second;
                fndInd = combined.lastIndexOf(word);
            }
            if(combined.length()<minLen) minLen = combined.length();
            cleanedStrs.add(combined);
        }
        for(int i=0; i<cleanedStrs.size(); i++){
            if(cleanedStrs.get(i).length()>minLen) cleanedStrs.remove(i);
        }
        //Collections.sort(cleanedStrs);
        return cleanedStrs.get(0);
    }

    public static void main(String[] args){
        String t1 = answer2("lololololo", "lol"); //looo
        String t2 = answer2("goodgooogoogfogoood", "goo"); //dogfood
        String t3 = answer2("lolol", "lol"); //lo

        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);
    }

}
