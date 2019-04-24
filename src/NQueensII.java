public class NQueensII {
    public int totalNQueens(int n) {
        if(n<=0) return 0;
        return helper(n,new boolean[2*n-1], new boolean[2*n-1], new boolean[n], 0);
    }
    //                              size 2*n-1,  size 2*n-1        size n
    //private int/void helper(int n,boolean[] lbottom2r, boolean[] rtop2l, boolean[] row, int j){
    private int helper(int n,boolean[] lbottom2r, boolean[] rtop2l, boolean[] row, int j){
        if(j==n)
            return 1;
        int ans = 0;
        for(int i=0;i<n;i++){
            if(!lbottom2r[i+j]&&!rtop2l[j-i+n-1]&&!row[i]){
                lbottom2r[i+j]=true;
                rtop2l[j-i+n-1]=true;
                row[i]=true;
                ans+=helper(n,lbottom2r,rtop2l, row, j+1);
                lbottom2r[i+j]=false;
                rtop2l[j-i+n-1]=false;
                row[i]=false;
            }
        }
        return ans;
    }
    public static void main(String[] args){
        NQueensII nq2 = new NQueensII();
        int res = nq2.totalNQueens(4);
    }
}
