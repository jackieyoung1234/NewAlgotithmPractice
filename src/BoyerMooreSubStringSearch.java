public class BoyerMooreSubStringSearch {
    private static final int R = 26;//alphabetic set nubmer
    private int[] indexTable;
    private int pattern_length;
    private String pat;
    public static int charAt(int index, String s){
       return s.charAt(index) - 'a';
    }
    public BoyerMooreSubStringSearch(String pattern){
        pattern_length = pattern.length();
        pat = pattern;
        indexTable = new int[R];
        for(int t=0;t < R; t ++)
            indexTable[t] = -1;
        for(int t=0; t < pattern_length; t ++)
            indexTable[charAt(t,pattern)] = t;
    }
    public int search(String text){
        int textLength = text.length();
        int i = 0,j = pattern_length - 1;
        int s;
        for(;i < textLength;s = j - indexTable[charAt(i+j,text)],i += (s < 1)? 1:s ,j = pattern_length - 1){
            while(charAt(i+j,text) == charAt(j,pat)) {
                if(j == 0 )
                    return i;
                j--;
            }
        }
        return textLength;
    }
    public static void main(String [] args){
       BoyerMooreSubStringSearch myBM = new BoyerMooreSubStringSearch("yangyu");
       int result = myBM.search("fjuaslyanfgsyangyfdaoweryangyufdklafasyang");
       result = myBM.search("fjuaslyanfgsyangyfdaoweyangyufdklafasyang");
       int fdsaf = 0;
    }
}
