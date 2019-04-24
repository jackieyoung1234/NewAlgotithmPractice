import java.util.HashMap;
import java.util.Map;

public class lengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s){
       int cur = 0;
       int j = 0;
       int jd = 0;
       int maxL =0;
       Map<Character,Integer> myM = new HashMap<>();
       while(cur<=s.length() - 1){
            if(s.length()-cur <= maxL){
                break;
            }
           while(j <= s.length()-1) {
               if (myM.containsKey(s.charAt(j))) {
                    jd = myM.get(s.charAt(j));
                    for(int temp = cur; temp <= jd; temp++)
                        myM.remove(s.charAt(temp));
                    cur = jd + 1;
                    break;
               }
               myM.put(s.charAt(j), j++);
               maxL = Math.max(maxL,j-cur);
           }
       }
       return maxL;
    }
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[256]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if(n-i <= ans)
                break;
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }
    public static void main(String [] args){
       int i = new lengthOfLongestSubstring().lengthOfLongestSubstring("abcabcbb");
       i = new lengthOfLongestSubstring().lengthOfLongestSubstring("bbbb");
       i = new lengthOfLongestSubstring().lengthOfLongestSubstring("pwwkew");
       i = new lengthOfLongestSubstring().lengthOfLongestSubstring("dvdf");
       i = new lengthOfLongestSubstring().lengthOfLongestSubstring2("abcabcbb");
       i = new lengthOfLongestSubstring().lengthOfLongestSubstring2("bbbb");
       i = new lengthOfLongestSubstring().lengthOfLongestSubstring2("pwwkew");
       i = new lengthOfLongestSubstring().lengthOfLongestSubstring2("dvdf");
    }
}
