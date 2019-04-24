public class zigZag {
    public String convert(String s, int numRows) {
        int slen = s.length();
        char[] sArr = s.toCharArray();
        if(slen == 1 || numRows == 1)
            return s;
        String result;
        StringBuilder[] myStrB = new StringBuilder[numRows];
        int StrBP = 0;
        int sP = 0;
        for(int temp = 0; temp < numRows;){
           myStrB[temp] = new StringBuilder();
        }
        while(sP < slen) {
            for (; sP < slen && StrBP <= numRows - 1; )
                myStrB[StrBP++].append(sArr[sP++]);
            for (StrBP = numRows - 2; sP < slen && StrBP >= 1; )
                myStrB[StrBP--].append(sArr[sP++]);
        }
        for(sP = 1; sP < numRows; ){
           myStrB[0].append(myStrB[sP++]);
        }
        return myStrB[0].toString();
    }
	public String convert2(String s, int numRows) {
        int sLen = s.length() -1 ;
        if(sLen == 1 || numRows == 1)
            return s;
        char[] cs = s.toCharArray();
        String result = "";
        int Q = 2*numRows -2;
        int another = Q;
        for(int j = 0; j <= numRows - 1 ; j ++){
            if((j == 0 )
               ||
               (j == numRows - 1)
            ) {
                for (int i = j; i <= sLen; i += Q)
                    result += cs[i];
            }
            if(j >= 1 && j <= numRows -2) {
                for (int i = 0; (i+j) <= sLen; i+=Q) {
                    result += cs[i+j];
                    if ((i + Q - j) > sLen)
                        break;
                    result += cs[i + Q - j];
                }
            }
        }
        return result;
    }
    public static void main(String []args){
	    zigZag z = new zigZag();
	    String ss = z.convert2("PAYPALISHIRING", 3);
        ss = z.convert2("PAYPALISHIRING", 1);

    }
}
