import java.util.*;
class LRUCache {
    //Could use a queue like data structure or array, let's try array first
    int cap;
    //Array is for keys, and HashMap will be for values
    Integer[] keys;
    Map<Integer, Integer> valMap = new HashMap<Integer, Integer>();
    public LRUCache(int capacity) {
        cap = capacity;
        keys = new Integer[cap];
        Arrays.fill(keys, -1);
    }

    public int get(int key) {
        //Capture index and value of key, shift all values on the left over 1, write into first index
        int out = -1, keyInd = -1;
        for(int j=0; j<keys.length; j++){
            if(keys[j]==key) { //Key match found
                keyInd = j;
                out = valMap.get(key);
                break;
            }
        }
        if(keyInd == -1) return -1; //Return -1 if index not found
        //Though we're using i+1 as an index, this is fine because keyInd is less than arr length
        for(int i=keyInd; i>0; i--) keys[i] = keys[i-1]; //Shift all vals left of key to the right 1
        keys[0] = (Integer)key; //Reassign 1st index to be the key, since it's being accessed
        return out;
    }

    public void put(int key, int value) {
        if(valMap.containsKey(key)){ //We won't remove any keys, just change the value for one
            this.get(key); //Shifts key to front of keys array
        } else {
            valMap.remove(keys[cap-1]); //Remove the least accessed key from map
            //Shifting all current values down
            for(int i=cap-1; i>0; i--) keys[i] = keys[i-1];
            //After shifting all values to the right, add new key into list
        }
        keys[0] = key;
        valMap.put(key, value);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
