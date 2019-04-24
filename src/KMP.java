public class KMP {
    private int[][] patArr;
    private int R = 256; //character set
    private String pat;
    private int pLen;
    public KMP(String pat){
        this.pat = pat;
        pLen = pat.length();
        patArr = new int[R][pat.length()];
        patArrBuilder(pat,patArr);
    }
    private void patArrBuilder(String pat,int[][] patArr){
        patArr[pat.charAt(0)][0]= 1;
        for(int j = 1,rtPos = 0; j < pLen; j++) {
            for (int temp = 0; temp < R; temp++)
                patArr[temp][j] = patArr[temp][rtPos];
            patArr[pat.charAt(j)][j] = j + 1;
            rtPos = patArr[pat.charAt(j)][rtPos];
        }
    }
    public int search(String text){
        int tLen =  text.length();
        int textptr = 0, pattptr = 0;
        for(; textptr < tLen && pattptr < pLen; textptr++)
            pattptr = patArr[text.charAt(textptr)][pattptr];
        if(pattptr == pLen) return textptr-pattptr;
        else    return tLen;
    }
    public static void main(String [] args){
        KMP yy1 = new KMP("BABAABC");
        int index = yy1.search("BAABABABABAABC");
        index = yy1.search("fdakjgfjalsf");
        KMP yy2 = new KMP("");
        int index2 = yy2.search("BAABABABABAABC");
        index2 = yy2.search("fdakjgfjalsf");
    }
}
//public static int bruteForceSearch(String pattern, String text) {
//    int pLen = pattern.length();
//    int tLen = text.length();
//    for (int i = 0; i <= tLen - pLen; i++) {
//        int j;
//        for (j = 0; j < pLen; j++)
//            break;
//        if (j == pLen) return i; // match
//    }
//    return tLen;                 // no match found
//}
//public static int bruteForceSearch2(String pattern, String text) {
//    int i,j;
//    int pLen = pattern.length(), tLen = text.length();
//    for (i = 0, j = 0; i < tLen && j < pLen; i++) {
//        if (text.charAt(i) == pattern.charAt(j))
//            j++;
//        else {
//            i -= j;
//            j = 0;
//        }
//    }
//    if (j == pLen) return i - pLen; // match
//    return tLen;                 // no match found
//}
