import java.util.Arrays;

public class LongestSubstringWithAtLeastKRepeatingCharacters {
    public int longestSubstring(String s, int k) {
        return helper(s,0,s.length()-1,k,null);
    }
    //return max length in current range st...end inclusive
    private int helper(String s, int st, int end, int k,int[][] cntdp){
        if(st>end) return 0;
        if(cntdp==null){
            cntdp = new int[s.length()][26];
            for(int i=st;i<=end;i++){
                if(i!=0)
                    cntdp[i]= Arrays.copyOf(cntdp[i-1],26);
                cntdp[i][s.charAt(i)-'a']++;
            }
        }
        //cnt 0......25
        int[] cnt = Arrays.copyOf(cntdp[end],26);//-cntdp[st-1]
        if(st!=0)
            for(int i=0;i<26;i++)
                cnt[i]-=cntdp[st-1][i];
        for(int i=st;i<=end;i++){
            if(cnt[s.charAt(i)-'a']<k){
                int j = i+1;
                // todo : edge case , j out of bounds
                while(j<=end&&cnt[s.charAt(j)-'a']<k) j++;
                int left = helper(s,st,i-1,k,cntdp);
                int right = helper(s,j,end,k,cntdp);
                return Math.max(left,right);
            }
        }
        return end-st+1;
    }
    public static void main(String[] args){
       LongestSubstringWithAtLeastKRepeatingCharacters ob = new LongestSubstringWithAtLeastKRepeatingCharacters();
       int res = ob.longestSubstring("aaabb",3);
       String[] ss = "a:a:a".split("\\:",0);
       boolean tt = "a".equals(ss[1]);
    }
}
