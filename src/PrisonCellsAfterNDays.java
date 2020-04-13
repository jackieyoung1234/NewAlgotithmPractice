import java.util.Arrays;

public class PrisonCellsAfterNDays {
    public int[] prisonAfterNDays(int[] cells, int N) {
        int[] bk = cells;
        int[] n2cp = new int[256];
        int[] cp2n = new int[256];
        int[] next;
        int pat;
        Arrays.fill(cp2n,-1);
        cp2n[pat=process(cells)]=0;
        n2cp[0]=pat;
        int n = 1;
        while(n<=N){
            //todo
            next = getN(cells);
            pat = process(next);
            if(cp2n[pat]==-1){
                cp2n[pat]=n;
                n2cp[n] = pat;
            }else{
                int repeat = cp2n[pat];
                N = ((N-repeat+1)%(n-repeat))+repeat;
                break;
            }
            cells = next;
            n++;
        }
        if(N==0) return bk;
        return getresult(n2cp[N]);
    }
    private int process(int[] cells){
        int base = 1;
        int res = 0;
        for(int i=7;i>=0;i--){
            if(cells[i]==1) res+=base;
            base*=2;
        }
        return res;
    }
    private int[] getN(int[] cur){
        int[] res = new int[8];
        for(int i=1;i<=6;i++){
            if(cur[i-1]==cur[i+1])
                res[i]=1;
        }
        res[0] = 0;
        res[7] = 0;
        return res;
    }
    private int[] getresult(int pat){
        int[] res = new int[8];
        for(int i= 7,index=0;i>=0;i--,index++){
            res[i]=(pat&(1<<index));
        }
        return res;
    }
    public static void main(String[] args){
        PrisonCellsAfterNDays p = new PrisonCellsAfterNDays();
        int[] res = p.prisonAfterNDays(
                new int[]{0,1,0,1,0,1,0,0},
               27
        );
    }
}
