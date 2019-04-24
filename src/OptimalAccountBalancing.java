import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OptimalAccountBalancing {
    public int minTransfers(int[][] transactions) {
        //id --- balance
        HashMap<Integer,Integer> info = new HashMap<>();
        for(int[] t: transactions){
            info.put(t[0],info.getOrDefault(t[0],0)-t[2]);
            info.put(t[1],info.getOrDefault(t[1],0)+t[2]);
        }
        int personcnt = info.size();
        int[] balance = new int[personcnt];
        int zerocnt = 0,i=0,v = 0;
        for(Map.Entry<Integer,Integer> e: info.entrySet()){
            v = e.getValue();
            if(v==0) zerocnt++;
            else balance[i++] = v;
        }
        Arrays.sort(balance,0,i);
        int paircnt = helper(balance,0,i-1);
        int base = personcnt-zerocnt-paircnt;
        return base==0?paircnt/2:base-1+paircnt/2;
    }
    private int helper(int[]a, int l, int h){
        if(l<=h) return 0;
        int paircnt = 0;
        int comp = 0;
        while(l<h){
            if(a[l]>0) break;
            if(a[h]<0) break;
            comp = Integer.compare(-a[l],a[h]);
            if(comp==0) {
                paircnt+=2;
                l++;
                h--;
            }
            else if(comp>0) l++;
            else h--;
        }
        return paircnt;
    }
    public static void main(String[] args){
        OptimalAccountBalancing o = new OptimalAccountBalancing();
        int res = o.minTransfers(
               new int[][]{
                       {0,1,5},
                       {4,3,2}
               }
        );
    }
}
