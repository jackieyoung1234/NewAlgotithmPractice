import edu.princeton.cs.algs4.IndexMinPQ;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Maze {
        private static final int[][] STEP =  new int[][]{
                {0,-1},//up
                {1,0},//right
                {0,1},//down
                {-1,0}//left
        };
    public boolean hasPath(int[][] maze, int[] start, int[] dest) {
        if(start[0]==dest[0]&&start[1]==dest[1]){
            return true;
        }
        int m = maze.length, n = maze[0].length;
        //0 up 1 right 2 down 3 left
        boolean[][] visited = new boolean[m][n];
        for(int i=0;i<4;i++){
            if(dfs(start,dest,maze,m,n,i,visited)) return true;
            visited[start[0]][start[1]] = false;
        }
        return false;
    }


    private boolean dfs(int[] cur, int[] dest, int[][] maze, int m, int n, int dire,boolean[][] visited){
        if(cur[0]<0||cur[1]<0||cur[0]>=m||cur[1]>=n||maze[cur[0]][cur[1]]==1||isVisited(cur,visited)){
            return false;
        }
        visitAction(cur,visited,true);
        boolean res = false;
        int[] next = new int []{cur[0]+STEP[dire][0],cur[1]+STEP[dire][1]};
        if(isWall(next,maze,m,n)){
            if(cur[0] == dest[0]&&cur[1]==dest[1])
                res=true;
            else{
                res = dfs(new int[]{cur[0]+STEP[(dire+1)%4][0],cur[1]+STEP[(dire+1)%4][1]},dest,maze,m,n,(dire+1)%4,visited) ||
                        dfs(new int[]{cur[0]+STEP[(dire+2)%4][0],cur[1]+STEP[(dire+2)%4][1]},dest,maze,m,n,(dire+2)%4,visited) ||
                        dfs(new int[]{cur[0]+STEP[(dire+3)%4][0],cur[1]+STEP[(dire+3)%4][1]},dest,maze,m,n,(dire+3)%4,visited);
            }
        }else{
            res = dfs(next,dest,maze,m,n,dire,visited);
        }
        //visitAction(cur,dire,visited,false);
        return res;
    }

    private boolean isVisited(int[] p, boolean[][] vis){
        return vis[p[0]][p[1]];
    }
    private void visitAction(int[] p, boolean[][] vis, boolean action){
        vis[p[0]][p[1]]=action;
    }
    private boolean isWall(int[] p, int[][] maze,int m, int n){
        return p[0]==-1||p[0]==m||p[1] ==-1||p[1]==n||maze[p[0]][p[1]]==1;
    }
        public static void main(String[] args){
            IndexMinPQ<Integer> pq = new IndexMinPQ<>(6);
            Maze m = new Maze();
            boolean res = m.hasPath(
                    new int[][]{{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}},
                    new int[]{0,4},
                    new int[]{3,2}
            );
        }
}
