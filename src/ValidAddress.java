public class ValidAddress {
    public String validIPAddress(String IP) {
        String[] candidate = IP.split("\\.",-1);
        int len = candidate.length;
        try {
            if (len == 4 && is4(candidate)) {
                return "IPv4";
            }
            candidate = IP.split(":",-1);
            len = candidate.length;
            if (len == 8 && is6(candidate)) {
                return "IPv6";
            }
            return "Neither";
        }catch( NumberFormatException e){
           return "Neither";
        }
    }
    private boolean is4(String[] c){
       //check every 0-255
       for(String s:c){
          if(!s4(s)) return false;
       }
       return true;
       // check leading 0, 0 ok,   04, 012 not ok
    }
    private boolean is6(String[] c){
        for(String s:c){
           if(!s6(s)) return false;
        }
        return true;
    }
    private boolean s4(String s) throws NumberFormatException{
       //check every 0-255
       int len = s.length();
       if(len>3) return false;
       if(len==1) return true;
       //len <=3, >1, ie, 2,3
       // check leading 0, 0 ok,   04, 012 not ok
       int numconverted = Integer.parseInt(s);
       if(numconverted<=0||numconverted>255) return false;
       if(s.charAt(0)=='0') return false;
       return true;
    }
    private boolean s6(String s) throws NumberFormatException{
        int len = s.length();
        if(len>4) return false;
        int convertednum = Integer.parseInt(s,16);
        if(convertednum<0) return false;
        return true;
    }
    private void test(String st, int[] at){
       return;
    }
    public static void main(String[] args){
        String[] ss = "1:2:".split(":",-1);
        int ii = Integer.parseInt("-0",16);
        ValidAddress v = new ValidAddress();
        String res = v.validIPAddress("172.16.254.1");
        res = v.validIPAddress("172.16.254.01");
        res = v.validIPAddress("2001:0db8:85a3:0000:0000:8a2e:0370:7334");
        res = v.validIPAddress("2001:db8:85a3:0:0:8A2E:0370:7334");
        res = v.validIPAddress("2001:0db8:85a3::8A2E:0370:7334");
        res = v.validIPAddress("02001:0db8:85a3:0000:0000:8a2e:0370:7334");

        v.test(null,null);
    }
}
