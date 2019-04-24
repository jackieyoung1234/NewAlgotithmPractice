public class removeDuplicates {
    public int removeDuplicates_MyVersion(int[] nums) {
        int len = nums.length;
        if(len == 0) return 0;
        int i = 0,j=1;
        while(i<len && j < len) {
            if(nums[i]!=nums[j]){
                if(j-i==1){i++;j++;}
                else{nums[++i] = nums[j++];}
            }
            else{j++;}
        }
        return i+1;
    }
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for(int t : nums){
            if(i == 0 || t>nums[i-1]){
                nums[i++] = t;
            }
        }
        return i;
    }
    public static void main(String[] args){
       int[][] s = new int[2][2];
       s[0][0] = 0;
       s[0][1] = 1;
       System.arraycopy(s[0],0,s[1],0,2);
       int i = 0;
    }
}
