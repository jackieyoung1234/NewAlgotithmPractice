import java.util.ArrayList;
import java.util.List;

public class subSets {
    int[] nums;
    int len = 0;
    public List<List<Integer>> subsets(int[] nums) {
        this.nums = nums;
        this.len = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        helper(result,new ArrayList<>(),0);
        return result;
    }
    private void helper(List<List<Integer>> result, List<Integer> up2Now, int index){
        result.add(new ArrayList(up2Now));
        int siz = up2Now.size();
        for(int i = index;i<len;i++){
            up2Now.add(nums[i]);
            helper(result,up2Now,i+1);
            up2Now.remove(siz);
        }
    }
    public static void main(String[] args){
        List<List<Integer>> res = new subSets().subsets(new int[]{1,2,3});
        Integer i = new Integer(1);
        i++;
        i++;
        i = 7;
        boolean tt = 'b' > 'a';
        int ik = 'i';
    }
}
