import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;

public class ExpressionAddOperators {
    private final char[] options= new char[]{
            '+',
            '-',
            '*'
    };
    public List<String> addOperators(String num, int target) {
        LinkedList<String> res = new LinkedList<>();
        helper(new StringBuilder(num),target,1,0,num.length()-1,res);
        return res;
    }
    //itercnt is level, for s len = len , len-1 level available
    private void helper(StringBuilder toNow,int target, int itercnt, int added, int slenm1, List<String> res){
        if(itercnt>slenm1){
            if(eval(toNow.toString())==target)
                res.add(toNow.toString());
            return;
        }
        int insertpos = itercnt+added;
        //plus no insert
        helper(toNow,target,itercnt+1,added,slenm1,res);
        for(char op : options){
            helper(toNow.insert(insertpos,op),target,itercnt+1,added+1,slenm1,res);
            toNow.deleteCharAt(insertpos);
        }
    }
    private int eval(String s){
        int sum = 0,n=0,sym=1;
        char zero = '0',nine = '9';
        int stack = 0;
        boolean skemp  = true;
        for(char c: s.toCharArray()){
            if(c>=zero&&c<=nine){
                n*=10;
                n+=(c-zero);
            }else if(c=='*'){
                if(skemp){
                    stack = n;
                    skemp = false;
                }else{
                    stack *= n;
                }
                n=0;
            }else{// if(c=='+'||'-'){
                if(!skemp){
                    n *= stack;
                    skemp = true;
                }
                sum+= sym*n;
                n = 0;
                sym = c=='+'?1:-1;
            }
            //}else{//(c=='-'){
            //   if(!skemp){
            //      n *= stack;
            //      skemp = true;
            //   }
            //   sum+= sym*n;
            //   n = 0;
            //   sym = -1;
            //}
        }
        if(!skemp){
            n *= stack;
        }
        return sum+ sym*n;
    }
    public static void main(String[] args){
        long i = 0;
        int ii = 0;
        boolean b = i == ii;
        ExpressionAddOperators e = new ExpressionAddOperators();
        List<String> res = e.addOperators("123",6);
    }
}
