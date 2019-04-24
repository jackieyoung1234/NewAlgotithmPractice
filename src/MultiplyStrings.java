public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        if("0".equals(num1)||"0".equals(num2)) return "0";
        if(num2.length()>num1.length()){
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }
        //num1 .length is always larger or equal than num2
        String res = helper1(num1,num2.charAt(num2.length()-1));
        String powi = "0";
        for(int i2=num2.length()-2;i2>=0;i2--){
           String temp = helper1(num1,num2.charAt(i2));
           if(!"0".equals(temp)) {
               res = helper2(res, temp+powi);
           }
           powi += "0";
        }
        return res;
    }
    //String 1 mutiply a single
    private String helper1(String s1, char c){
       int ci = c - '0';
       if(ci==0) return "0";
       StringBuilder res = new StringBuilder();
       int carry=0, p=0;
       for(int i=s1.length()-1;i>=0;i--){
          int si = s1.charAt(i)-'0';
          p = si*ci+carry;
          carry = p/10;
          res.insert(0,p%10);
       }
       if(carry!=0) res.insert(0,carry);
       return res.toString();
    }
    private String helper2(String s1, String s2){
       if("0".equals(s1)) return s2;
       if("0".equals(s2)) return s1;
       int p1=s1.length()-1,p2=s2.length()-1;
       boolean tag = p1<p2;
       int carry = 0;
       int cur = 0;
       int n1 =0, n2 = 0;
       StringBuilder sb = new StringBuilder();
       while(p1>=0&&p2>=0){
             n1 = s1.charAt(p1--)-'0';
             n2 = s2.charAt(p2--)-'0';
             cur = n1+n2+carry;
             carry = cur/10;
             sb.insert(0,cur%10);
       }
       if(tag) {
           s1 = s2;
           p1 = p2;
       }
       //if(comp > 0){
       while(p1>=0){
         n1 = s1.charAt(p1--)-'0';
         cur = n1 + carry;
         carry = cur/10;
         sb.insert(0,cur%10);
       }
       //}else if(comp < 0){
       //   while(p2<s2.length()){
       //     n1 = s2.charAt(p2++)-'0';
       //     cur = n1 + carry;
       //     carry = cur/10;
       //     sb.insert(0,cur%10);
       //   }
       //}
       if(carry!=0)
            sb.insert(0,carry);
       return sb.toString();
    }
    public static void main(String[] args){
       MultiplyStrings mt = new MultiplyStrings();
       String testh2 = mt.helper2("99","1");
       testh2 = mt.helper2("1","99");
       String testh1 = mt.helper1("13",'4');
       String res = mt.multiply("123","456");
    }

}
