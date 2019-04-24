public class CanIWin {
    public boolean canIWin(int mc, int de) {
        if(de==0)return true;
        if(mc==0)return false;
        if((mc+1)*mc<2*de) return false;
        boolean[] set = new boolean[mc+1];
        return helper(set,mc,de);
    }
    //edge case (mc+1)*mc/2>desire
    private boolean helper(boolean[] set,int mc, int left){
        if(left<=0) return false;
        int max_available = 0;
        for(int i=mc;i>=1;i--){
            if(!set[i]){
                max_available = i;
                break;
            }
        }
        if(left<=max_available) return true;
        for(int i=max_available;i>=1;i--){
            if(!set[i]){
                set[i] = true;
                if(!helper(set,mc,left-i))
                    return true;
                set[i] =false;
            }
        }
        return false;
    }
    public static void main(String[] args){
        int r = (int)Math.ceil(-2/(double)3);
        r = (int)Math.ceil(-4/(double)3);
        boolean res = new CanIWin().canIWin(10,40);
        res = new CanIWin().canIWin(10,11);
        res = new CanIWin().canIWin(10,12);
    }
}
