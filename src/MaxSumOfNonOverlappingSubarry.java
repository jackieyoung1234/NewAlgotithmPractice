public class MaxSumOfNonOverlappingSubarry {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        //todo: edge case k ==0
        int len = nums.length;
        int[] presum = new int[len+1];
        for(int i=0;i<len;i++){
            presum[i+1] = presum[i]+nums[i];
        }
        int firstindex = 0;
        int[] secondindex = new int[]{0,k};
        int[] thirdindex = new int[]{0,k,2*k};
        int firstsum = presum[k];
        int secondsum = presum[2*k];
        int thirdsum = presum[3*k];
        int firstnow = 0, secondnow = 0, thirdnow = 0;
        for(int i=1;i<len-3*k+1;i++){
            firstnow = presum[i+k]-presum[i];
            secondnow = presum[i+2*k]-presum[i+k];
            thirdnow = presum[i+3*k]-presum[i+2*k];
            if(firstnow>firstsum){
                firstindex = i;
                firstsum = firstnow;
            }
            if(firstsum + secondnow>secondsum){
                secondindex[0] = firstindex;
                secondindex[1] = i+k;
                secondsum = firstsum+secondnow;
            }
            if(secondsum+thirdnow>thirdsum){
                thirdindex[0] = secondindex[0];
                thirdindex[1] =secondindex[1];
                thirdindex[2] = i+2*k;
            }
        }
        return thirdindex;
    }
    public static void main(String[] args){
       int i =(int) Math.pow(10,2);
       MaxSumOfNonOverlappingSubarry m = new MaxSumOfNonOverlappingSubarry();
       int[] res = m.maxSumOfThreeSubarrays(new int[]{
            1,2,1,2,6,7,5,1
       },
               2);
    }
}
