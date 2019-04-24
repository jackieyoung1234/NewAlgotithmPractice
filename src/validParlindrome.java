public class validParlindrome {
    public boolean isPalindrome(String s) {
        //corner case empty
        int aI = 'a',zI = 'z',AI = 'A' , ZI='Z';
        int len = s.length();
        char[] cA = s.toCharArray();
        for(int i =0,j=len-1;i<j;){
            int ci = Character.toLowerCase(cA[i]), cj = Character.toLowerCase(cA[j]) ;
            if(
                    ((ci >=aI && ci<=zI))
                            &&
                            ((cj >=aI && cj<=zI))
                    ) {
                if(ci != cj)
                    return false;
                i++;
                j--;
            }else{
                if(ci< aI || ci >zI)
                    i++;
                if(cj< aI || cj >zI)
                    j--;
            }

        }
        return true;
    }
    public static void main(String[] args){
        int i0 = '0';
        int i00 = Character.toLowerCase('0');
        boolean res = new validParlindrome().isPalindrome("A man, a plan, a canal: Panama");
    }
}
