public class SparseMatrixMultiplication {
    public int[][] multiply(int[][] A, int[][] B) {
        //todo: edge case arow = 0, acol=0, brow = 0, b col  =0
        int row = A.length;
        int acol = A[0].length;
        int col = B[0].length;
        int[][] res = new int[row][col];
        for(int r=0;r<row;r++){
            for(int c=0;c<col;r++){
                res[r][c] = helper(A[r],B,acol,c);
            }
        }
        return res;
    }
    private int helper(int[] a, int[][] b, int len, int cindex){
        int res = 0;
        for(int i=0;i<len;i++){
            res+=(a[i]*b[i][cindex]);
        }
        return res;
    }
    public static void main(String[] args){
        int[] i1 = new int[]{1,2};
        int[] i2 = new int[]{1,2};
        boolean ii = i1.equals(i2);
       int[][] res = new SparseMatrixMultiplication().multiply(
            new int[][]{{1,0,0},{-1,0,3}},
            new int[][]{{7,0,0},{0,0,0},{0,0,1}}
       );
    }
}
