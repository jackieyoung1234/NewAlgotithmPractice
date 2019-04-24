import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class SuperUglyNumber {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int len = primes.length;
        int[] res = new int[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] i1, int[] i2){
                return Integer.compare(i1[1],i2[1]);
            }
        }

        );
        //0-- ptr,   1--val, 2--index in primes
        int[] mine = new int[3];
        for(int i=0;i<len;i++){
            pq.add(new int[]{0,primes[i],i});
        }
        for(int i=1;i<n;i++){
            mine = pq.poll();
            res[i] = mine[1];
            //find min of cadi, to update res[i]
            //update every min cadi's cnt ptr and update cadi value
            LinkedList<int[]> l = new LinkedList<>();
            mine[1]= res[++mine[0]]*primes[mine[2]];
            //add back
            l.add(mine);
            while(pq.peek()[1]==res[i]){
                int[] minenew = pq.poll();
                minenew[1]= res[++minenew[0]]*primes[minenew[2]];
                l.add(minenew);
            }
            pq.addAll(l);
        }
        return res[n-1];
    }
    public static void main(String[] args){
       SuperUglyNumber ss = new SuperUglyNumber();
       int x =0;
       Integer i = 1;
       Integer ii = i+1;
       i++;
    }
}
