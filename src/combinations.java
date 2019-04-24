import java.util.ArrayList;
import java.util.List;

public class combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        ArrayList<Integer> up2 = new ArrayList<>();
        helper(1,n,k,result,up2);
        return result;
    }
    private void helper(int intNow,int n, int k,List<List<Integer>> result, ArrayList<Integer>up2Now){
        if(up2Now.size()==k){
            result.add(new ArrayList<Integer>(up2Now));
            return;
        };
        if(intNow>n) return;
        ArrayList<Integer> new2Now = new ArrayList<>(up2Now);
        new2Now.add(intNow);
        helper(intNow+1,n,k,result,new2Now);//take
        helper(intNow+1,n,k,result,up2Now);//take
    }
}
