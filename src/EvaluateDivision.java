import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class EvaluateDivision {
    private class SV{
        String pa;
        double r;// this/pa = r
        public SV(String s, double r){
            this.pa = s;
            this.r = r;
        }
    }
    //    a  b,3
//    b  e,3
//    e  e,1
    private SV getParent(HashMap<String,SV> hm,String s){
        SV pas = hm.get(s);
        SV temp = null;
        if(pas==null) return null;
        double res = pas.r;
        while(!s.equals(pas.pa)){
            s = pas.pa;
            temp = hm.get(pas.pa);
            pas.pa = temp.pa;
            res*=temp.r;
            pas.r *= temp.r;
            pas = temp;
        }
        return new SV(s,res);
    }
    private void set(HashMap<String,SV> hm, String s1, String s2,double d){
        SV sp1 = getParent(hm,s1);
        SV sp2 = getParent(hm,s2);
        if(sp1.pa.equals(sp2.pa)) return;
        hm.put(sp1.pa,new SV(sp2.pa,d*sp2.r/sp1.r));
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries){
        HashMap<String,SV> hm = new HashMap<>();
        double[] res = new double[queries.size()];
        String o1=null, o2=null;
        SV so1 = null, so2 = null;
        int i = 0;
        for(i=0;i<values.length;i++){
            o1 = equations.get(i).get(0);
            o2 = equations.get(i).get(1);
            so1 = hm.get(o1);
            so2 = hm.get(o2);
            if(so1==null&&so2==null){
                hm.put(o1,new SV(o2,values[i]));
                hm.put(o2,new SV(o2,1.0));
            }else if(so1==null){
                hm.put(o1,new SV(o2,values[i]));
            }else if(so2==null){
                hm.put(o2,new SV(o1,1/values[i]));
            }else{
                set(hm,o1,o2,values[i]);
            }
        }
        i= 0;
        for(List<String> q: queries){
            so1 = getParent(hm,q.get(0));
            so2 = getParent(hm,q.get(1));
            if(so1==null||so2==null) res[i]  = -1.0;
            else if(!so1.pa.equals(so2.pa)) res[i] = -1.0;
            else res[i] = so1.r/so2.r;
            i++;
        }
        return res;
    }
    public static void main(String[] args){
        EvaluateDivision e = new EvaluateDivision();
        double[] res = e.calcEquation(
                Arrays.asList(
                        Arrays.asList(new String[]{"x1","x2"}),
                        Arrays.asList(new String[]{"x2","x3"}),
                        Arrays.asList(new String[]{"x3","x4"}),
                        Arrays.asList(new String[]{"x4","x5"})
                ),
                new double[]{3.0,4.0,5.0,6.0},
                Arrays.asList(
                        Arrays.asList(new String[]{"x1","x5"}),
                        Arrays.asList(new String[]{"x5,x2"})
                        )
        );
    }
}
