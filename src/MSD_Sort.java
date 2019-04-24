public class MSD_Sort {
    private static int R = 256;
    private static String[] aux;
    public static void MSD_Sort(String [] a){
       MSD_Sort(a,0,0,a.length-1);
    }
    private static int charAtD(String a, int d){
        return (d>=a.length())? -1:a.charAt(d);
    }
    private static void MSD_Sort(String [] a, int lo, int d, int hi){
        if(lo >= hi)
            return;
        aux = new String[a.length];
        int cnt[] = new int[R+2];
        for(int temp = lo;temp <= hi;temp++){
           cnt[charAtD(a[temp],d)+2]++;
        }
        for(int temp = 0;temp <= R;temp++){
            cnt[temp+1] += cnt[temp];
        }
        for(int temp = lo; temp <= hi;temp++){
            aux[cnt[charAtD(a[temp],d)+1]++] = a[temp];
        }
        for(int temp = lo; temp <= hi;temp++){
            a[temp] = aux[temp - lo];
        }
        for(int temp = 0; temp < R;temp ++)
            MSD_Sort(a,lo + cnt[temp],d+1,lo+cnt[temp+1]-1);
    }
    public static void main(String [] args){
        String []s = {"she","","sells","seashells","by","an","",
                "the","sea","shore","the","shells","she","sells","are","surely","seashells",
                "The","basic","idea","behind","MSD","string","sort","is",
                "quite","effective:","in","typical","applications,","the",
                "strings","will","be","in","order","after","examining",
                "only","a","few","characters","in","the","key.",
                "Put","another","way,","the","method",
                "quickly","divides","the","array","to",
                "be","sorted","into","small"};
        MSD_Sort(s);
        int m = 'M';
        int a = 'a';
        System.out.println(m);
        System.out.println(a);
    }
}
