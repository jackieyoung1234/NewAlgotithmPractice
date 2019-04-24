import java.util.*;

public class HashMapWithExp {
    private static final int EXP_VAL = Integer.MIN_VALUE;
    private class Entry{
        int v;
        long exptime;
        public Entry(int v,long exptime){
            this.v = v;
            this.exptime = exptime;
        }
    }
    HashMap<Integer, Entry> hm;
    public HashMapWithExp(){
        hm = new HashMap<>();
    }

    public void put(int k, int v, long exp){
        long expt = System.currentTimeMillis()+exp;
        Entry n = new Entry(v,expt);
        hm.put(k,n);
    }

    public int get(int k){
        if(!hm.containsKey(k) || isExp(hm.get(k))){
            hm.remove(k);
            return EXP_VAL;
        }
        return hm.get(k).v;
    }

    private boolean isExp(Entry e){
        return System.currentTimeMillis()<e.exptime;
    }

    public void removeExpEntry(){
    }

    public static void main(String[] args){
        TreeSet<int[]> ts = new TreeSet<>((i1,i2)->Integer.compare(i1[0],i2[0]));
        ts.add(new int[]{1,1});
        ts.add(new int[]{2,2});
        ts.add(new int[]{3,3});
        Set<int[]> ss = ts.headSet(new int[]{2,6},true);
        ss.clear();
        TreeMap<Integer,Integer> tm = new TreeMap<>();
        tm.put(1,1);
        tm.put(2,1);
        tm.put(3,1);
        tm.put(4,1);
        tm.put(5,1);
        tm.put(6,1);
        Map<Integer,Integer> mm =  tm.headMap(3,true);//   // .clear();
        for(int k: mm.keySet()){
            System.out.println(k);
        }
        tm.put(7,1);
    }
}
