import Utilities.Timer;

public class LeastOperatorsToExpressNumber {

    public int leastOpsExpressTarget2(int x, int target) {
        int mod = 0, p = 0 ,nextp = 0, n = 0, nextn = 0, cnt = 0;
        int cntMod = 0, cntX = 0;
        while(target>0){
            mod = (target%x);
            target /= x;
            if(cnt==0){
                p = mod*2;
                n = (x-mod)*2;
            }else{
                cntMod = cnt*mod;
                cntX = cnt*x;
                nextp = Math.min(
                        cntMod+p,
                        cntMod+cnt+n
                );
                nextn = Math.min(
                        cntX-cntMod+p,
                        cntX-cntMod-cnt+n
                );
                p = nextp;
                n = nextn;
            }
            cnt++;
        }
        return Math.min(cnt+n,p)-1;
    }
    public int leastOpsExpressTarget(int x, int target) {
        int mod = 0, p = 0 ,nextp = 0, n = 0, nextn = 0, cnt = 0;
        while(target>0){
            mod = (target%x);
            target /= x;
            if(cnt==0){
                p = mod*2;
                n = (x-mod)*2;
            }else{
                nextp = Math.min(
                        cnt*mod+p,
                        cnt*(mod+1)+n
                );
                nextn = Math.min(
                        cnt*(x-mod)+p,
                        cnt*(x-mod-1)+n
                );
                p = nextp;
                n = nextn;
            }
            cnt++;
        }
        return Math.min(cnt+n,p)-1;
    }
    public static void main(String[] args){
       LeastOperatorsToExpressNumber l = new LeastOperatorsToExpressNumber();
       Timer tm = new Timer();
       tm.st();
       int res, res2;
       for(int i=0;i<10000;i++){
            res = l.leastOpsExpressTarget(11,500041);
            res = l.leastOpsExpressTarget(3,929);
       }
       double d1 = tm.stopAndRead();
        tm.st();
        for(int i=0;i<10000;i++){
            res2 = l.leastOpsExpressTarget2(11,500041);
            res2 = l.leastOpsExpressTarget2(3,929);
        }
        double d2 = tm.stopAndRead();
        System.out.println("d1 is "+ d1 + " d2 is " + d2);
    }
}
