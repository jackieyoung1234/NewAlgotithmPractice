import Utilities.Timer;
import edu.princeton.cs.algs4.IndexMinPQ;

import java.util.Random;
import java.util.TreeMap;

public class PerformanceTestIndexPQVSTreeMap {
    public static void main(String[] args){
        int size = 1000;
        IndexMinPQ<Integer> impq = new IndexMinPQ<>(size);
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        Timer tm1 = new Timer();
        double t1, t2;
        Random r = new Random();
        tm1.st();
        for(int i=0;i<1000000;i++){
           for(int j=0;j<size;j++){
               impq.insert(j,r.nextInt());
           }
            for(int j=0;j<size;j++){
                impq.changeKey(j,r.nextInt());
            }
            for(int j=0;j<size;j++){
                impq.delete(j);
            }
        }
        t1=tm1.stopAndRead();
        tm1.st();
        for(int i=0;i<1000000;i++){
            for(int j=0;j<size;j++){
                tm.put(j,r.nextInt());
            }
            for(int j=0;j<size;j++){
                tm.put(j,r.nextInt());
            }
            for(int j=0;j<size;j++){
                tm.remove(j);
            }
        }
        t2=tm1.stopAndRead();
        System.out.println("time 1 is " + t1);
        System.out.println("time 2 is " + t2);
    }
}
