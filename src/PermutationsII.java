import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
       List<List<Integer>> res = new LinkedList<>();
       ArrayList<Integer> left = new ArrayList<>(nums.length);
       for(int num:nums){
          left.add(num);
       }
       helper(res, new LinkedList<Integer>(), left);
       return res;
    }
    private void helper(List<List<Integer>> res, List<Integer> tonow, ArrayList<Integer> left){
        if(left.isEmpty()){
            res.add(new ArrayList<>(tonow));
            return;
        }
        Integer rem = null;
        for(int i=0;i<left.size();i++){
            rem = left.remove(i);
            tonow.add(rem);
            helper(res,tonow,left);
            left.add(i,rem);
        }
    }
    public static void main(String[] args){
       List<Integer> l = Arrays.asList(1,1);
       Integer i1 = l.get(0);
       Integer i2 = l.get(1);
       boolean ii = i1.equals(i2);
       boolean iii = i1==i2;
    }
}
