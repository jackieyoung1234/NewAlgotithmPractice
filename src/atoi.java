import java.util.ArrayList;
import java.util.Arrays;

public class atoi {
    public int myAtoi(String str){
        String filtered = str.replaceAll("^\\s+|[^0-9]+$","");
        int i = 0,cnt = 0, result = 0, length = filtered.length();
        boolean neg = false;
        int temp = 0;
        if(filtered.charAt(0) == '+'
              ||
           filtered.charAt(0) == '-'){
            if(filtered.charAt(0) == '-')
                neg = true;
            i++;
        }
        for(;i < length; i++){
            temp = filtered.charAt(i) - '0';
            if(temp > 9 || temp < 0)
                break;
            temp = neg? result * 10 - temp:result * 10 + temp;
            if(result != temp/10)
                return neg?Integer.MIN_VALUE:Integer.MAX_VALUE;
            result = temp;
            cnt++;
        }
        if(cnt == 0)
            return 0;
        return result;
    }
    public static void main(String [] args){
       String tt = "   adf90b0dsa &*()  ".replaceAll("^\\s+|[^0-9]+$","");
       atoi myA = new atoi();
       int ti = 0;
       ti = myA.myAtoi("       +899ADS");
       ti = myA.myAtoi("       899ADS");
       ti = myA.myAtoi("      -899ADS");
       ti = myA.myAtoi("      -899ADS23");
       ti = myA.myAtoi("      -2147483648");
        ti = myA.myAtoi("      -2147483648");
        ti = myA.myAtoi("");
        ArrayList<Integer>  l1 = new ArrayList<>(Arrays.asList(1,2,3));
        ArrayList<Integer>  l2 = new ArrayList<>(Arrays.asList(4,5,6));
        String tt2 = "o";

    }
}
