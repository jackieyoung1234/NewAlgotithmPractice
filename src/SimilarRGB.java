public class SimilarRGB {
    public String similarRGB(String color) {
       return "#"+mindifpow2(color,1)+mindifpow2(color,3)+mindifpow2(color,5);
    }
    // unit is a string with length 2
    // ith 1 charAt 1 and charAt2
    private String mindifpow2(String unit,int ith){
        int v1 = Integer.parseUnsignedInt(unit.substring(ith,ith+1),16);
        int v2 = Integer.parseInt(unit.substring(ith+1,ith+2),16);
        if(v1==v2) return unit.substring(ith,ith+2);
        int v = v1*16 + v2;
        int va = v/17;
        if((v%17)>8) va++;
        String val = Integer.toHexString(va);
        return val+val;
    }
    public static void main(){
    }
}
