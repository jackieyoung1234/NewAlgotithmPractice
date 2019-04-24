import java.util.PriorityQueue;

public class Kthsmallest378 {
    public int kthSmallest(int[][] matrix, int k) {
        int len = matrix.length;
        PriorityQueue<element> minHeap = new PriorityQueue<>();
        for(int i=0;i<len;i++){
            minHeap.add(new element(0,i,matrix[0][i]));
        }
        for(int i=0;i<k-1;i++){
            element smallest2Now = minHeap.poll();
            int x = smallest2Now.x;
            int y = smallest2Now.y;
            if(x == len-1) continue;
            minHeap.add(new element(x+1,y,matrix[x+1][y]));
        }
        return minHeap.poll().v;
    }
    private class element implements Comparable<element>{
        int x;
        int y;
        int v;
        public element(int x, int y, int v){
            this.x = x;
            this.y = y;
            this.v = v;
        }
        @Override
        public int compareTo(element e){
            return Integer.compare(this.v, e.v);
        }
    }
    public static void main(String[] args){
        int res = new Kthsmallest378().kthSmallest(new int[][]{{1,5,9},{10,11,13},{12,13,15}},8);
    }
}
