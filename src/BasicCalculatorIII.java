import java.util.Arrays;

public class BasicCalculatorIII {
    public int calculate(String s) {
        int[] info = new int[2];
        helper(s,info);
        return info[1];
    }
    public void helper(String s, int[] info){
        char op = '+';
        int res = 0,last = 0, cur = 0;
        for(;info[0]<s.length();info[0]++){
            char c = s.charAt(info[0]);
            if(c==' ') continue;
            else if(c>='0'&&c<='9'){
                cur = c-'0';
                while(info[0]<s.length()-1&&s.charAt(info[0]+1)>='0'&&s.charAt(info[0]+1)<='9'){
                    cur = cur*10+(s.charAt(++info[0])-'0');
                }
            }else if(c=='('){
                info[0]++;
                helper(s,info);
                cur = info[1];
            }else if(c==')'){
                //info[0]++;
                break;
            }else{//+-*/
                op = c;
                continue;
            }

            //update
            if(op=='+'){
                res += last;
                last = cur;
            }else if(op=='-'){
                res += last;
                last = -cur;
            }else if(op=='*'){
                last *= cur;
            }else{
                last /= cur;
            }
        }
        info[1]= res+last;
    }
    //int[2]  [0] curindex, [1] for return res
    private void helper2(String s, int[] info){
        char cc = '\u0000';
        int cur = 0, sign = 1;//+/-
        int n1 = 0, sign2 = 0;//1--*,  -1--/
        int prev = 0;
        for(;info[0]<s.length();info[0]++){
            cc = s.charAt(info[0]);
            if(cc==' ') continue;
            if(cc>='0'&&cc<='9'){
                cur = cur*10+cc-'0';
            }else if(cc=='+'||cc=='-'){
                if(sign2!=0){
                    cur = sign2==1?n1*cur:n1/cur;
                    n1 =0;
                    //prev += cur*sign;
                    //sign = cc=='+'?1:-1;
                }
                prev += cur*sign;
                sign = cc=='+'?1:-1;
                sign2 = 0;
                cur = 0;
            }else if(cc=='*'||cc=='/'){
                if(sign2==0){
                    n1 = cur;
                    //cur = 0;
                    //sign2 = cc=='*'?1:-1;
                }else{
                    n1 = sign2==1?n1*cur:n1/cur;
                    //cur =0;
                    //sign2 = cc=='*'?1:-1;
                }
                cur =0;
                sign2 = cc=='*'?1:-1;
            }
            else if(cc=='('){
                info[0]++;
                helper(s,info);
                cur = info[1];
            }else{//cc ==')'
                //info[0]++;
                break;
            }
        }
        if(sign2!=0){
            cur = sign2==1?n1*cur:n1/cur;
        }
        info[1] = prev+sign*cur;
    }
    public static void main(String[] args){
       int[] a = new int[]{2,1,3};
        Integer[] aa = new Integer[]{0,1,2};
        Arrays.sort(aa,(i1,i2)-> Integer.compare(a[i1],a[i2]));
       int res = new BasicCalculatorIII().calculate("(15*(1+3*15/3/5)-2*1)");
    }
}
