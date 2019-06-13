public class StringBytes {
    public static void main(String[] args) throws Exception{
        int i = 0x12;
        String charSet = "UTF-16BE";
        String ss =  String.valueOf(i);
        System.out.println(ss.length());
        System.out.println(ss.getBytes(charSet).length);
        String ss2 = new String(intConverter(i),charSet) ;
        byte[] ss2i = ss2.getBytes(charSet);
        System.out.println(ss2.length());
        System.out.println(ss2.getBytes(charSet).length);
        System.out.println("______________________");
        charSet = "UTF-8";
        ss =  String.valueOf(i);
        System.out.println(ss.length());
        System.out.println(ss.getBytes(charSet).length);
        ss2 = new String(intConverter(i),charSet) ;
        ss2i = ss2.getBytes(charSet);
        System.out.println(ss2.length());
        System.out.println(ss2.getBytes(charSet).length);
        int ii = 8975;
        char[] ca = intConverterII(ii);
        int iires = toInt(new String(ca).toCharArray());
    }
    private static byte[] intConverter(int i){
       return new byte[]{
               (byte)(i>>24),
               (byte)(i>>16),
               (byte)(i>>8),
               (byte)(i)
       };
    }
    private static char[] intConverterII(int i){
        return new char[]{
                (char)(i>>16),
                (char)(i)
        };
    }
    private static int toInt(char[] s){
        int res = 0;
        for(int i=0;i<s.length;i++){
           res=(res<<16);
           res+=(int)(s[i]);
        }
        return res;
    }
}
