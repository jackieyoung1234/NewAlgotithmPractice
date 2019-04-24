public class BackspaceStringCompare {
        //##        #
        //a#        ''
        //aab#       aa#a
        //nzp#o#g  b#nzp#o#g
        public boolean backspaceCompare(String S, String T) {
            int ptr1 = S.length()-1, ptr2 = T.length()-1;
            char sc = '\u0000', tc = '\u0000';
            int cnt1 = 0, cnt2 = 0;
            while(ptr1>=0&&ptr2>=0){
                //s
                //sc = S.charAt(ptr1);
                //tc = T.charAt(ptr2);
                while(ptr1>=0&&(S.charAt(ptr1)=='#'||cnt1>0)){
                    if(S.charAt(ptr1)=='#') cnt1++;
                    else cnt1--;
                    ptr1--;
                }
                while(ptr2>=0&&(T.charAt(ptr2)=='#'||cnt2>0)){
                    if(T.charAt(ptr2)=='#') cnt2++;
                    else cnt2--;
                    ptr2--;
                }
                if(ptr1>=0&&ptr2>=0&&S.charAt(ptr1--)!=T.charAt(ptr2--)) return false;

            }
            while(ptr1>=0&&(S.charAt(ptr1)=='#'||cnt1>0)){
                if(S.charAt(ptr1)=='#') cnt1++;
                else cnt1--;
                ptr1--;
            }
            while(ptr2>=0&&(T.charAt(ptr2)=='#'||cnt2>0)){
                if(T.charAt(ptr2)=='#') cnt2++;
                else cnt2--;
                ptr2--;
            }
            return ptr1==-1&&ptr2==-1;
        }
        public void dd(int[] k ){
           k[0] ++;
        }
        public static void main(String[] args){
               boolean res = new BackspaceStringCompare().backspaceCompare("abc#o#g","b#abc#o#g");
               int[] k = new int[]{0};
               new BackspaceStringCompare().dd(k);
        }

}
