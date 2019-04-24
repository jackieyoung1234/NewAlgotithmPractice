public class AndroidUnlockPatterns {
    private static final int[][] MOVE= new int[][]{
        {0,1},
        {1,0},
        {1,1},
        {0,-1},
        {-1,0},
        {1,-1},
        {-1,1},
        {-1,-1},
        {1,2},
        {2,1},
        {-1,2},
        {1,-2},
        {2,-1},
        {-2,1},
        {-1,-2},
        {-2,-1},
        //conditional
        {0,2},
        {2,0},
        {0,-2},
        {-2,0},
        {2,2},
        {-2,2},
        {2,-2},
        {-2,-2}
    };
    public int numberOfPatterns(int m, int n) {
        //edge case
        if(m>n) return 0;
        boolean[][] record = new boolean[3][3];
        int res = 0;
        int[] info = new int[]{1,0};
        dfs(record,0,0,info,m,n);
        res += (info[1]*4);
        info[0]=1;
        info[1]=0;
        dfs(record,0,1,info,m,n);
        res += (info[1]*4);
        info[0]=1;
        info[1]=0;
        dfs(record,1,1,info,m,n);
        res += info[1];
        return res;
    }
    //info: 0---depth to now
    //      1---result
    private void dfs(boolean[][] pos, int x, int y, int[] info,int m, int n){
        if(info[0]>n) return;
        if(info[0]>=m && info[0]<=n)
            info[1]++;
        pos[x][y] = true;
        for(int[] step :MOVE){
           int newx = x+step[0];
           int newy = y+step[1];
           if(validated(newx,newy,x,y,step,pos)) {
               info[0]++;
               dfs(pos, newx, newy, info, m, n);
               info[0]--;
           }
        }
        pos[x][y] = false;
    }
    private boolean validated(int x, int y, int ox, int oy, int[] inc, boolean[][] pos){
       if(x<0||y<0||x>2||y>2|| pos[x][y]) return false;
       if(
           (Math.abs(Math.abs(inc[1])-Math.abs(inc[0]))==2)
           ||
           (Math.abs(inc[1])==2 && Math.abs(inc[0])==2)
         ){
          if(!pos[ox+inc[0]/2][oy+inc[1]/2]) return false;
       }
       return true;
    }
    public static void main(String[] args){
       AndroidUnlockPatterns ap = new AndroidUnlockPatterns();
       int res = ap.numberOfPatterns(3,3);
       res = ap.numberOfPatterns(2,2);
       res = ap.numberOfPatterns(2,2);
       res = ap.numberOfPatterns(1,2);
    }
}
