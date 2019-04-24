public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        long res;
        long div = (long) dividend, dir = (long)divisor;
        if(div==0) return 0;
        res = helper(Math.abs(div),Math.abs(dir));
        if(div*dir<0){
            res = -res;
        }
        if(res>Integer.MAX_VALUE) return Integer.MAX_VALUE;
        return (int)res;
    }
    private long helper(long div, long dir){
        long res = 0;
        int shift = 0;
        long dir_backup = dir;
        while(true){
            if(div<dir) break;
            while(div>= dir){
                dir <<= 1;
                shift++;
            }
            res += ((long)1<<(--shift));
            div -= (dir>>1);
            shift=0;
            dir = dir_backup;
        }
        return res;
    }
    public static void main(String[] args){
       int rs = new DivideTwoIntegers().divide(Integer.MIN_VALUE,-1);
    }
}
