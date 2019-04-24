
public class findMedianSortedArrays {
    public double findMeSoAr(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length)
            return findMeSoAr(nums2,nums1);
       return tryCut(nums1,nums2,2* nums1.length);
    }
    public double tryCut(int[] nums1,int[] nums2,int cut1) {
        int l1, r1, l2, r2;
        int lo = 0;
        int hi = cut1;
        while (true) {
            int cut2 = nums1.length + nums2.length - cut1;
            if (cut1 == 0)
                l1 = Integer.MIN_VALUE;
            else
                l1 = nums1[(cut1 - 1) / 2];
            if (cut1 == 2 * nums1.length)
                r1 = Integer.MAX_VALUE;
            else
                r1 = nums1[(cut1) / 2];
            if (cut2 == 0)
                l2 = Integer.MIN_VALUE;
            else
                l2 = nums2[(cut2 - 1) / 2];
            if (cut2 == 2 * nums2.length)
                r2 = Integer.MAX_VALUE;
            else
                r2 = nums2[(cut2) / 2];
            if (l1 <= r2 && l2 <= r1)
                break;
            else if (l1 > r2)
                hi = cut1;
            else
                lo = cut1;
            cut1 = lo + (hi - lo)/2;
        }
        return (double)(Math.max(l1,l2)+Math.min(r1,r2))/2;
    }
    public static void main(String[] args){
        double result = new findMedianSortedArrays().findMeSoAr(new int[]{1,2,3,4},new int[]{7});
        result = new findMedianSortedArrays().findMeSoAr(new int[]{1,3},new int[]{2,4,5});
        result = new findMedianSortedArrays().findMeSoAr(new int[]{1,4,5},new int[]{2,3,6});
        int i = 0;
    }
}
