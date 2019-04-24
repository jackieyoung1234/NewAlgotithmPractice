import java.util.Arrays;

public class SETI_Compress {
    public String comp(String t){
        if(t==null) return null;
        int len = t.length();
        if(len==0) return "";
        char[] res = new char[len];
        int ptr = 0;
        char pre = '\u0000', cur = '\u0000';
        int cnt = 1;
        for(int i=0;i<len;i++){
            cur = t.charAt(i);
            if(cur!=pre&&cnt>2){
                ptr = helper(res,ptr,cnt);
            }
            res[ptr++] = cur;
            //update cnt
            if(cur!=pre){
                cnt =1;
            }
            else cnt++;
            //update pre
            pre = cur;
        }
        if(cnt>2){
            ptr = helper(res,ptr,cnt);
        }
        return new String(res,0,ptr);
    }
    private int helper(char[] res,int ptr, int cnt){
        ptr-=(cnt-1);
        res[ptr++] ='X';
        char[] cntchar = String.valueOf(cnt).toCharArray();
        System.arraycopy(cntchar,0,res,ptr,cntchar.length);
        return ptr + cntchar.length;
    }

    public static void main(String[] args){
        System.out.println(
                "aabbbccccd: "+ new SETI_Compress().comp("aabbbccccd")
        );
        System.out.println(
                "a: "+ new SETI_Compress().comp("a")
        );
        System.out.println(
                "aaa: "+ new SETI_Compress().comp("aaa")
        );
        System.out.println(
                "aaaaaaaaaaaaaaaaaaaaa: "+ new SETI_Compress().comp("aaaaaaaaaaaaaaaaaaaaa")
        );
        System.out.println(
                "aabbb: "+ new SETI_Compress().comp("aabbb")
        );
        System.out.println(
                "aabbbcccccccccccccccccccccc: "+ new SETI_Compress().comp("aabbbcccccccccccccccccccccc")
        );
        System.out.println(
                "aaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbccc: "+ new SETI_Compress().comp("aaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbccc")
        );

    }
}
