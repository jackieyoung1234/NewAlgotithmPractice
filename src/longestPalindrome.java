public class longestPalindrome {
    public String longestPalindrome(String s){
        int maxL = 1;
        int[] indexes = {0,1};
        for(int start = 0; start<s.length(); start++){
            if(s.length() - start <= maxL)
                break;
            int[] temp = findP(s,start);
            if((temp[1]- temp[0])>maxL){
                maxL = temp[1]- temp[0];
                indexes = temp;
            }
        }
        return s.substring(indexes[0],indexes[1]);
    }
    public int[] findP(String s, int l){
        for(int j = s.length()-1;j>l;j--){
            int jj = j;
            int i = l;
            while(s.charAt(jj--) == s.charAt(i++)){
                if(jj <= i){
                    return new int[]{l,j+1};
                }
            }
        }
        return new int[]{l,l+1};
    }
    public String longestPalindrome2(String s) {
        int max = 1;
        int te = 0;
        int m = 2 * s.length() -1;
        int i = 2;
        int pointer = i;
        for(; i <= m; i++) {
            if (max >= i + 1 + i%2)
                break;
            te = twoWayCenter(s, i);
            if (te > max) {
                max = te;
                pointer = i;
            }
        }
        int left = (pointer-max)>>1;
        return s.substring(left,left+max);
    }
    public int twoWayCenter(String s, int pointer){
        int sLength = s.length()-1;
        int right = (pointer + 1 + pointer%2)>>1;
        int left = (pointer - 1 - pointer%2)>>1;
        while(left>=0 && right <= sLength){
            if(s.charAt(left ) != s.charAt(right))
                break;
            left--;
            right++;
        }
        return (right - left -1);
    }
    public static void main(String [] args){
       //int[] temp;
       //temp = new longestPalindrome().longestPalindrome("abcbf");
       // temp = new longestPalindrome().longestPalindrome("aebbehebk");
       String temp;
       temp = new longestPalindrome().longestPalindrome("abcbf");
       temp = new longestPalindrome().longestPalindrome("aebbehebk");
       temp = new longestPalindrome().longestPalindrome("aaaaaaaaa");
       int length;
       temp = new longestPalindrome().longestPalindrome2("abcbf");
       temp = new longestPalindrome().longestPalindrome2("aebbehebk");
       temp = new longestPalindrome().longestPalindrome2("aaaaaaaaa");
       int i =0;
    }
}
