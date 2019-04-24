public class FindDifference {
    public char findTheDifference(String s, String t) {
        int[] sa = new int[26];
        char c = '\u0000';
        for(char cc : s.toCharArray()){
            sa[cc-'a']++;
        }
        for(char cc : t.toCharArray()){
            if (--sa[cc - 'a'] == -1){
                c = cc;
                break;
            }
        }
        return c;
    }
    public static void main(String[] args){
       char ccc = new FindDifference().findTheDifference("abcd","abcde");
    }
}
