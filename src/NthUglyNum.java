import java.util.ArrayList;

public class NthUglyNum {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int ptr2 = 0, ptr3 = 0, ptr5 = 0;
        int cadi2 = dp[ptr2]*2;
        int cadi3 = dp[ptr3]*3;
        int cadi5 = dp[ptr5]*5;
        for(int i=1;i<n;i++){
            dp[i] = Math.min(Math.min(cadi2,cadi3),cadi5);
            if(dp[i]==cadi2){
                cadi2 = dp[++ptr2]*2;
            }
            if(dp[i]==cadi3){
                cadi3 = dp[++ptr3]*3;
            }
            if(dp[i]==cadi5){
                cadi5 = dp[++ptr5]*5;
            }
        }
        return dp[n-1];
    }
    public static void main(String[] args){
       int res = new NthUglyNum().nthUglyNumber(10);
       ArrayList<Integer> l = new ArrayList<>();
       int x = 0;
       l.add(x=3+5);
    }
}
