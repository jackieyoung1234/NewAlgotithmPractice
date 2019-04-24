import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class pertumate {
    public List<List<Integer>> permute(int[] nums) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> toNow = new ArrayList<>();
        int len = nums.length;
        if(len != 0)
            helper(new ArrayList(Arrays.stream(nums).boxed().collect(Collectors.toList())),toNow,res,len);
        return res;

    }
    //corner cases for empty nums
    private void helper(ArrayList<Integer> n, ArrayList<Integer> toNow, ArrayList<List<Integer>> res,int len){
        if(toNow.size() == len) {
            res.add(new ArrayList(toNow));
        }
        else{
            int size = n.size();
            for(int i=0;i<size;i++){
                toNow.add(n.remove(i));
                helper(new ArrayList(n),toNow,res,len);
                n.add(i,toNow.remove(toNow.size()-1));
            }
        }
    }
    public static void main(String[] args){
        Integer[] y = new Integer[5];
        ArrayList<Integer> yy = new ArrayList(5);
        for(int tt : yy){
           tt = new Integer(1);
        }
        ArrayList<Integer> yyCopy =(ArrayList<Integer>)  yy.clone();
        yy.set(1,2);
        ArrayList<Integer> test = new ArrayList<>(Arrays.asList(new Integer[]{1,2,3}));
        List<List<Integer>> res = new pertumate().permute(new int[]{1,2,3});
    }
}
