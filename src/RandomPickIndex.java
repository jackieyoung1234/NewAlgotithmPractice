import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class RandomPickIndex {
    Random rand;
    HashMap<Integer, ArrayList<Integer>> bk;
    public RandomPickIndex(int[] nums) {
        rand = new Random(new Date().getTime());
        bk = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            bk.computeIfAbsent(nums[i], indexList -> new ArrayList()).add(i);
        }
    }

    public int pick(int target) {
        ArrayList<Integer> indexList = bk.get(target);
        return indexList.get(rand.nextInt(indexList.size()));
    }
    public static void main(String[] args){
       RandomPickIndex rpi = new RandomPickIndex(new int[]{1,2,3,3,3});
       HashMap<Integer,Integer> hm = new HashMap<>();
       hm.computeIfAbsent(1,key->key+3);
       hm.computeIfAbsent(2,key->key+3);
       hm.computeIfPresent(2, (key,val)->{
           return key*10;
       });
    }
}
