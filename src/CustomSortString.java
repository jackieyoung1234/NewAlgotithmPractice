import java.util.Arrays;

public class CustomSortString {
     public String customSortString(String S, String T) {
        char[] sa = S.toCharArray();
        int[] cnt = new int[26];
        char[] res = new char[T.length()];
        for(int i=0;i<T.length();i++){
           cnt[T.charAt(i)-'a']++;
        }
        int index=0, toindex = 0;
        for(int i=0;i<sa.length;i++){
           toindex = index+cnt[sa[i]-'a'];//exclusive
           Arrays.fill(res,index,toindex,sa[i]);
           index = toindex;
           cnt[sa[i]-'a'] = 0;
        }
        for(int i=0;i<26;i++){
            if(cnt[i]!=0){
             toindex = index + cnt[i];
             Arrays.fill(res,index,toindex,(char)('a'+i));
             index = toindex;
            }
        }
        return new String(res);
    }
    public static void main(String[] args){
        CustomSortString css = new CustomSortString();
        String res = css.customSortString("disqyr","iwyrysqrdj");
    }
}
