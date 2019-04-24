import java.util.Arrays;

public class CountOfRangeSum {

    public int countRangeSum(int[] nums, int lower, int upper) {
        // todo nums.length =0
        int len = nums.length;
        if(len==0) return 0;
        long[] sums=new long[len];
        long[] sorted;
        sums[0]=nums[0];
        for(int i=1;i<len;i++){
            sums[i] += sums[i-1]+nums[i];
        }
        sorted = Arrays.copyOf(sums,len);
        Arrays.sort(sorted);
        int[] ftree = new int[len+1];
        int l = 0, h = 0,res =0;
        for(int i=0;i<len;i++){
            if(sums[i]>=lower && sums[i]<=upper) res++;
            l = findLastSmaller(sorted,sums[i]-upper,len-1)+1;
            h = findLastSmaller(sorted,sums[i]-lower+1,len-1)+1;
            res += sum(ftree,h)-sum(ftree,l);
            update(ftree, findLastSmaller(sorted,sums[i]+1,len-1)+1,len+1);
        }
        return res;
    }
    private void update(int[] ftree, int i, int len){
        while(i<len){
            ftree[i]++;
            i+=(i&(-i));
        }
    }
    // i is real index in sorted array + 1
    private int sum(int[] ftree, int i){
        int res = 0;
        while(i>0){
            res += ftree[i];
            i-=(i&(-i));
        }
        return res;
    }
    // <
    //if no such element, return -1
    private int findLastSmaller(long[] a, long target,  int lenm1){
        int l = 0, r = lenm1;
        int mid = 0, comp = 0;
        while(l<r) {
            mid = l + (r-l+1)/2;
            comp = Long.compare(a[mid],target);
            if(comp>=0) r = mid-1;
            else l = mid;
        }
        return a[l]<target?l:-1;
    }
    public static void main(String[] args){
        CountOfRangeSum c = new CountOfRangeSum();
        int res = c.countRangeSum(new int[]{-2,5,-1},-2,2);
        res = c.countRangeSum(new int[]{Integer.MAX_VALUE,Integer.MIN_VALUE,-1,0},-1,0);
        res = c.countRangeSum(new int[]{Integer.MIN_VALUE+1,0,Integer.MIN_VALUE+1,Integer.MAX_VALUE},-1,3000);
        res = c.countRangeSum(new int[]{Integer.MIN_VALUE,0,Integer.MIN_VALUE,Integer.MAX_VALUE},-1,3000);
    }
}
