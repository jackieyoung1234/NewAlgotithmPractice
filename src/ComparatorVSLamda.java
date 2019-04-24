import Utilities.Timer;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;

public class ComparatorVSLamda {
    public static void main(String[] args){
        PriorityQueue<Integer> p1 = new PriorityQueue<>(Collections.reverseOrder());
        Random r = new Random();
        Timer t1 = new Timer();
        t1.st();
        for(int i=0;i<1000000;i++){
            p1.offer(r.nextInt());
        }
        double d1 = t1.stopAndRead();
        PriorityQueue<Integer> p2 = new PriorityQueue<>((i1,i2)->Integer.compare(i2,i1));
        Timer t2= new Timer();
        t2.st();
        for(int i=0;i<1000000;i++){
            p2.offer(r.nextInt());
        }
        double d2 = t2.stopAndRead();
        System.out.println(String.format("time for rever order is %f for lamda is %f",d1,d2));
    }
}
