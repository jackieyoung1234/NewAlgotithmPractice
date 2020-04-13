import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Triangle {
    public int minimumTotal(List<List<Integer>> t) {
        int size=t.size();
        if(size==0) return 0;
        if(size==1) return t.get(0).get(0);
        //todo replace queue with int[]
        Queue<Integer> q = new LinkedList<>();
        q.offer(t.get(0).get(0));
        List<Integer> curl;
        int level = 1,res = Integer.MAX_VALUE,j,qsize,cur;
        while(level<size){
            qsize=q.size();
            j=0;
            curl = t.get(level);
            while(qsize-->0){
                cur = q.poll();
                q.offer(cur+curl.get(j));
                q.offer(cur+curl.get(++j));
            }
            level++;
        }
        for(int i:q){
            res = Math.min(i,res);
        }
        return res;
    }
    public static void main(String[] args){
        Triangle t = new Triangle();
        List<List<Integer>> ll = Arrays.asList(
               Arrays.asList(1),
               Arrays.asList(2,3),
                Arrays.asList(4,5,6),
                Arrays.asList(4,5,6,7)
        );
        int res = t.minimumTotal(ll);
    }
}
