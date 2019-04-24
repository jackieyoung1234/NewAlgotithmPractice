import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CountOfSmallerThanItself {
    private class Ftree{
        int len;
        int[] a;
        public Ftree(int len){
            this.len = len;
            a = new int[len];
        }
        public int presum(int i){
            int res = 0;
            while(i>0){
                res += a[i];
                i -= (i&(-i));
            }
            return res;
        }
        public void update(int i){
            while(i < len){
                a[i]++;
                i += (i&(-i));
            }
        }
    }
    private int helper(int[] a, int target, int lo, int hi){
        int mid = 0, comp = 0;
        while(lo<hi){
            mid = lo + (hi-lo+1)/2;
            comp = Integer.compare(a[mid],target);
            if(comp>=0) hi = mid-1;
            else lo = mid;
        }
        return a[lo]<target?lo:-1;
    }
    public List<Integer> countSmaller(int[] nums){
        if(nums==null || nums.length==0) return new ArrayList<Integer>();
        int len = nums.length;
        List<Integer> res = new ArrayList<>(len);
        Ftree f = new Ftree(len+1);//index 0 is never used
        int[] sort = Arrays.copyOf(nums,len);
        Arrays.sort(sort);
        for(int i=len-1;i>=0;i--){
            //52261
            //index 31240
            //ftree.a.len = 6
            //31110
            // 1 2 3 4 5
            // 1 0 0 0 1
            //find last index i, [i]<target, if no such i, return -1
            int index = helper(sort,nums[i],0,len-1)+1;
            res.add(0,f.presum(index));
            f.update(index+1);//add 1
        }
        return res;
    }
    public static void main(String[] args){
        List<Integer> res = new CountOfSmallerThanItself().countSmaller(
                new int[]{5,2,6,1}
        );
    }

}
