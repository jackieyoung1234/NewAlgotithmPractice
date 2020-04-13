public class PartitionToKEqualSubsets {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0, max = 0;
        for(int n : nums){
            sum+=n;
            max = Math.max(n,max);
        }
        if((sum%k)!=0 || max>(sum/k)) return false;
        return backTrack(k,nums,new boolean[nums.length],0,sum/k);
    }
    private boolean backTrack(int k, int[] nums, boolean[] visited, int sum2n, int target){
        if(sum2n>target) return false;
        if(sum2n==target){
            if(k==1)
                return true;
            return backTrack(k-1,nums,visited,0,target);
        }
        for(int i=0;i<nums.length;i++){
            if(!visited[i]){
                visited[i] = true;
                if(backTrack(k,nums,visited,sum2n+nums[i],target)) return true;
                visited[i] = false;
            }
        }
        return false;
    }
    public static void main(String[] args){
       PartitionToKEqualSubsets p = new PartitionToKEqualSubsets();
       boolean res = p.canPartitionKSubsets(
               new int[]{18,20,39,73,96,99,101,111,114,190,207,295,471,649,700,1037},
        4
       );
       int i = 0;
    }
}
