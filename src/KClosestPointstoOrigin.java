import java.util.PriorityQueue;

public class KClosestPointstoOrigin {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(K+1,(i1, i2)->
                Integer.compare(
                        euDistance(i2),
                        euDistance(i1)
                )
        );
        for(int i=0;i<points.length;i++){
            pq.offer(points[i]);
            if(i>=K) pq.poll();
        }
        return pq.toArray(new int[K][2]);
    }
    private int euDistance(int[] p){
        return x2(p[0])+x2(p[1]);
    }
    private int x2(int x){
        return x*x;
    }
    public static void main(String[] args){
        int[][] test = new int[3][2];
        KClosestPointstoOrigin kclpo = new KClosestPointstoOrigin();
        int[][] res = kclpo.kClosest(
               new int[][]{
                       {1,3},{-2,2}
               }
                ,1
        );

    }
}
