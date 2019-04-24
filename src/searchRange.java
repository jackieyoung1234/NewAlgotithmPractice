public class searchRange {
    private int bS(int[] n, int s, int e, int t){
        while(s<=e) {
            int mid = s + (e-s)/2;
            int c = Integer.compare(n[mid],t);
            if(c == 0) return mid;
            else if(c<0) s = mid + 1;
            else e = mid - 1;
        }
        return -1;
    }
    public int[] searchRange_Version1(int[] nums, int target) {
        int lenM1 = nums.length-1;
        int i = bS(nums,0,lenM1,target);
        if(i == -1)  return new int[]{-1,-1};
        int l = i, r = i;
        while(l-1>=0 && nums[l-1] == target) l--;
        while(r+1<= lenM1 && nums[r+1] == target) r++;
        return new int[]{l,r};
    }
    public int[] searchRange(int[] nums, int target) {
        if(nums.length == 0) return new int[]{-1,-1};
        return helper(nums, target, 0, nums.length - 1);
    }
    private int[] helper(int[] nums, int target, int lo, int hi) {
    if (nums[lo] == target && nums[hi] == target) return new int[]{lo, hi};
    if (nums[lo] <= target && nums[hi] >= target) {
        int mid = lo + (hi - lo) / 2;
        int[] left = helper(nums, target, lo, mid);
        int[] right = helper(nums, target, mid + 1, hi);
        if (left[0] == -1) return right;
        if (right[0] == -1) return left;
        return new int[]{left[0], right[1]};
    }
    return new int[]{-1, -1};
    }
}
