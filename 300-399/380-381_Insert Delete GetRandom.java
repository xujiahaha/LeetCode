/**
 * Solution for  380. Insert Delete GetRandom in O(1) - No duplicates
 * Analysis: Getting a random item in HashSet takes O(n) time. 
 *           In order to get random in O(1) time, two conditions must be satisfied:
 *                 1). indices of items are consecutive numbers in the range of [0, n); 
 *                 2). given index, get the item in O(1) time. ---> (array/HashMap) 
 *           Build a bi-directional mapping between item and index. 
 * Sol #1: Use two HashMap<Integer, Integer>, one maps val to index, the other maps index to val.
 * Sol #2: since indices must be consecutive, we can use an ArrayList<Integer> to replace the HashMap that maps index to val.
 * Space complexity: O(n), where n is the number of inserted val.
*/
public class RandomizedSet {

    HashMap<Integer, Integer> map;
    ArrayList<Integer> list;
    Random rand = new Random();
    int size;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<Integer, Integer>();
        list = new ArrayList<Integer>();
        size = 0;
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(map.containsKey(val)) return false;
        map.put(val, size);
        list.add(val);
        size++;
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)) return false;
        int index = map.get(val);
        // if the val to be removed is not the last item in the map, the swap it with the last item, then remove it.
        if(index < size-1) {
            int lastItem = list.get(size-1);
            list.set(index, lastItem);
            map.put(lastItem, index);
        }
        list.remove(size-1);
        map.remove(val);
        size--;
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(rand.nextInt(size));
    }
}

/** --------------------------------------------------------------------------------------------- */

/**
 * Follow up: allow duplicates (LeetCode 381)
 * Key point: similar to 380. Instead of mapping val to an integer, we need to map val to a Set<Integer>.
 *            Set stores all indices of val. (Note: cannot use a list)
 * Space complexty: O(n)
*/ 
public class RandomizedCollection {

    private Map<Integer, Set<Integer>> map;
    private List<Integer> list;
    int size;
    Random rand = new Random();
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        map = new HashMap<Integer, Set<Integer>>();
        list = new ArrayList<Integer>();
        size = 0;
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean containVal = map.containsKey(val);
        if(!containVal) { // if first met
            map.put(val, new HashSet<Integer>());
        }
        map.get(val).add(size);
        list.add(val);
        size++;
        return !containVal;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)) return false;
        int index = map.get(val).iterator().next();
        map.get(val).remove(index);
        if(index < size -1) {
            int lastItem = list.get(size-1); // get last item in the list
            list.set(index, lastItem); // copy lastItem to index
            // update the set of indices of lastItem
            map.get(lastItem).remove(size-1); 
            map.get(lastItem).add(index);
        }
        //remove last item from list
        list.remove(size-1);
        // if index set of val is empty, remove val from map directly
        if(map.get(val).isEmpty()) map.remove(val);
        size--;
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(rand.nextInt(size));
    }
}
