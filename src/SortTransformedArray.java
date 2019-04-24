public class SortTransformedArray {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        //todo len == 0, solved
        int len = nums.length;
        int[] res = new int[len];
        //todo a ==0
        if(a==0){
            if(b>=0){
                for(int i=0;i<len;i++){
                    res[i]=helper2(nums[i],a,b,c);
                }
            }else{
                for(int i=0;i<len;i++){
                    res[i]=helper2(nums[len-i],a,b,c);
                }
            }
            return res;
        }
        double middle = -b/(double)(2*a);
        int index = helper1(nums,0,nums.length,-b/(double)(2*a));
        int lptr = index-1, rptr = index;
        int dif = 0;
        if(a>0){
            int resptr=0;
            while(lptr>=0 || rptr<len){
                if(lptr<0) res[resptr++]=helper2(nums[rptr++],a,b,c);
                else if(rptr>=len) res[resptr++]=helper2(nums[lptr--],a,b,c);
                else{
                    dif = Double.compare(middle-(double)(nums[lptr]),(double)(nums[rptr])-middle);
                    if(dif>=0) {
                        res[resptr++]=helper2(nums[rptr++],a,b,c);
                    }else{
                        res[resptr++]=helper2(nums[lptr--],a,b,c);
                    }
                }
            }
        }else{
            int resptr=len-1;
            while(lptr>=0 || rptr<len){
                if(lptr<0) res[resptr--]=helper2(nums[rptr++],a,b,c);
                else if(rptr>=len) res[resptr--]=helper2(nums[lptr--],a,b,c);
                else{
                    dif = Double.compare(middle-(double)(nums[lptr]),(double)(nums[rptr])-middle);
                    if(dif>=0) {
                        res[resptr--]=helper2(nums[rptr++],a,b,c);
                    }else{
                        res[resptr--]=helper2(nums[lptr--],a,b,c);
                    }
                }
            }
        }
        return res;
    }
    //find first index i, [i]>= (-b/2a)
    // hi = up boundary + 1
    // if hi == up boundary + 1, then no element >= target
    private int helper1(int[] a, int lo, int hi, double t){
        while(lo<hi){
            int mid = lo + (hi-lo)/2;
            int comp = Double.compare((double)a[mid],t);
            if(comp < 0){
                lo=mid+1;
            }else{
                hi = mid;
            }
        }
            return hi;

    }
    private int helper2(int x, int a, int b, int c){
        return a*x*x+b*x+c;
    }
    public static void main(String[] args){
       SortTransformedArray stfa = new SortTransformedArray();
       int[] res = stfa.sortTransformedArray(new int[]{-4,-2,2,4},-1,3,5);
    }
}
