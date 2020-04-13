public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        //todo len = 0
        if(len==0) return new int[0];
        int seglen = len/k+(((len%k)==0)?0:1);
        int[] lm = new int[len];
        int[] rm = new int[len];
        int reslen = len-k+1;
        int[] res = new int[reslen];
        int m2n=0,i=0;
        for(;i<len;i++){
            if((i%k)==0){
                m2n=nums[i];
            }else{
                m2n=Math.max(m2n,nums[i]);
            }
            lm[i]=m2n;
        }
        i=len-1;
        for(int gi=seglen-1;gi>=0;gi--){
            m2n=nums[i];
            for(;i>=gi*k;i--){
                m2n=Math.max(m2n,nums[i]);
                rm[i] = m2n;
            }
        }
        i=k-1;
        for(int resptr=0;resptr<reslen;resptr++,i++){
            res[resptr]= Math.max(lm[i],rm[i-k+1]);
        }
        return res;
    }
    public static void main(String[] args){
        SlidingWindowMaximum s = new SlidingWindowMaximum();
        int[] res = s.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7},3);
    }
}
