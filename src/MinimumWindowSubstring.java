public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        int[] expcnt = new int[256];
        for(char c: t.toCharArray()){
            expcnt[c]++;
        }

        int leftmatch = t.length();
        //java array has longest length of 2^32
        int left = -1, right = Integer.MAX_VALUE-1;
        int st = 0;
        for(int end=0;end<s.length();end++){
            char c = s.charAt(end);
            //1.update left match number
            if(expcnt[c]-->0){
                leftmatch--;
            }
            //3.how to move forward st
            //2.update left and right
            if(leftmatch==0){
                while(st<s.length()&&expcnt[s.charAt(st)]<0){
                    expcnt[s.charAt(st++)]++;
                }
                if(end-st<right-left){
                    left = st;
                    right = end;
                }
            }
        }
        return left==-1?"":s.substring(left,right+1);
    }
    public static void main(String[] args){
        long i  = (long)Integer.MAX_VALUE - Integer.MIN_VALUE;
        int[] a1 = new int[]{1,2};
        int[] a2 = new int[]{1,2};
        //int[] a3 = a1+a2;
    }
}
