import java.util.TreeSet;

public class ShortestSubarrayWithSumAtLeastK {
    private class pair implements Comparable<pair>{
        int index;
        int val;
        @Override
        public int compareTo(pair b){
            int valcomp = Integer.compare(this.val,b.val);
            if(valcomp<0) return valcomp;
            return valcomp!=0?valcomp:Integer.compare(this.index,b.index);
        }
        public pair(int i, int v){
            this.index = i;
            this.val = v;
        }
    }
    public int shortestSubarray(int[] A, int K) {
        TreeSet<pair> ts = new TreeSet<>();
        ts.add(new pair(-1,0));
        int sum = 0;
        pair newp;
        int res = Integer.MAX_VALUE;
        for(int i = 0; i< A.length;i++) {
            sum+= A[i];
            newp = new pair(i,sum-K);
            for(pair cell : ts.headSet(newp,true))
                res = Math.min(i-cell.index,res);
            newp.val+=K;
            ts.add(newp);
        }
        if(res!=Integer.MAX_VALUE){
            return res;
        }
        return -1;
    }
    public static void main(String[] args){
       char b = 'a' + 1;
    }
}
