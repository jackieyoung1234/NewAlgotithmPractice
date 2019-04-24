public class LongestLine {
    public int longestLine(int[][] M) {
        if(M.length==0||M[0].length==0)return 0;
        //direction 0,up  1, diagnal,  2, left
        return Math.max(Math.max(helperleft(M),helperup(M)),helperdiag(M));
    }
    //mutated diag solution
    private int  helperdiag(int[][] M){
        int res =0;
        for(int i=0;i<M.length;i++){
            for(int j=0;j<M[0].length;j++){
                if(M[i][j]==1){
                    if(i==0||j==0){
                        M[i][j]=1;
                    }else{
                        M[i][j]=1+M[i-1][j-1];
                    }
                    res = Math.max(M[i][j],res);
                }
            }
        }
        return res;
    }
    private int  helperleft(int[][] M){
        int res =0;
        int prev = 0;
        for(int i=0;i<M.length;i++){
            prev = 0;
            for(int j=0;j<M[0].length;j++){
                if(M[i][j]==1){
                    prev+=1;
                    res = Math.max(prev,res);
                }else{
                    prev=0;
                }
            }
        }
        return res;
    }
    private int  helperup(int[][] M){
        int res =0;
        int prev = 0;
        for(int j=0;j<M[0].length;j++){
            prev = 0;
            for(int i=0;i<M.length;i++){
                if(M[i][j]==1){
                    prev+=1;
                    res= Math.max(prev,res);
                }else{
                    prev = 0;
                }
            }
        }
        return res;
    }
    public static void main(String[] args){
       LongestLine l = new LongestLine();
       int res = l.longestLine(
               new int[][]{
                           {1,1,0,0,1,0,0,1,1,0},
                           {1,0,0,1,0,1,1,1,1,1},
                           {1,1,1,0,0,1,1,1,1,0},
                           {0,1,1,1,0,1,1,1,1,1},
                           {0,0,1,1,1,1,1,1,1,0},
                           {1,1,1,1,1,1,0,1,1,1},
                           {0,1,1,1,1,1,1,0,0,1},
                           {1,1,1,1,1,0,0,1,1,1},
                           {0,1,0,1,1,0,1,1,1,1},
                           {1,1,1,0,1,0,1,1,1,1}
               }
       );
    }
}
