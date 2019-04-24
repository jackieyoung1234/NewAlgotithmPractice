import java.util.HashMap;

public class EvaluateDivision {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        HashMap<String, Entry> h = new HashMap<>();
        int len = equations.length;
        double[] res =new double[queries.length];
        for(int i=0;i<len;i++){
            String dividend = equations[i][0];
            String divisor = equations[i][1];
            Entry dp = h.get(dividend);
            Entry sp = h.get(divisor);
            if(dp==null && sp == null){
                h.put(dividend, new Entry(divisor,values[i]));
                h.put(divisor,new Entry(divisor,1.0));
            }else if(sp==null){
                //case a/b =2 ,  a/c =2
                helper(h, dividend, divisor, dp, values[i]);
                h.put(divisor, new Entry(divisor,1.0));
            }else if(dp==null){
                //case c/d =2 ,   a/c =3
                Entry newe = findP(h,divisor);
                newe.v *= values[i];
                h.put(dividend, newe);
            }else{
                helper(h,dividend,divisor,dp,values[i]);
            }
        }
        for(int i=0;i<queries.length;i++){
            Entry dp = findP(h,queries[i][0]);
            Entry sp = findP(h,queries[i][1]);
            if(dp==null || sp == null || !dp.parent.equals(sp.parent)){
                res[i] = -1.0;
            }else {
                res[i] = dp.v/sp.v;
            }
        }
        return res;
    }
    private class Entry{
        String parent;
        double v;
        public Entry(String p, double v){
            this.parent = p;
            this.v = v;
        }
    }
    //pind parent helper
// a/b 2   c/d 3      a/c 6  a -- c 6  b --- 3 c c--- 3 d  d ---- 1 d
//      b  / d??  b     3 c         b ---- 3 * c/d
//b 3c    c --3d    d-- 1d     _____    b   27e
//e =3c , ee = ( c ) = d--3, return d, 3*||  e = 3d,  ee = (d)= 1d, return d--3*1|| e = 1d,
    private Entry findP(HashMap<String,Entry> h, String s){
        Entry e = h.get(s);
        if(e==null||e.parent.equals(s)){
            return e;
        }
        Entry ee = findP(h,e.parent);
        return new Entry(ee.parent, e.v*ee.v);
    }
    //helper recursive function
    private void helper(HashMap<String, Entry> h, String a, String c, Entry ap, double a_c){
        if(!ap.parent.equals(a)){
            //recur call
            helper(h,ap.parent,c,h.get(ap.parent),a_c/ap.v);
        }
        h.put(a,new Entry(c,a_c));//path compression
    }
    public static void main(String[] args){
        EvaluateDivision e = new EvaluateDivision();
        double res[] = e.calcEquation(
            new String[][]{
                    {"a","b"},
                    {"b","c"},
                    {"c","d"},
                    {"d","e"}
            },
            new double[]{
                    3,
                    4,
                    5,
                    6
            },
            new String[][]{
                    {"a","e"},
                    {"e","b"},
                    {"b","d"},
                    {"b","b"},
                    {"b","s"},
                    {"s","s"}
            }
        );
    }
}
