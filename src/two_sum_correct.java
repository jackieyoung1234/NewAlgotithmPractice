import java.util.HashMap;
import java.util.Map;

public class two_sum_correct {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> myM = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            myM.put(nums[i], i);
            if (myM.containsKey(target - nums[i])) {
                int another = myM.get(target - nums[i]);
                if (another != i)
                    return new int[]{myM.get(target - nums[i]),i};
            }
        }
            return null;
    }
    public static void main (String[]args){
        int[] ss = new two_sum_correct().twoSum(new int[]{3, 19, 29, 54, 1, 22, 12, 4, 8}, 13);
        ss = new two_sum_correct().twoSum(new int[]{3, 2, 4}, 6);
        int i = 0;
        if ((i = 2) != -1)
            System.out.println((i = 2));
        i = 3;
    }
}
