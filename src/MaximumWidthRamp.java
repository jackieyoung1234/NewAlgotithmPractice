import java.util.Arrays;
import java.util.Comparator;

public class MaximumWidthRamp {
    public int maxWidthRamp(int[] A) {
        int len = A.length;
        int res = 0, min2n=len;
        Integer[] indexA = new Integer[len];
        for(int i=0;i<len;i++){
            indexA[i] = i;
        }
        Arrays.sort(
                indexA,
                (a1, a2)->{
                    int comp = Integer.compare(A[a1],A[a2]);
                    if(comp!=0) return comp;
                    return Integer.compare(a1,a2);
                }
        );
        Integer[] a = new Integer[]{1,2,3,4};
        Arrays.sort(
                a,
                new Comparator<Integer>(){
                   @Override
                   public int compare(Integer i1, Integer i2){
                      return Integer.compare(i1,i2);
                   }
                }

        );
        for(int i : indexA){
            if(i>min2n) res = Math.max(res,i-min2n);
            if(i<min2n) min2n = i;
        }
        return res;
    }
}
