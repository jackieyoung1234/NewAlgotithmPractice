public class MaximusSum3NonOverlappingSubarrays {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int len = nums.length;
        //[i] index = [0..i], len is k, max sum array starting index : i<len-2k
        int[] left = new int[len];
        //[i] index = [i  ... len-1] max sum array starting index:i>2k , i<len
        int[] right= new int[len];
        int[] pre = new int[len+1];
        for(int i=0;i<len;i++){
            pre[i+1] = pre[i]+nums[i];
        }
        int leftindex = 0;
        for(int i=k, lefts2n = pre[k] ;i<len-2*k;i++){
            if(pre[i+1]-pre[i-k+1]>lefts2n){
                leftindex = i-k+1;
                lefts2n = pre[i+1] - pre[i-k+1];
            }
            left[i] = leftindex;
        }
        int rightindex = len-k;
        right[len-k]= rightindex;
        for(int i=len-k-1, rights2n = pre[len]- pre[len-k];i>=2*k;i--){
            if(pre[i+k]-pre[i]>=rights2n){
                rightindex = i;
                rights2n = pre[i+k]-pre[i];
            }
            right[i] = rightindex;
        }

        //middleptr : k......len-2k
        int middleptr = k;
        int maxsum2n = 0;
        int toupdate = 0;
        for(int i=k;i<=len-2*k;i++){
            toupdate = pre[i+k] - pre[i] + pre[left[i-1]+k] - pre[left[i-1]] + pre[right[i+k]+k]- pre[right[i+k]];
            if(toupdate>maxsum2n) {
                middleptr = i;
                maxsum2n = toupdate;
            }
        }
        return new int[]{left[middleptr-1],middleptr,right[middleptr+k]};
        //prefixsum int[len+1]   [i+1]= prefix sum 0 --- i
        //sum middle = prefix[middlestptr+k-1] - prefixs[middlesptr-1]
        // middle sum = ..............pre[middlestptr+k] - pre[middlestptr]
        // sum left    leftmax  leftstptr = left[middleptr-1]
        //       sum left = pre[stptr+k]-pre[stptr]
        //sum right   rightmax  ptr = right[middleptr+k]
        //       sum right = pre[stptr+k]-pre[stptr]
    }
    public static void main(String[] args){
        MaximusSum3NonOverlappingSubarrays m = new MaximusSum3NonOverlappingSubarrays();
        int[] res = m.maxSumOfThreeSubarrays(
                new int[]{4,5,10,6,11,17,4,11,1,3},
                1
        );
    }
}
