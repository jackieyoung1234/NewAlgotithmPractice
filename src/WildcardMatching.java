public class WildcardMatching {
    public boolean isMatch(String s, String p) {
        int slen = s.length();
        int plen = p.length();
        boolean[][] dp = new boolean[slen+1][plen+1];
        dp[0][0] = true;
        if(plen>0&&p.charAt(0)=='*'){
            for(int i=1;i<=slen;i++){
                dp[i][0] = true;
            }
        }
        for(int i=1;i<=slen;i++){
            for(int j=1;j<=plen;j++){
                if(s.charAt(i-1)==p.charAt(j-1)||p.charAt(j-1)=='?')
                    dp[i][j]=dp[i-1][j-1];
                else if(p.charAt(j-1)=='*'){
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
            }
        }
        return dp[slen][plen];
    }
    public static void main(String[] args){
       boolean res = new WildcardMatching().isMatch("adceb","*a*b");
    }
}
