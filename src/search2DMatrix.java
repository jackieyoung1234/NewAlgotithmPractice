public class search2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if(m==0)return false;
        int n = matrix[0].length;
        if(n==0) return false;
        int lo = 0, hi = 2*m-1;
        int mid1 = 0;
        int mid2 = 0;
        int base = 0, upper=0;
        while(lo<hi){
            mid1 = lo + (hi-lo)/2;
            mid2 = mid1+1;
            if(mid1%2==0) {
                base = matrix[mid1>>1][0];
                upper= matrix[mid1>>1][n-1];
            }else{
                base = matrix[mid1>>1][n-1];
                upper= matrix[mid2>>1][0];
            }
            if(target <base) {
                hi = mid1;
            }else if(target == base){
                return true;
            }else if(target > base && target < upper){
                if(mid1%2 == 0) break;
                return false;
            }else if(target == upper){
                return true;
            }else{//>mid2
                lo = mid2;
            }
        }
        if(lo >= hi) return false;
        //binary search lo mid1 or mid2
        lo = 0;
        hi = n-1;
        int index = mid1/2;
        while(lo <= hi){
            mid1 = lo + (hi-lo)/2;
            int comp = Integer.compare(target,matrix[index][mid1]);
            if(comp==0)return true;
            else if(comp>0) lo = mid1+1;
            else hi = mid1-1;
        }
        return false;
    }
    public static void main(String [] args){
       int[][] ttt = {{1,3,5,7},{10,11,16,20},{23,30,34,50}};
       boolean tt = new search2DMatrix().searchMatrix(ttt,11);
       char a = 'a';
       a ^= 256;
    }
}
