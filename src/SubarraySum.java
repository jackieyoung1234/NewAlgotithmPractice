import static org.junit.Assert.assertEquals;

public class SubarraySum {
    public int subarraySum(int[] arr){
       //i+1 * len-i
       //edge case: len = 0
       int res = 0, len = arr.length;
       for(int i = 0; i < len; i++){
          res += (i+1)*(len-i)*arr[i];
       }
       return res;
    }
    public static void main(String[] args ){
        SubarraySum s = new SubarraySum();
        assertEquals( s.subarraySum(new int[]{4,5}),18);
    }
}
