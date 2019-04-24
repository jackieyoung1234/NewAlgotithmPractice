import java.math.BigInteger;
import java.util.Random;

public class Rabin_Karp {
    private int rabin_karpSearch(String context,String target, int tlen) {
        long div = getProbableLongPrime();
        long inithash = getIniHash(context, tlen, div);
        long targethash = getHash(target, div);
        long leadingpower = getLeadingDigitPower(tlen, div);
        if (inithash == targethash) return 0;
        for (int i=tlen; i < context.length(); i++) {
            inithash = (inithash + div - context.charAt(i - tlen) * leadingpower % div) % div;
            inithash = (inithash*256 + context.charAt(i)) % div;
            if (inithash == targethash && check(context, i-tlen+1, target, tlen)) return i-tlen+1;
        }
        return context.length();
    }
    private long getLeadingDigitPower(int len, long div) {
        long res = 1;
        while (--len > 0) {
            res *= 256;
            res %= div;
        }
        return res;
    }
    private boolean check(String s, int st, String t, int tlen) {
        return s.substring(st, st + tlen).equals(t);
    }
    private long getProbableLongPrime() {
        //2^8 = 256, hash: < prime,   hash * 2^8 + char <= Long.MAX_VALUE(2^63-1)
        return BigInteger.probablePrime(54, new Random()).longValue();
    }
    private long getHash(String s, long div) {
        long res = 0;
        for (char c : s.toCharArray()) {
            res = (res * 256 + c) % div;
        }
        return res;
    }
    private long getIniHash(String s, int len, long div) {
        return getHash(s.substring(0,len),div);
    }


    public static void main(String[] args ){
       Rabin_Karp r = new Rabin_Karp();
       int res = r.rabin_karpSearch("aabbcaabbc","abb",3);
       res = r.rabin_karpSearch("abzzz","abb",3);
       res = 0%4;
       int[] a1 = new int[2];
       int[] a2 = new int[2];
       boolean comp = a1.equals(a2);
       a1[1]=2;
       a2[0]=2;
    }
}
