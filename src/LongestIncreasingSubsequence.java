import java.util.TreeMap;

public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        //todo: edge case, len = 0, 1
        int len = nums.length;
        if(len==0) return 0;
        //index:num of children,[index] : correspoding value
        int[] bk = new int[len+1];
        int headPtr = 0;
        bk[0] = nums[0];
        for(int i=1;i<len;i++){
            int newv = nums[i];
            int comp = Integer.compare(newv, bk[headPtr]);
            if(comp>0)
                bk[++headPtr] = newv;
            else if(comp==0){
                continue;
            }
            else {
                int idex = findlastsmall(newv, headPtr, bk);
                //edge if -1, all >= newv
                bk[idex+1] = newv;
            }
        }
        return headPtr+1;
    }

    public int findlastsmall(int v, int hi, int[] a){
        int lo = 0, h = hi;
        while(lo<h){
           int mid = lo+(h-lo+1)/2;
           int comp = Integer.compare(a[mid],v);
           if(comp>=0) h = mid-1;
           else lo=mid;
        }
        return a[lo]<v?lo:-1;
    }

    public static void main(String[] args){
       LongestIncreasingSubsequence ls = new LongestIncreasingSubsequence();
       int res = ls.lengthOfLIS(new int[]{2,2,2,2,2});
       res = ls.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18});
        res = ls.lengthOfLIS(new int[]{1,3,6,7,9,4,10,5,6});
    }
}
