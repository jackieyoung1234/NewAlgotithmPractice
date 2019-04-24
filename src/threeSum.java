import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static sort.topBottomMergeSort.mergeSortUB;

public class threeSum {
    private List<Integer[]> twoSumSearch(int[] target, int sum, int stIndex){
        List<Integer[]> result = new ArrayList<>();
        int lastI = 0;
        for(int i = stIndex+1; i < target.length; i ++){
            if(i == stIndex+1 || target[i] != lastI) {
                int temp = binarySearch(target,sum - target[i],i + 1,target.length-1);
                if (temp != -1) result.add(new Integer[]{target[stIndex], target[i], target[temp]});
                lastI = target[i];
            }
        }
        return result;
    }
    private int binarySearch(int[] target, int searchNumber, int lo, int hi){
        while(lo <= hi) {
            int mid = (hi - lo)/2 + lo;
            int comp = Integer.compare(searchNumber, target[mid]);
            if (comp == 0) return mid;
            else if (comp < 0)
                hi = mid-1;
            else
                lo = mid + 1;
        }
        return -1;
    }

    private int[] filter0(int [] nums,List<List<Integer>> resultL){
        int[] result = new int[]{-1,-1};
        boolean tag = true;
        boolean tag2 = true;
        for(int i = 0; i < nums.length; i++){
            if(tag2) {
                if (nums[i] == 0) {
                    if (tag) {
                        result[0] = i;
                        result[1] = i;
                        tag = false;
                    }
                    else
                        result[1] = i;
                }
                else if(!tag){
                    tag2 = false;
                }
            }
        }
        if(result[0] == -1) return nums;
        else{
            if(result[1]-result[0]>=2)
                resultL.add(Arrays.asList(new Integer[]{0,0,0}));
            int[] numsNew = new int[nums.length  + result[0] - result[1]];
            System.arraycopy(nums,0,numsNew,0,result[0]+1);
            System.arraycopy(nums,result[1]+1,numsNew, result[0] + 1,nums.length - result[1]-1);
            return numsNew;
        }

    }
    public List<List<Integer>> threeSum1stVersion(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length < 3 || nums == null) return result;
        mergeSortUB(nums);
        nums = filter0(nums,result);
        int tempI = 0;
        for(int i = 0; i < nums.length - 2; i ++){
            if(i == 0 || (nums[i]!=tempI)) {
                List<Integer[]> t = twoSumSearch(nums, -nums[i], i);
                if (t != null)
                    for (Integer[] temp : t)
                        result.add(Arrays.asList(temp));
            }
            tempI = nums[i];
        }
        return result;
    }
    public List<List<Integer>> threeSum2ndversion(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length < 3 || nums == null) return result;
        Arrays.sort(nums);
        nums = filter0(nums,result);
        int tempI = 0;
        for(int i = 0; i < nums.length - 2; i ++){
            if(i == 0 || (nums[i]!=tempI)) {
                List<Integer[]> t = twoSumSearch(nums, -nums[i], i);
                if (t != null)
                    for (Integer[] temp : t)
                        result.add(Arrays.asList(temp));
            }
            tempI = nums[i];
        }
        return result;
    }
    public List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length < 3 || nums == null) return result;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 2; i ++){
            if(i == 0 || (nums[i] != nums[i-1])) {
                int lo = i+1, hi = nums.length -1, sum = -nums[i];
                while(lo < hi){
                    if(nums[lo] + nums[hi] < sum) lo++;
                    else if(nums[lo] + nums[hi] > sum) hi--;
                    else{
                       result.add(Arrays.asList(new Integer[]{nums[i],nums[lo],nums[hi]}));
                       while(nums[lo++] == nums[lo] && lo < hi);
                       while(nums[hi--] == nums[hi] && lo < hi);
                    }
                }
            }
        }
        return result;
    }
    public static void main(String[] args){
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result = new threeSum().threeSum(new int[]{-1,0,1,2,-1,-4});
        result = new threeSum().threeSum(new int[]{0,0,0,0});
        result = new threeSum().threeSum(new int[]{-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6});
        result = new threeSum().threeSum(new int[]{-1,0,1});
        int i = 0;
    }
}
