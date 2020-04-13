import java.util.Arrays;

public class CherryPick {
    public int cherryPickup(int[][] grid) {
        //todo memoi 2d to 1d
        int len = grid.length;
        int[][] memoi = new int[len+1][len+1];
        boolean[][] iscon = new boolean[len+1][len+1];
        //todo remove this to see results
        if(grid[0][0]==-1||grid[len-1][len-1]==-1) return 0;
        iscon[1][1] = true;
        int[] parent = new int[(len+1)*(len+1)];
        int index = 0;
        int x = len, y = len;
        int nx = 0, ny = 0;
        for(int i=1;i<=len;i++){
            for(int j=1;j<=len;j++){
                if(grid[i-1][j-1]==-1){
                    iscon[i][j] = false;
                    continue;
                }
                if(!iscon[i-1][j]&&!iscon[i][j-1]){
                    iscon[i][j] = false;
                    continue;
                }
                iscon[i][j] = true;
                if(memoi[i-1][j]>=memoi[i][j-1]){
                    memoi[i][j] = grid[i-1][j-1] + memoi[i-1][j];//,memoi[i][j-1]);
                    if(i>1&&j>1){
                        index = i*(len+1)+j;
                        parent[index] = index-len;
                    }
                }else{
                    memoi[i][j] = grid[i-1][j-1] + memoi[i][j-1];
                    if(i>1&&j>1){
                        index = i*(len+1)+j;
                        parent[index] = index-1;
                    }
                }
            }
        }
        if(!iscon[len][len]) return 0;
        while(x>1&&y>1){
            grid[x-1][y-1]=0;
            index = parent[x*(len+1)+y];
            x = index/(len+1);
            y = index%(len+1);
        }
        if(x==1){
            for(;y>=1;){
                grid[0][--y] = 0;
            }
        }else{
            for(;x>=1;){
                grid[--x][0] = 0;
            }
        }
        int mid = memoi[len][len];
        for(int[] m : memoi){
            Arrays.fill(m,0);
        }
        for(boolean[] b:iscon){
            Arrays.fill(b,false);
        }
        iscon[len-1][len-1] = true;
        for(int i=len-1;i>=0;i--){
            for(int j=len-1;j>=0;j--){
                if(grid[i][j]==-1){
                    iscon[i][j] = false;
                    continue;
                }
                if(!iscon[i+1][j]&&!iscon[i][j+1]){
                    iscon[i][j] = false;
                    continue;
                }
                iscon[i][j] = true;
                memoi[i][j] = grid[i][j] + Math.max(memoi[i+1][j],memoi[i][j+1]);
            }
        }
        return memoi[0][0]+mid;
    }
    public static void main(String[] args){
       CherryPick c = new CherryPick();
       int res = c.cherryPickup(
              new int[][]{
                      {0,1,-1},
                      {1,0,-1},
                      {1,1,1},
              }
       );
    }
}
