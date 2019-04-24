import java.util.Arrays;

public class KEmptySlots {
    public int kEmptySlots(int[] flowers, int k) {
        int N = flowers.length;
        int glen = N/(k+1)+ ((N%(k+1))==0?0:1);
        //int glen = N/(k+1)+ 1;
        int tt = N/(k+1);
        int ttt = (N%(k+1))==0?0:1;
        int tttt = (N%(k+1));
        int[] min = new int[glen];
        int[] max = new int[glen];
        Arrays.fill(max,0);
        Arrays.fill(min,Integer.MAX_VALUE);
        for(int i=0;i<N;i++){
            int findex = flowers[i];
            int gindex = (findex-1)/(k+1);
            if(gindex>0 && findex<min[gindex]&&max[gindex-1]==findex-k-1){
                return i+1;
            }
            if(gindex<glen-1&&findex>max[gindex]&&min[gindex+1]==findex+k+1){
                return i+1;
            }
            max[gindex] = Math.max(findex,max[gindex]);
            min[gindex] = Math.min(findex,min[gindex]);
        }
        return -1;
    }
    public static void main(String[] args){
       KEmptySlots k = new KEmptySlots();
       int res = k.kEmptySlots(new int[]{1,3,2},1);
    }
}
