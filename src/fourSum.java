import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class fourSum {
    public List<List<Integer>> fourSumOld(int[] nums, int target){
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < nums.length - 3; i++){
            if (nums[i] != nums[i - 1] || i == 0) {
                for (int j = i + 1; j < nums.length - 2; j++) {
                    if (nums[j] != nums[j - 1] || j == 1) {
                        int lo = j + 1, hi = nums.length - 1, innerTarget = target - nums[j] - nums[i];
                        while (lo < hi) {
                            if (nums[lo] + nums[hi] < innerTarget)
                                while (nums[lo++] == nums[lo] && lo < hi) ;
                            else if (nums[lo] + nums[hi] > innerTarget)
                                while (nums[hi--] == nums[hi] && lo < hi) ;
                            else {
                                res.add(Arrays.asList(new Integer[]{nums[i], nums[j], nums[lo], nums[hi]}));
                                while (nums[lo++] == nums[lo] && lo < hi) ;
                                while (nums[hi--] == nums[hi] && lo < hi) ;
                            }
                        }
                    }
                }
            }
        }
        return res;
    }
    public List<List<Integer>> fourSum(int[] nums, int target){
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length < 4)
            return res;
        Arrays.sort(nums);
        int max = nums[nums.length-1];
        if(4*nums[0] > target || 4 * max < target)
            return res;
        for(int i = 0; i < nums.length - 3; i++){
            int numsi = nums[i];
            if(i > 0 && numsi == nums[i-1])
                continue;
            if(numsi + 3*max < target)
                continue;
            if(4 * numsi > target)
                break;
            if(4 * numsi == target)
                if( i < nums.length-3 && nums[i+3]==numsi){
                    res.add(Arrays.asList(new Integer[]{numsi, numsi, numsi, numsi}));
                    break;
                }
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j == i+1 || nums[j] != nums[j - 1]) {
                    int lo = j + 1, hi = nums.length - 1, innerTarget = target - nums[j] - numsi;
                    while (lo < hi) {
                        if (nums[lo] + nums[hi] < innerTarget)
                            while (nums[lo++] == nums[lo] && lo < hi) ;
                        else if (nums[lo] + nums[hi] > innerTarget)
                            while (nums[hi--] == nums[hi] && lo < hi) ;
                        else {
                            res.add(Arrays.asList(new Integer[]{numsi, nums[j], nums[lo], nums[hi]}));
                            while (nums[lo++] == nums[lo] && lo < hi) ;
                            while (nums[hi--] == nums[hi] && lo < hi) ;
                        }
                    }
                }
            }
        }
        return res;
    }
    public static void main(String [] args){
       List<List<Integer>> res = new fourSum().fourSum(new int[]{1,0,-1,0,-2,2},0);
       int i = 0;
    }
}
