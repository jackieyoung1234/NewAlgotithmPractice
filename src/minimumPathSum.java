public class minimumPathSum {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int i = 1;
        for(;i<n;i++)
            grid[0][i] += grid[0][i-1];
        for(i = 1;i<m;i++)
            grid[i][0] += grid[i-1][0];
        for(i = 1; i < m; i++){
            for(int j = 1; j< n; j++)
                grid[i][j] += Math.min(grid[i-1][j],grid[i][j-1]);
        }
        return grid[m-1][n-1];
    }
    public static void main(String [] args){
        int res = new minimumPathSum().minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}});
        System.out.print(new int[]{1,2,3}[0]);
    }
}
