import java.util.Stack;

public class BasicCalculatorII {
    public int calculate(String s) {
        //* -- 2  / -- -2
        Stack<Integer> sk = new Stack<>();
        int sum = 0, n = 0, sym = 1;
        char zero = '0', nine = '9';
        int op = 0;
        for(char c : s.toCharArray()){
            //num
            if(c>=zero && c <= nine){
                n*=10;
                n+=(c-zero);
            }
            //+ or -
            else if(c=='+'||c=='-') {
                if(!sk.isEmpty()){
                    if(sk.pop()=='2'){
                       n*=sk.pop();
                    }else{
                       n = sk.pop()/n;
                    }
                }
                sum+=(sym*n);
                sym = (c=='+')?1:-1;
                n=0;
            }
            //* or /
            else if(c=='*'||c=='/'){
                if(!sk.isEmpty()) {
                    if(sk.pop()=='2'){
                       n*=sk.pop();
                    }else{
                       n = sk.pop()/n;
                    }
                }
                sk.push(n);
                sk.push(c == '*' ? 2 : -2);
                n=0;
            }
        }
        if(!sk.isEmpty()){
            if(sk.pop()=='2'){
                n*=sk.pop();
            }else{
                n = sk.pop()/n;
            }
        }
        return sum+(sym*n);
    }
    public static void main(String[] args){
        BasicCalculatorII bc = new BasicCalculatorII();
        int res = bc.calculate("1-8/4/2");
        res = bc.calculate("0*0");
        res = bc.calculate("100000000/1/2/3/4/5/6/7/8/9/10");
    }
}
