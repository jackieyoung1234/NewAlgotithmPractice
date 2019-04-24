import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class letterCombinations {
    public List<String> letterCombinations(String digits) {
        char[] dArr = digits.toCharArray();
        String[] mem = {" ","1","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        List<String> res = new ArrayList<>();
        for(int i = 0; i < digits.length() ; i++){
            char[] temp = mem[Character.getNumericValue(dArr[i])].toCharArray();
            int cnt = res.size();
            while(cnt-- > 0) {
                String tempString = res.remove(cnt);
                for (char tempChar : temp)
                    res.add(tempString + tempChar);
            }
            if(i == 0){
                for(char tempChar: temp)
                    res.add("" + tempChar);
            }
        }

        return res;
    }

    public static void main(String [] args){
        List<String> res = new letterCombinations().letterCombinations("22");
        res = new letterCombinations().letterCombinations("23");
        res = new letterCombinations().letterCombinations("234");
    }
}
