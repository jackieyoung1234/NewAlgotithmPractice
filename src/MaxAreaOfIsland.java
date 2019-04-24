import java.util.LinkedList;
import java.util.Queue;

public class MaxAreaOfIsland {
    private static final int[][] MOVES = new int[][]{
        {1,0},
        {-1,0},
        {0,-1},
        {0,1}
    };
    public int maxAreaOfIsland(int[][] grid) {
        //todo m = 0 or n = 0
        int m = grid.length;
        if(m==0) return 0;
        int n = grid[0].length;
        if(n==0) return 0;
        int res = 0,res2n=0;
        int newx = 0, newy = 0;
        int[] cur;// = new int[2];
        Queue<int[]> q = new LinkedList<>();
        for(int i=0;i<m;i++){
           for(int j=0;j<n;j++){
              if(grid[i][j]!=0){
                 grid[i][j] = 0;
                 q.offer(new int[]{i,j});
                 while(!q.isEmpty()){
                    cur = q.poll();
                    grid[cur[0]][cur[1]] = 0;
                    res2n++;
                    for(int[] move:MOVES){
                       newx = cur[0]+move[0];
                       newy = cur[1]+move[1];
                       if(newx>=0 && newx<m && newy>=0 && newy< n&& grid[newx][newy]!=0) {
                           q.offer(new int[]{newx, newy});
                       }
                    }
                 }
                 res = Math.max(res,res2n);
                 q.clear();
                 res2n = 0;
              }
           }
        }
        return res;
    }
    public static void main(String[] args){
        MaxAreaOfIsland m  = new MaxAreaOfIsland();
        int res = m.maxAreaOfIsland(
               new int[][]{
                       {1,1,0,0,0},
                       {1,1,0,0,0},
                       {0,0,0,1,1},
                       {0,0,0,1,1},
               }
        );
    }
}
