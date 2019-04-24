import java.util.Arrays;

public class plusOne {
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        int[] res = new int[len+1];
        int carry = 1;
        int i = len-1;
        while(i>=0){
            int temp = digits[i]+carry;
            res[(i--)+1] = (temp)%10;
            carry = (temp>=10)?1:0;
        }
        if(carry == 0)return Arrays.copyOfRange(digits,1,len+1);
        res[0] = 1;
        return res;
    }
    public static void main(String[] args){
        int[] res = new plusOne().plusOne(new int[]{1,2,3});
    }
}
