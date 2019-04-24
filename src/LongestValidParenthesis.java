public class LongestValidParenthesis {
    public int longestValidParentheses(String s) {
        int len=s.length();
        if(len==0) return 0;
        int i=0,j=0,cnt=0;;
        int cur = 0;
        char left = '(';
        int catj = s.charAt(j)-left;
        int res = 0;
        while(i<len&&j<len){
            cur = s.charAt(i++)-left;
            if(cur==0) cnt++;
            else cnt--;
            while(j<=i&&(cnt<0||catj!=0)){
                if(catj==0) cnt--;
                else cnt++;
                if(j++==len-1) break;
                catj = s.charAt(j)-left;
            }
            if(cur!=0&&cnt!=i-j) res = Math.max(res,i-j-cnt);
        }
        return res;
    }
    public static void main(String[] args){
        LongestValidParenthesis l = new LongestValidParenthesis();
       int res = l.longestValidParentheses(")()))");
    }
}
