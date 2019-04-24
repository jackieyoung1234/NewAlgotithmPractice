import java.util.Random;

public class FindKthLargest {
    private Random rand;
    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        //k is always valid
        rand = new Random();
        int lo = 0,hi = len-1;
        int targetIndex = k-1;
        while(lo<hi){
            int j = partition(nums,lo,hi);
            int comp = Integer.compare(j,targetIndex);
            if(comp==0) break;
            else if(comp>0) hi = j-1;
                // j < target Index
            else lo = j+1;
        }
        return nums[targetIndex];
    }
    private void shuffle(int[] arr, int lo,int hi){
        int rin = rand.nextInt(hi-lo+1)+lo;
        exch(arr,lo,rin);
    }
    // partition assumes lo < hi, i.e., need to check this boundary condition before call partition
    private int partition(int[] arr, int lo, int hi){
        shuffle(arr,lo,hi);
        int target= arr[lo];
        int i = lo, j = hi+1;
        while(true){
            while(arr[++i]>target){
                if(i==hi) break;
            }
            while(arr[--j]<target){
                if(j==lo) break;
            }
            if(i>=j){
                break;
            }
            exch(arr,i,j);
        }
        exch(arr,lo,j);
        return j;
    }
    private void exch(int[] a, int i, int j){
        int temp = a[i];
        a[i]=a[j];
        a[j]=temp;
    }
    public static void main(String[] args){
       FindKthLargest fkl = new FindKthLargest();
       int res = fkl.findKthLargest(new int[]{-1,-1},2);
       res = fkl.findKthLargest(new int[]{2,1},1);
       res = fkl.findKthLargest(new int[]{1},1);
       char i = "abs".charAt(0);
    }
}
