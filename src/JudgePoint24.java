import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JudgePoint24 {
    public boolean judgePoint24(int[] nums) {
        double[] dn = new double[4];
        ArrayList<Double> dl = new ArrayList<>(Arrays.asList(
                (double)nums[0],
                (double)nums[1],
                (double)nums[2],
                (double)nums[3]
        ));
    for(int i=0;i<4;i++){
        dn[0] = dl.remove(i);
        for(int j=0;j<3;j++){
            dn[1] = dl.remove(j);
            for(int k=0;k<2;k++){
                dn[2] = dl.remove(k);
                dn[3] = dl.remove(0);
                if(helper(dn,1)){
                    return true;
                }else{
                    dl.add(0,dn[3]);
                    dl.add(k,dn[2]);
                }
            }
            dl.add(j,dn[1]);
        }
        dl.add(i,dn[0]);
    }

       return false;

               }
private boolean helper(double[] a, int round){
        if(round == 4){
        return a[0]==24.0;
        }
        for(int i=0;i<4-round;i++){
        double[] an = new double[4-round];
        for(int ope=0;ope<4;ope++){
        op(a,an,i,round,ope);
        if(helper(an,round+1))
        return true;
        }
        }
        return false;
        }
private void op(double[] a, double[]an, int pos, int round, int ope){
        //round - a.length - an.length
        //1            4          3
        //2            3          2
        //3           2          1
        //ope   0 + 1 - 2 *  3 /
        for(int i=0;i<pos;i++){
        an[i] = a[i];
        }
        if(ope==0){
        an[pos] = a[pos] + a[pos+1];
        } else if(ope==1){
        an[pos] = a[pos] - a[pos+1];
        }else if(ope==2){
        an[pos] = a[pos] * a[pos+1];
        }else{
        an[pos] = a[pos]/a[pos+1];
        }
        for(int i=pos+1;i<4-round;i++){
        an[i] = a[i+1];
        }
    }
    public static void main(String[] args){
       JudgePoint24 j = new JudgePoint24();
       boolean res = j.judgePoint24(new int[]{1,5,9,1});
    }
}
