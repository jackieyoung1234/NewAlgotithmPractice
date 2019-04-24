public class IsValidSerialization {
    public boolean isValidSerialization(String preorder) {
        char[] sa = preorder.toCharArray();
        int rsum = 0;
        for(int i=sa.length-1;i>=0;i--){
            char c = sa[i];
            //if(c==',') continue;
            //else if(c=='#'){
            if(c=='#'){
                rsum++;
                //}else{
            }else if(Character.isDigit(c)){
                //num
                do{
                    i--;
                }while(i>=0&&Character.isDigit(sa[i]));
                if(--rsum<=0) return false;
            }
        }
        return rsum==1;
    }
}
