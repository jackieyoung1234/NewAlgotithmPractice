import java.util.*;

public class RandomizedSet {
    Map<Integer,Integer> v2index;
    List<Integer> vlist;
    Random rand;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        v2index = new HashMap<Integer,Integer>();
        vlist = new ArrayList<>();
        rand = new Random();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        //if(v2index.putIfAbsent(val,vlist.size())!=null) return false;
        if(v2index.containsKey(val)) return false;
        v2index.put(val,vlist.size());
        vlist.add(val);
        return true;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        int index = v2index.getOrDefault(val,-1);
        if(index==-1) return false;
        int sizem1 = vlist.size()-1;
        Integer last = vlist.get(sizem1);
        vlist.set(index,last);
        vlist.remove(sizem1);
        v2index.remove(val);
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return vlist.get(rand.nextInt(vlist.size()));
    }
    public static void main(String[] args){
       RandomizedSet r = new RandomizedSet();
       r.insert(3);
       r.insert(-2);
       r.remove(2);
       r.insert(1);
       r.insert(-3);
       r.insert(-2);
       r.remove(-2);
       r.remove(3);
       r.insert(-1);
       r.remove(-3);
       r.insert(1);
       r.insert(-2);
       r.insert(-2);
       r.insert(-2);
       r.insert(1);
       r.getRandom();
       r.insert(-2);
       r.remove(0);
       r.insert(-3);
       r.insert(1);
    }
}
