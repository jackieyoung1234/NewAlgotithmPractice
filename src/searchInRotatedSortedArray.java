public class searchInRotatedSortedArray {
    private int bS(int[] n, int s, int e,int t){
        while(e>=s){
            int mid = s + (e-s)/2;
            if(n[mid]==t) return mid;
            else if(n[mid]>t)
                e = mid-1;
            else
                s = mid + 1;
        }
        return -1;
    }
    public int search_SlowVersion(int[] nums, int target) {
        int len = nums.length;
        if(len == 0) return -1;
        if(nums[0] > target && nums[len -1] < target) return -1;
        int i = 0;
        while( nums[i] != target && i < len - 1 && nums[i+1] > nums[i])i++;
        if(nums[i] == target) return i;
        if( i == len-1 ) {
            return -1;
        }
        //modify here
        if(nums[i]<target || nums[i+1]>target) return -1;
        return bS(nums,i+1,len-1,target);
    }
    private int bSI(int[] n, int s, int e,int t){
        while(e>=s){
            int mid = s+ (e-s)/2;
            if(n[mid]<t) {
                if(n[mid-1]>=t)
                    return mid;
                e=mid-1;
            }
            else{
                s = mid + 1;
            }
        }
        return -1;
    }
    public int search(int[] nums, int target) {
        int len = nums.length;
        if(len == 0) return -1;
        if(len == 1) return nums[0] == target?0:-1;
        if(nums[0] > target && nums[len -1] < target) return -1;
        int i = bSI(nums,0,len-1,nums[0]);
        if(i == -1) return bS(nums,0,len-1,target);
        if(target > nums[len-1]) return bS(nums,0,i-1,target);
        else return bS(nums,i,len-1,target);

    }
    public static void main(String[] args){
        int i = new searchInRotatedSortedArray().search(new int[]{3,1},1);
    }
}
