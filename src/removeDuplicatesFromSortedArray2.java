public class removeDuplicatesFromSortedArray2 {
    public int removeDuplicates(int[] nums) {
       int bk = 0;
       for(int n:nums){
            if(bk<3||n>nums[bk-3])
                nums[bk++] = n;
       }
       return bk;
    }
    public int removeDuplicatesOriginal(int[] nums) {
        if(nums.length<=2) return nums.length;
        int i =2, ofs = 0;
        for(;i<nums.length;i++) {
            //?? conner case i-2 = -1
            if(nums[i]!= nums[i-1]||nums[i-1]!=nums[i-2]) {
                if(ofs!=0)
                    nums[i-ofs]=nums[i];
            }
            else{
                ofs++;
                while(i+1<nums.length&&nums[i]==nums[i+1]){
                    i++;
                    ofs++;
                }
            }
        }
        return nums.length -ofs;
    }
    public static void main(String[] args){
       int ii = new removeDuplicatesFromSortedArray2().removeDuplicates(new int[]{1,1,1,2,2,3});
       ii = new removeDuplicatesFromSortedArray2().removeDuplicates(new int[]{1,1,1,1,2,3,3,3,3,4,4,6,7,7,7,7});
       ii = new removeDuplicatesFromSortedArray2().removeDuplicates(new int[]{1,1,1,2,2,3});
       ii = new removeDuplicatesFromSortedArray2().removeDuplicates(new int[]{1,1,1,2,2,3});
    }
}
