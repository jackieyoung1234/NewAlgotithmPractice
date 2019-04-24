import java.util.ArrayList;
import java.util.List;

public class permute {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        for(int i : nums){
            int j = res.size();
            if(j == 0){
                ArrayList<Integer> aL1 = new ArrayList<Integer>();
                aL1.add(i);
                res.add(aL1);
            }
            else {
                List<List<Integer>> res2 = new ArrayList<>();
                for(List<Integer> temp : res){
                    for(int t = 0; t<=j ; t++){
                        ArrayList<Integer> temp1 = new ArrayList(temp);
                        temp1.add(t,i);
                        res2.add(temp1);
                    }
                }
                res = res2;
            }
        }
        return res;
    }
   public List<List<Integer>> permute_version1(int[] nums){
   ArrayList<List<Integer>> res = new ArrayList<>();
       ArrayList<Integer> toNow = new ArrayList<>();
       int len = nums.length;
       if(len != 0)
        helper(nums,toNow,res,len);
       return res;
    }
    //corner cases for empty nums
    private void helper(int[] n, ArrayList<Integer> toNow, ArrayList<List<Integer>> res,int len){
       if(toNow.size() == len) {
            res.add(new ArrayList(toNow));
       }
       else{
           for(int t : n){
               if(toNow.contains(t)) continue;
               toNow.add(t);
               helper(n,toNow,res,len);
               toNow.remove(toNow.size()-1);
           }
       }
   }
   public static void main(String[] args){
        List<List<Integer>> res= new permute().permute(new int[]{1,2,3});
        res= new permute().permute(new int[]{5,4,6,2});
   }
}
