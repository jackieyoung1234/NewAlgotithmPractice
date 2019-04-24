import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class combinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
       ArrayList<List<Integer>> res = new ArrayList<>();
       ArrayList<Integer> toNow = new ArrayList<>();
       Arrays.sort(candidates) ;
       helper(candidates,target,res,toNow);
       return res;
    }
    private void helper(int[] c, int t, ArrayList<List<Integer>> res, ArrayList<Integer>toNow){
        int len = c.length-1;
        if(t == 0)
            if(toNow != null){
                res.add(new ArrayList(toNow));
                return;
            }
        if(len == -1 || t < 0) return;
        if(c[len]<=t) {
            toNow.add(c[len]);
            helper(Arrays.copyOfRange(c,0,len),t-c[len],res,toNow);
            toNow.remove(toNow.size()-1);
        }
        helper(Arrays.copyOfRange(c,0,len),t,res,toNow);
    }
    public static void main(String[] args){
       List<List<Integer>> re = new combinationSum2().combinationSum2(new int[]{2,1,7,10,6,1,5},8);
    }
}
