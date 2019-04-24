import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaximumDiffInAnArray {
    public int maxDifference(int[] arr){
        int min2now = arr[0], res = -1;
        for(int i=1;i<arr.length;i++){
           res = Math.max(res,arr[i]-min2now);
           min2now = Math.min(min2now,arr[i]);
        }
        return res;
    }
    public static void main(String[] args){
       MaximumDiffInAnArray m = new MaximumDiffInAnArray();
       assertEquals(m.maxDifference(new int[]{1,2,6,4}),5);
    }
}
