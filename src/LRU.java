import java.util.HashMap;
import java.util.PriorityQueue;

public class LRU {
    private class KeyAgePair implements Comparable<KeyAgePair>{
        Integer k;
        int a;
        public KeyAgePair(int k, int a){
           this.k = k;
           this.a = a;
        }
        @Override
        public int hashCode(){
           return k.hashCode();
        }
        @Override
        public boolean equals(Object kap2){
           KeyAgePair k2 = (KeyAgePair) kap2;
           return this.k.equals(k2.k);
        }
        @Override
        public int compareTo(KeyAgePair k2){
           return Integer.compare(k2.a,this.a);
        }
    }
    private PriorityQueue<KeyAgePair> pq;
    //int val
    private HashMap<Integer,Integer> hm;
    private int minage;
    private int ca;
    private int victimkey;
    public LRU(int capacity) {
       ca = capacity;
       minage = 0;
       hm = new HashMap<>();
       pq = new PriorityQueue<>();
       victimkey=0;
    }

    private void updateVictim(){
       victimkey = pq.peek().k;
    }
    public int get(int key) {
       if(!hm.containsKey(key)) return -1;
       int val = hm.get(key);
       if(key == victimkey){
           pq.poll();
       }else{
           pq.remove(new KeyAgePair(key,0));
       }
       pq.offer(new KeyAgePair(key,--minage));
       updateVictim();
       return val;
    }

    public void put(int key, int value) {
       int usedcnt = hm.size();
       if(usedcnt == ca && !hm.containsKey(key)){
          hm.remove(victimkey);
          pq.poll();
          pq.offer(new KeyAgePair(key,--minage));
          //hm.put(key, value);
       }else if(hm.containsKey(key)){
           if(key == victimkey){
              pq.poll();
           }else{
              pq.remove(new KeyAgePair(key,0));
           }
           pq.offer(new KeyAgePair(key,--minage));
           //hm.put(key, value);
       }else{// i.e.,usedcnt!=capacity&&!hm.containsKey(key)){
          //hm.put(key,value);
          pq.offer(new KeyAgePair(key,--minage));
          usedcnt++;
       }
       hm.put(key, value);
       updateVictim();
    }
    public static void main(String[] args){
        LRU cache = new LRU(2);
        cache.put(1, 1);
        cache.put(2, 2);
        int res = cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        res = cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        res =cache.get(1);       // returns -1 (not found)
        res = cache.get(3);       // returns 3
        res =cache.get(4);       // returns 4
    }
}
