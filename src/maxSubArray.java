public class maxSubArray {
    public int maxSubArray(int[] nums){
        if(nums.length == 0)
            return 0;
        int max2Now = Integer.MIN_VALUE;
        int c2Now = 0;
        for(int i : nums){
            int temp = c2Now + i;
            max2Now = Math.max(temp,max2Now);
            c2Now = Math.max(0,temp);
        }
        return max2Now;
    }
    public int maxSubArray_divideAndConquer(int[] nums){
        if(nums.length == 0)
            return 0;
        return helper(nums,0,nums.length-1);
    }
    private int helper(int[] arr, int left, int right){
        if(left == right) return arr[left];
        int mid = left + (right -left)/2;
        int lM = helper(arr,left,mid);
        int rM = helper(arr,mid+1,right);
        int lCSum = Integer.MIN_VALUE;
        int temp = 0;
        int k = mid;
        while(k>=left){
            temp += arr[k--];
            lCSum = Math.max(temp,lCSum);
        }
        temp = 0;
        int rCSum = Integer.MIN_VALUE;
        k = mid+1;
        while(k <= right){
            temp += arr[k++];
            rCSum = Math.max(temp,rCSum);
        }
        return Math.max(Math.max(lM,rM),lCSum+rCSum);
    }
    public static void main(String[] args){
        int[][] kk = new int[3][2];
        int kkk = (int)Math.ceil(Math.max(2,4)/2.0);
    }
}
