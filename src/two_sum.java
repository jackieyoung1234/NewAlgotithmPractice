public class two_sum {
    public int[] twoSum(int[] nums, int target) {
        sort(nums);
        int result;
        for (int index = 0; index < nums.length; index++) {
            if (target == 2 * nums[index])
                continue;
            if((result = index_BS(target - nums[index], nums))!= -1)
                return new int[]{index, result};
        }
        return null;
    }
    public void sort(int[] nums){
       int[] aux = new int[nums.length];
       mergeSort(aux,nums,0,nums.length-1);
    }

    public void mergeSort(int [] aux, int[] nums, int lo,int hi){
       if(hi<=lo)
           return;
       int mid = lo + (hi - lo)/2;
       mergeSort(aux,nums,lo,mid);
       mergeSort(aux,nums,mid+1,hi);
       merge(aux,nums,lo,mid,hi);
    }
    public void merge(int [] aux, int[] arr, int lo,int mid, int hi){
        int m = lo;
        int n = mid + 1;
        for(int i = lo; i <= hi; i ++){
           aux[i]  = arr[i];
        }
        for(int i = lo; i <= hi; i ++){
           if(m > mid) arr[i]= aux[n++];
           else if(n > hi) arr[i] = aux[m++];
           else if(aux[m]<aux[n]) arr[i] = aux[m++];
           else arr[i] = aux[n++];
        }
    };
    public int index_BS(int key, int[] num) {
        int lo = 0;
        int hi = num.length - 1;
        int mid;
        while (hi >=lo) {
            mid = lo + (hi - lo) / 2;
            if (key == num[mid])
                return mid;
            else if (key > num[mid])
                lo = mid+1;
            else
                hi = mid-1;
        }
        return -1;
    }
    public static void main(String [] args){
        int[] ss = new two_sum().twoSum(new int[]{3,19,29,54,1,22,12,4,8},13);
        int i = 0;
        if((i = 2) != -1)
            System.out.println((i=2));
        int[] s = {1,2};
        i = s[(0-1)/2];
        i = (-1)/2;
    }
}
