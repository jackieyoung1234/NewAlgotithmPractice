import java.util.Arrays;

public class KnightProbability {
    private int[][] moves = new int[][]{
            {1,-2},
            {1,2},
            {-1,-2},
            {-1,2},
            {-2,-1},
            {-2,1},
            {2,-1},
            {2,1},
    };
    public double knightProbability(int N, int K, int r, int c) {

        //initialize dp[][][] to all -1
        //info  [0] K  [1] N [2] res
        double[][] dp = new double[K][N*N];
        for(double[] entry:dp){
            Arrays.fill(entry,-1);
        }
        int[] info = new int[]{K,N};
        return helper(0,info,r,c,dp);
    }
    //first nth is 0
    //info  [0] K  [1] N
    private double helper(int nth, int[] info,int r, int c, double[][] dp){
        if(nth==info[0]) {
            return 1;
        }
        int key = r*info[1]+c;
        if(dp[nth][key]!=-1) return dp[nth][key];
        double res = 0;
        for(int[] move:moves){
            int newr = r+move[0];
            int newc = c+move[1];
            if(validMove(newr,newc,info[1]))
                res +=helper(nth+1,info,newr,newc,dp);
        }
        res/=8;
        dp[nth][key]=res;
        return res;
    }
    private boolean validMove(int r, int c, int N){
        if(r>=N || r<0 || c>=N ||c<0)  return false;
        return true;
    }
    public static void main(String[] args){
       KnightProbability kp = new KnightProbability();
       double res = kp.knightProbability(8,30,6,4);
    }
}
