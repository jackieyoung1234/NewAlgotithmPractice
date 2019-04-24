import java.util.ArrayList;
import java.util.List;

public class minimumTotal {
    public int minimumTotal(List<List<Integer>> triangle) {
        //todo:corner case null
        int len = triangle.size();
        if(len==0) return 0;
        int[] bkp = new int[len];
        for(int level=1;level<=len;level++){
            int[] bk = new int[len];
            for(int i=0;i<level;i++){
                if(i==0)
                    bk[i] = bkp[i]+triangle.get(level-1).get(i);
                else if(i==level-1)
                    bk[i] = bkp[i-1] + triangle.get(level-1).get(i);
                else
                    bk[i] = Math.min(bkp[i],bkp[i-1]) + triangle.get(level-1).get(i);
            }
            bkp = bk;
        }
        for(int i=1;i<len;i++){
            bkp[i] = Math.min(bkp[i-1],bkp[i]);
        }
        return bkp[len-1];
    }
    public static void main(String[] args){
       ArrayList<Integer> a0 = new ArrayList<>();
       ArrayList<Integer> a1 = new ArrayList<>();
       ArrayList<Integer> a2 = new ArrayList<>();
       ArrayList<Integer> a3 = new ArrayList<>();
       a0.add(2);
       a1.add(3);
       a1.add(4);
       a2.add(6);
       a2.add(5);
       a2.add(7);
       a3.add(4);
       a3.add(1);
       a3.add(8);
       a3.add(3);
       List<List<Integer>> res = new ArrayList<>();
       res.add(a0);
        res.add(a1);
        res.add(a2);
        res.add(a3);
       int rr = new minimumTotal().minimumTotal(res);
       int rrr = Character.toLowerCase('.');
    }
}
