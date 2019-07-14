public class UniquePathIII {
    public int uniquePathsIII(int[][] grid) {
        int zerocnt=0;
        int m=grid.length, n= grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int[] st = new int[2];
        int[] end = new int[2];
        for(int mi=0;mi<m;mi++){
            for(int ni=0;ni<n;ni++){
                if(grid[mi][ni]==0) zerocnt++;
                if(grid[mi][ni]==1){
                    zerocnt++;
                    st[0] = mi;
                    st[1]=ni;
                }
                if(grid[mi][ni]==2){
                    end[0] = mi;
                    end[1]=ni;
                }
            }
        }
        return dfs(st[0],st[1],end,0,zerocnt,visited,grid,m,n);
    }
    public int dfs(int x, int y, int[] end,int walkcnt, int zerocnt, boolean[][] visited, int[][] grid, int m, int n ){
        if(x<0||y<0||x>=m||y>=n || grid[x][y]==-1 || visited[x][y])  return 0;
        if(x==end[0]&&y==end[1])
            return walkcnt==zerocnt?1:0;
        visited[x][y] = true;
        int res = 0;
        res +=dfs(x-1,y,end,++walkcnt,zerocnt,visited,grid,m,n);
        res +=dfs(x,y-1,end,walkcnt,zerocnt,visited,grid,m,n);
        res +=dfs(x+1,y,end,walkcnt,zerocnt,visited,grid,m,n);
        res +=dfs(x,y+1,end,walkcnt,zerocnt,visited,grid,m,n);
        visited[x][y] = false;
        return res;
    }
    public static void main(String[] args){
        int res = new UniquePathIII().uniquePathsIII(
                new int[][]{
                        {1,0,0,0},
                        {0,0,0,0},
                        {0,0,2,-1}
                }
        );
    }
}
