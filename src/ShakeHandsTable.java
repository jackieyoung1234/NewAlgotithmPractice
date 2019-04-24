import java.util.Arrays;

public class ShakeHandsTable {
    public int calc(int n){
       if(n<=0) return -1;
       int[] mem = new int[n+1];
       Arrays.fill(mem,-1);
       return helper(n,mem);
    }
    private int helper(int n,int[] mem){
        if((n&1)==1) return 0;
        if(n==0) return 1;
        if(mem[n]!=-1) return mem[n];
        int res = 0;
        for(int i=1; i<n/2;i++){
            res += 2*helper(i-1,mem)*helper(n-1-i,mem);
        }
        res += helper(n/2-1,mem)*helper(n/2-1,mem);
        mem[n] = res;
        return res;
    }
    public static void main(String[] args){
        int i = -2;
        while(i<9) {
            System.out.println(i + " : "+  new ShakeHandsTable().calc(i++));
        }
    }
}
