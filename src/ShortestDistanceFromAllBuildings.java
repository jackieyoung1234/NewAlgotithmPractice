import java.util.LinkedList;
import java.util.Queue;

public class ShortestDistanceFromAllBuildings {
    private final int[][] M = new int[][]{
            {0,1},
            {0,-1},
            {-1,0},
            {1,0},
    };
    public int shortestDistance(int[][] grid) {
        //todo: edge case, m or n = 0, no this case, because at least one building
        int bcnt = 0, m = grid.length, n = grid[0].length;
        int[][] dis = new int[m][n];
        int mindis = Integer.MAX_VALUE;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++)
                if(grid[i][j]==1){
                    bcnt++;
                }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++)
                if(grid[i][j]==1&&!bfs(new int[]{i,j,1},dis,grid,m,n,bcnt)){
                    return -1;
                }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==0)
                    mindis = Math.min(mindis,dis[i][j]);

            }
        }
        return mindis;
    }
    //return reachable cnt from this building, should be bcnt-1
    private boolean bfs(int[] pos, int[][] dis, int[][] grid,int m, int n,int bcnt){
        Queue<int[]> q = new LinkedList<>();
        int curx=0, cury=0, curlevel=0 ;
        int newx = 0, newy=0, newlevel = 0;
        int[] cur;
        boolean availableplace = false;
        boolean[][] vd = new boolean[m][n];
        int res =0;
        vd[pos[0]][pos[1]] = true;
        q.offer(pos);
        while(!q.isEmpty()){
            cur = q.poll();
            curx = cur[0];
            cury = cur[1];
            curlevel = cur[2];
            vd[curx][cury]=true;
            for(int[] mm : M){
                newx = curx+mm[0];
                newy = cury+mm[1];
                if(newx >=0 && newx <m && newy >=0 && newy <n && !vd[newx][newy] &&grid[newx][newy]!=2){
                    vd[newx][newy] = true;
                    if(grid[newx][newy]==1) res++;
                    else if(grid[newx][newy]==0){
                        availableplace = true;
                        dis[newx][newy]+=curlevel;
                        q.offer(new int[]{newx,newy,curlevel+1});
                    }
                }
            }
        }
        return res==bcnt-1&&availableplace;
    }
    public static void main(String[] args){
       int[][] map = new int[][]{
               {1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}
       };
       ShortestDistanceFromAllBuildings s = new ShortestDistanceFromAllBuildings();
       int res = s.shortestDistance(map);
       map = new int[][]{
       {1,1,1,1,1,0},{0,0,0,0,0,1},{0,1,1,0,0,1},{1,0,0,1,0,1},{1,0,1,0,0,1},{1,0,0,0,0,1},{0,1,1,1,1,0}};
       res = s.shortestDistance(map);
    }
}
