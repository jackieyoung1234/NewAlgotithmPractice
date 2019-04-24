public class wiggleSort {
    public void wiggleSort(int[] nums) {
        boolean up = true;
        for(int i=0;i<nums.length-1;){
           if(
             //((nums[i]>nums[i+1])&&up)
             //||
             //((nums[i]<nums[i+1])&&!up)
             (nums[i]<nums[i+1])  != up
             ){
               nums[i+1] ^=nums[i];
               nums[i] ^=nums[i+1];
               nums[i+1] ^=nums[i++];
           }
           up = !up;
        }
    }
    public static void main(String[] args){
       int[] test = new int[]{3,5,2,1,6,4};
       new wiggleSort().wiggleSort(test);
    }
}
