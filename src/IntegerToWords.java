import java.util.*;
class IntegerToWords {
    public static String IntegerToWords(int num) { //Takes an integer less than 2^31 - 1, and converts to text
        /* Divide number up by groups of 3 digits. All include the words one, two, three, four, five, six, seven, eight, nine, ten, eleven
        twelve, thirteen, fourteen, fifteen, sixteen, seventeen, eighteen, nineteen, twenty, thirty, forty, fifty, sixty, seventy, eighty,
        ninety, hundred
        Beyond that, at every level except for the first 3, it's thousand, million, billion
        So we can at least work with a 3 digit number first and see where we get, then apply that to bigger numbers
        */
        if(num==0) return "Zero"; //Base case

        char[] ones = {'e','e','e'};
        char[] thousands = {'e','e','e'};
        char[] millions  = {'e','e','e'};
        char[] billions  = {'e','e','e'};
        String numStr = Integer.toString(num);
        int oneInd=2, thsInd=2, milInd=2, bilInd=2;
        int numInd=numStr.length()-1;
        while(numInd>=0){ //Populating Backward in case number is no a 100s number
            //Go through each set of 3, populating until input number is finished
            if(oneInd>=0) {
                ones[oneInd] = numStr.charAt(numInd);
                oneInd--;
            } else{
                if(thsInd>=0){
                    thousands[thsInd] = numStr.charAt(numInd);
                    thsInd--;
                } else{
                    if(milInd>=0){
                        millions[milInd] = numStr.charAt(numInd);
                        milInd--;
                    } else{
                        if(bilInd>=0){
                            billions[bilInd] = numStr.charAt(numInd);
                            bilInd--;
                        }
                    }
                }
            }
            numInd--;
        }
        //Get string for each set of 3 digits for the number
        String bilStr=threeDigitConv(billions);
        String milStr=threeDigitConv(millions);
        String thsStr=threeDigitConv(thousands);
        String oneStr=threeDigitConv(ones);;
        StringBuilder out = new StringBuilder("");
        if(!bilStr.isEmpty()) out.append(bilStr).append(" Billion ");
        if(!milStr.isEmpty()) out.append(milStr).append(" Million ");
        if(!thsStr.isEmpty()) out.append(thsStr).append(" Thousand ");
        if(!oneStr.isEmpty()) out.append(oneStr);
        return out.toString().trim(); //Trim the output in case there's extra spaces

    }

    private static String threeDigitConv(char[] ones){
        StringBuilder out = new StringBuilder("");
        HashMap<Character, String> oneMap = new HashMap<Character,String>() //Ones digit hashmap
        {{
            put('e',""); put('0', ""); put('1',"One"); put('2', "Two"); put('3', "Three"); put('4', "Four");
            put('5', "Five"); put('6', "Six"); put('7', "Seven"); put('8', "Eight"); put('9', "Nine");
        }};
        HashMap<Character, String> teenMap = new HashMap<Character,String>() //Teens digits hashmap
        {{
            put('e',""); put('0',"Ten"); put('1', "Eleven"); put('2', "Twelve"); put('3', "Thirteen"); put('4', "Fourteen");
            put('5', "Fifteen"); put('6', "Sixteen"); put('7', "Seventeen"); put('8', "Eighteen"); put('9', "Nineteen");
        }};
        HashMap<Character, String> tensMap = new HashMap<Character,String>() //Tens digits hashmap
        {{
            put('e',""); put('0',""); put('2', "Twenty "); put('3', "Thirty "); put('4', "Forty ");
            put('5', "Fifty "); put('6', "Sixty "); put('7', "Seventy "); put('8', "Eighty "); put('9', "Ninety ");
        }};
        //If there is a hundreds digit, append ones digit and "Hundred"
        if(ones[0]!='e' && ones[0]!='0') out.append(oneMap.get(ones[0])).append(" Hundred ");
        //If tens digit is 1, append teen number. Else, append tens number and ones digit number
        if(ones[1]=='1') out.append(teenMap.get(ones[2]));
        else out.append(tensMap.get(ones[1])).append(oneMap.get(ones[2]));

        return out.toString().trim();

    }
}