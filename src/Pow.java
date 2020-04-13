public class Pow {
    public double myPow(double x, int n) {
        boolean neg = n<0;
        long ln = (long)n;
        if(neg) ln=(-ln);
        int stindex = 31;
        long mark = (1L<<31);
        while(stindex>=0){
            if((ln&mark) != 0) break;
            mark = (mark>>1);
            stindex--;
        }
        double res = 1;
        while(stindex>=0){
            res*=res;
            if((ln&mark) != 0)
                res*=x;
            mark = (mark>>1);
            stindex--;
        }
        if(neg) return 1/res;
        return res;
    }
    private static byte[] longtoByteArray(long x){
        byte[] res =new byte[8];
       int i = 7;
       while(i>=0){
           res[i] = (byte)(x>>(8*i));
           i--;
       }
       return res;
    }
    public static void main(String[] args){
        long m1 = (1<<31);
        byte[] b1 = longtoByteArray(m1);
        m1 = (1L<<31);
        byte[] b2 = longtoByteArray(m1);
        long li = (long)Integer.MIN_VALUE;
        li = -li;
        boolean res = (li&1)==0;
        long resll = (li&1);
       Pow p = new Pow();
       double resd = p.myPow(2.0,Integer.MIN_VALUE);
    }
}
