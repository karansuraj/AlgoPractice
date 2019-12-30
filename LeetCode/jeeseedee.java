import java.util.*;

public class jeeseedee {

    public static int generalizedGCD(int num, int[] arr){
        if(arr.length==0) return 0;
        if(arr.length==1) return arr[0];

        Arrays.sort(arr);
        int res=1,x=1;
        while(x<=arr[0]){
            x++;
            for(int i=0; i<num; i++){
                if(arr[i] % x !=0) break;
                if(i==num-1) res = x;
            }
        }
        return res;
    }

    static List<List<Integer>> ClosestXdestinations(int numDestinations,
                                             List<List<Integer>> allLocations,
                                             int numDeliveries)
    {
        //Find closest x destinations from the start point 0,0
        //1. Calculate the distances from origin for all coordinates while putting into list
        //2. Map (with Hashmap) calc'd distances to indices in location List
        //3. Sort distances list ascending
        //4. Iterate through distances list for numDeliveries, getting indices from hashmap to put in output list

        List<Integer> currLoc;
        List<Double> distances = new ArrayList<Double>();
        Map<Double,Integer> distMap = new HashMap<Double,Integer>();
        double distFromOrg;
        //Loop over destinations, calculating distances and mapping distances to locations
        for(int i=0; i<numDestinations; i++){
            currLoc = allLocations.get(i);
            distFromOrg = Math.sqrt(Math.pow(currLoc.get(0),2) + Math.pow(currLoc.get(1),2));
            if(!distMap.containsKey(distFromOrg)){ //Don't want to add duplicate distance to dist list
                distances.add(distFromOrg);
                distMap.put(distFromOrg,i);
            }
        }

        //Sort distance list
        Collections.sort(distances);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int LocationIndex;
        //Loop through distance list and put corresponding location in result
        for(int j=0; j<numDeliveries; j++){
            LocationIndex = distMap.get(distances.get(j));
            result.add(allLocations.get(LocationIndex));
        }
        return result;
    }

    public static void main(String[] args){
        /*List<List<Integer>> t1Locs = new ArrayList<List<Integer>>();
        List<Integer> l1 = new ArrayList<>();
        l1.add(1); l1.add(-3);
        List<Integer> l2 = new ArrayList<>();
        l2.add(1); l2.add(2);
        List<Integer> l3 = new ArrayList<>();
        l3.add(3); l3.add(4);
        t1Locs.add(l1);
        t1Locs.add(l2);
        t1Locs.add(l3);
        ClosestXdestinations(3, t1Locs, 1);
*/

        List<List<Integer>> t2Locs = new ArrayList<List<Integer>>();
        List<Integer> a1 = new ArrayList<>();
        a1.add(3); a1.add(6);
        List<Integer> a2 = new ArrayList<>();
        a2.add(2); a2.add(4);
        List<Integer> a3 = new ArrayList<>();
        a3.add(5); a3.add(3);
        List<Integer> a4 = new ArrayList<>();
        a4.add(2); a4.add(7);
        List<Integer> a5 = new ArrayList<>();
        a5.add(1); a5.add(8);
        List<Integer> a6 = new ArrayList<>();
        a6.add(7); a6.add(9);
        t2Locs.add(a1);
        t2Locs.add(a2);
        t2Locs.add(a3);
        t2Locs.add(a4);
        t2Locs.add(a5);
        t2Locs.add(a6);
        ClosestXdestinations(6, t2Locs, 3);

        /*int[] t1 = new int[]{2,3,4,5,6};
        System.out.println(generalizedGCD(5,t1));

        int[] t2 = new int[]{2,4,6,8,10};
        System.out.println(generalizedGCD(5,t2));*/

        /*int[] t3 = new int[]{9,24,39,42,48};
        System.out.println(generalizedGCD(5,t3));*/
    }

}
