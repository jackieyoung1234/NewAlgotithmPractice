public class MaxConsecutiveOnesIII {
    public int longestOnes(int[] A, int K) {
        int left = 0, right = 0;
        for (; right < A.length; right++) {
            if (0 == A[right]) K--;
            if (K < 0 && 0 == A[left++]) K++;
        }

        return right - left;
    }
    public static void main(String[] args){
       MaxConsecutiveOnesIII m = new MaxConsecutiveOnesIII();
       int res = m.longestOnes(new int[]{1,1,1,1,1,1,0,0,1,1,1,0,1,1,0,1,1},2);
    }
}
