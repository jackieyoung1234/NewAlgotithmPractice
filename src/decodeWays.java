public class decodeWays {
    public int numDecodings(String s) {
        //corner case slen = 0,
        int len = s.length();
        int[] dp = new int[len+2];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = helper(s.substring(0,1))?1:0;
        for(int i=3;i<len+2;i++){
            if(helper(s.substring(i-3,i-1)))
                dp[i] += dp[i-2] ;
            if(helper(s.substring(i-2,i-1)))
                dp[i] += dp[i-1]  ;
        }
        return dp[len+1];
    }
    private boolean helper(String t){
        int value = Integer.valueOf(t);
        if(t.length()==1){
            return value>=1 &&value<=9;
        }else{
            return (t.charAt(0)!='0')&&value>=1 && value<=26;
        }
    }
    public static void main(String[] args){
        int res = new decodeWays().numDecodings("01");
    }
}
