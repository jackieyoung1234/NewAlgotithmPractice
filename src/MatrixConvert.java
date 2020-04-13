import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class MatrixConvert {
    private boolean match(int[][] s, int[][] e){
        for(int i=0;i<s.length;i++){
            for(int j=0;j<s[0].length;j++){
                if(s[i][j]!=e[i][j]) return false;
            }
        }
        return true;
    }
    private String encode(int[][] a){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[0].length;j++){
                sb.append(a[i][j]);
            }
        }
        return sb.toString();
    }
    private int[] find0(int[][] a){
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[0].length;j++){
                if(a[i][j]==0) return new int[]{i,j};
            }
        }
        return new int[]{-1,-1};
    }
    private int[][] swapAndCreate(int[][] a, int x, int y, int x1, int y1){
        int[][] res = new int[a.length][a[0].length];
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[0].length;j++){
                res[i][j] = a[i][j];
            }
        }
        res[x][y] = a[x1][y1];
        res[x1][y1] = a[x][y];
        return res;
    }
    private void swap(int[][] a, int x, int y, int x1, int y1){
        int tmp = a[x][y];
        a[x][y] = a[x1][y1];
        a[x1][y1] = tmp;
    }
    private void tryAddChildIfNotVisitedBefore(int[][] a, int[] pos0, Queue<int[][]> q, Queue<int[]> q0, HashSet<String> visited){
        int x0 = pos0[0],y0 = pos0[1];
        int[][] copy;
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[0].length;j++){
                if(i!=x0||j!=y0){
                    //create a new deep copy
                    copy = swapAndCreate(a,x0,y0,i,j);
                    if(visited.add(encode(copy))){
                        q.add(copy);
                        q0.add(new int[]{i,j});
                    }
                    //onlly swap
                    swap(a,x0,y0,i,j);
                }
            }
        }
    }
    public int minStep(int[][] st, int[][] end){
        if(match(st,end)) return 0;
        int[][] cur;
        int[] pos0;
        Queue<int[][]> q = new LinkedList<>();
        Queue<int[]> q0 = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        int size=1,step = 0;
        q.offer(st);
        //todo
        visited.add(encode(st));
        q0.offer(find0(st));
        while((size=q.size())>0){
            step++;
            System.out.println("step : "+step);
            while(size-->0){
                cur = q.poll();
                if(match(cur,end)) return step;
                pos0 = q0.poll();
                System.out.println("pos0 : "+pos0[0]+" "+pos0[1]);
                tryAddChildIfNotVisitedBefore(cur,pos0,q,q0,visited);
            }
        }
        return -1;
    }
    public static void main(String[] args){
        MatrixConvert m = new MatrixConvert();
        int res = m.minStep(
                new int[][]{{0,2},{4,3},{1,6}},
                new int[][]{{4,1},{3,0},{6,2}}
        );
    }
}
