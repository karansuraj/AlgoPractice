import java.util.*;
import java.io.*;

public class AllTheHands {
    private int val;

    public AllTheHands(int data){
        val = data;
    }

    public static void process(InputStream stream){
        Scanner scanner = new Scanner(System.in);
        //String n = scanner.nextLine();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream), 32768);

        List<Integer> tList = new ArrayList<Integer>(Arrays.asList(1,3,3));
        System.out.println(tList.size());
        try{
            BufferedReader in = new BufferedReader(new FileReader("test.txt"));
            StringTokenizer tokenizer;
            String inStr;
            while((inStr = in.readLine())!=null){
                tokenizer = new StringTokenizer(inStr,", ");
                System.out.println(tokenizer.countTokens());
                while(tokenizer.hasMoreTokens()) {
                    System.out.println(tokenizer.nextToken());
                }
            }
        } catch (FileNotFoundException ex){
            ex.printStackTrace();
        } catch(IOException ex){
            ex.printStackTrace();
        }

    }

    public static void main(String[] args){
        //process(System.in);
        //Print big hand below
        //Read in line of hand, tokenize, and add to list
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer tokens = new StringTokenizer(reader.readLine(),", ");
        String input = "Ah, 6h, 2h, 9h, Th";
        StringTokenizer tokens = new StringTokenizer(input,", ");
        List<String> hand = new ArrayList<String>();
        while(tokens.hasMoreTokens()) hand.add(tokens.nextToken());
        //Check from top rank to bottom rank possible poker hands
        //Check if invalid input
        boolean invalid=false;
        if(hand.size()!=5) invalid=true;
        String suit,rank;
        Set<String> validSuits = new HashSet<String>(Arrays.asList("h","c","d","s"));
        Set<String> validRanks = new HashSet<String>(Arrays.asList("2","3","4","5","6","7","8","9","T","J","Q","K","A"));
        for(String card: hand){
            if(card.length()!=2){
                invalid=true;
                break;
            }
            if(!validRanks.contains(card.substring(0,1)) || !validSuits.contains(card.substring(1,2))){
                invalid=true;
                break;
            }
        }
        if(invalid) printOut("invalid input");

        //Check "straight flush": Check if all same suit & sequential
        int suitCnt=0;
        //Check same suit
        boolean sameSuit=true;
        char curHand=hand.get(0).charAt(1);
        for(int i=1; i<hand.size(); i++){
            if(hand.get(i).charAt(1)!= curHand) sameSuit=false;
        }

        //Check if sequential cards
        boolean sequential=true;
        List<Integer> orderedHand = new ArrayList<Integer>();
        //String rank;
        int num;
        for(String card: hand) {
            rank = card.substring(0,1);
            switch(rank){
                case "T":
                    num = 10;
                    break;
                case "J":
                    num = 11;
                    break;
                case "Q":
                    num = 12;
                    break;
                case "K":
                    num = 13;
                    break;
                case "A":
                    num = 14;
                    break;
                default:
                    num = Integer.valueOf(rank);

            }
            orderedHand.add(num);
        }
        Collections.sort(orderedHand);
        Integer curCard=orderedHand.get(0);
        for(int j=1; j<orderedHand.size(); j++){
            if(orderedHand.get(j)!=curCard+1) sequential=false;
            curCard=orderedHand.get(j);
        }
        if(sameSuit && sequential) printOut("straight flush");

        //Check if four of a kind
        boolean fourOfKind=true;
        int startInd=0;
        if(orderedHand.get(0)!=orderedHand.get(1)){ //First 2 cards not equal, so start at 2nd card
            startInd=1;
        }
        int startCard=orderedHand.get(startInd);
        //startInd++;
        for(startInd=startInd+1; startInd<orderedHand.size(); startInd++){
            if(orderedHand.get(startInd)!=startCard) fourOfKind=false;
        }
        if(fourOfKind) printOut("four of a kind");

        //Check if Full House
        int threeInd=0;
        int twoInd=3;
        if(orderedHand.get(1)!=orderedHand.get(2)){
            threeInd=2;
            twoInd=0;
        }
        boolean threeOfKind=true;
        boolean onePair=true;
        if((orderedHand.get(threeInd)+orderedHand.get(threeInd+1))/2 != orderedHand.get(threeInd+2)){
            threeOfKind=false;

        } else {
            if(orderedHand.get(twoInd)!=orderedHand.get(twoInd+1)) onePair=false;
        }
        if(threeOfKind && onePair) printOut("full house");

        if(sameSuit) printOut("flush");

        if(sequential) printOut("straight");

        if(threeOfKind) printOut("three of a kind");

        int numPairs=0;
        for(int k=0; k<orderedHand.size()-1; k++){
            if(orderedHand.get(k)==orderedHand.get(k+1)) {
                numPairs++;
                k++; //Move past pair
            }
        }
        if(numPairs==2) printOut("two pair");

        if(numPairs==1) printOut("one pair");

        printOut("high card");

        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
    }
    public static void printOut(String out){
        System.out.println(out);
        System.exit(0);
    }


}
