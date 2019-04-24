import java.util.Arrays;

public class threeSumClosest {
     public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = Integer.MAX_VALUE;
        int returnV = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length - 2; i ++){
            int sum = target - nums[i];
            int lo = i + 1, hi = nums.length - 1;
            while(lo < hi){
                int ad = nums[lo]+nums[hi] - sum;
                if(ad == 0) return target;
                else if(ad < 0) {
                    if ((-ad) < result) {
                        result = (-ad);
                        returnV = ad + sum + nums[i];
                    }
                    while(nums[lo++] == nums[lo] && lo < hi);
                }else{//ad > 0
                    if(ad < result) {
                        result = ad;
                        returnV = ad + sum + nums[i];
                    }
                    while(nums[hi--] == nums[hi] && lo < hi);
                }
            }
        }
        return returnV;
    }
    public static void main(String [] args){
        int result = new threeSumClosest().threeSumClosest(new int[]{0,0,0},1);
        result = new threeSumClosest().threeSumClosest(new int[]{1,1,-1},0);
        result = 0;
    }
}
