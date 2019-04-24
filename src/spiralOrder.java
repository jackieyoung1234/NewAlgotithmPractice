import java.util.ArrayList;
import java.util.List;

public class spiralOrder {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        if(m==0)
            return new ArrayList<>();
        int n = matrix[0].length;
        //int roundTotal = Math.min(m,n)/2 + Math.min(m,n)%2;
        int roundTotal = (int)Math.ceil(Math.min(m,n)/2.0);///2 + Math.min(m,n)%2;
        List<Integer> result = new ArrayList<>(m*n);
        for(int i = 0; i < roundTotal; i++)
            helper(i,result,matrix,m,n);
        return result;
    }
    private void helper(int round, List<Integer>result,int[][] arr, int m, int n){
        //odd number corner case
        int i=round,j=round;
        while(j<n-round){
            result.add(arr[i][j++]);
        }
        j--;
        if(i++ == m-1 -round)
            return;
        while(i<m-round){
            result.add(arr[i++][j]);
        }
        i--;
        if(j-- == round)
            return;
        while(j>=round){
            result.add(arr[i][j--]);
        }
        j++;
        i--;
        while(i>round){
            result.add(arr[i--][j]);
        }
        return;
    }
    public static void main(String[] args){
        List<Integer> ss = new spiralOrder().spiralOrder(new int[][]{new int[]{1,2,3},new int[]{4,5,6},new int[]{7,8,9}});
    }
}
