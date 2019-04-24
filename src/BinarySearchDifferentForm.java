import java.util.Arrays;

public class BinarySearchDifferentForm {
    /** note :
     * only first >/>=, and   last </<= should use mutant bsearch
     * because    suppose >/>= sequence starts from [i], [i+1], ... [last] for first >/>=,    or i = len, for no such index
     *</<= sequence ends at i : [0] [1]... [i] or i = -1 for no such index
     * for first </<=, only needs to check [0]
     * for last >/>=, only needs to check [last]
     */

    /**
     * last index i, [i]<target
     * if no such index, return -1
     * assumes arr not null or empty
     */
    public static int findLastIndexSmallerThan(int[] arr, int target){
       if(arr==null || arr.length == 0) throw new IllegalArgumentException();
       int lo = 0, hi = arr.length-1;
       int mid = 0, comp = 0;
       while(lo<hi){
           mid = lo + (hi-lo+1)/2;
           comp = Integer.compare(arr[mid],target);
           if(comp>=0) hi = mid-1;
           else lo = mid;
       }
       return arr[lo]<target?lo:-1;
    }

    /**
     * assumes arr not null or empty
     * if no such index,return arr.length
     */
    public static int findLastIndexLargerThan(int[] arr, int target){
        if(arr==null || arr.length == 0) throw new IllegalArgumentException();
        return arr[arr.length-1]>target?arr.length-1:arr.length;
    }
    /**
     * if no such index, return -1
     * assumes arr not null or empty
     */
    public static int findFirstIndexSmallerThan(int[] arr, int target){
       if(arr==null || arr.length == 0) throw new IllegalArgumentException();
       return arr[0]<target?0:-1;
    }

    /**
     * assumes arr not null or empty
     * if no such index,return arr.length
     */
    public static int findFisrtIndexLargerThan(int[] arr, int target){
        if(arr==null || arr.length == 0) throw new IllegalArgumentException();
        int lo = 0, hi = arr.length;
        int mid = 0, comp = 0;
        while(lo<hi){
           mid = lo + (hi-lo)/2;
           comp = Integer.compare(arr[mid],target);
           if(comp<=0) lo = mid+1;
           else hi = mid;
        }
        return hi;
    }
    public static int bsearch(int[] arr, int lo, int hi, int target){
       int mid = 0,comp = 0;
       while(lo<=hi){
           mid = lo+(hi-lo)/2;
           comp = Integer.compare(arr[mid],target);
           if(comp==0)return mid;
           else if(comp>0) hi = lo-1;
           else lo = lo+1;
       }
       return -1;
    }
    public static void main(String[] args){
        int test = Arrays.binarySearch(new int[]{1,3,5,7},4);
        test = Arrays.binarySearch(new int[]{1,3,5,7},8);
        test = Arrays.binarySearch(new int[]{1,3,5,7},0);
        int res = findLastIndexSmallerThan(new int[]{
                2,2,3,3,4
                },
                5
                );

        res = findLastIndexSmallerThan(new int[]{
                        2,2,3,3,
                },
                5
        );
        res = findLastIndexSmallerThan(new int[]{
                        2,2,3,3,4
                },
                1
        );
        res = findLastIndexSmallerThan(new int[]{
                        2,2,3,3,
                },
                1
        );
        res = findLastIndexSmallerThan(new int[]{
                        2,2,3,3,
                },
                3
        );
        res = findLastIndexSmallerThan(new int[]{
                        2,2,2,3,3,
                },
                3
        );



        res = findLastIndexLargerThan(new int[]{
                        2,2,3,3,4
                },
                5
        );
        res = findLastIndexLargerThan(new int[]{
                        2,2,3,3,
                },
                5
        );
        res = findLastIndexLargerThan(new int[]{
                        2,2,3,3,4
                },
                1
        );
        res = findLastIndexLargerThan(new int[]{
                        2,2,3,3,
                },
                1
        );
        res = findLastIndexLargerThan(new int[]{
                        2,2,3,3,
                },
                2
        );
        res = findLastIndexLargerThan(new int[]{
                        2,2,2,3,3,
                },
                2
        );




        res = findFirstIndexSmallerThan(new int[]{
                        2,2,3,3,4
                },
                5
        );
        res = findFirstIndexSmallerThan(new int[]{
                        2,2,3,3,
                },
                5
        );
        res = findFirstIndexSmallerThan(new int[]{
                        2,2,3,3,4
                },
                1
        );
        res = findFirstIndexSmallerThan(new int[]{
                        2,2,3,3,
                },
                1
        );
        res = findFirstIndexSmallerThan(new int[]{
                        2,2,3,3,
                },
                2
        );
        res = findFirstIndexSmallerThan(new int[]{
                        2,2,2,3,3,
                },
                2
        );


        res = findFisrtIndexLargerThan(new int[]{
                        2,2,3,3,4
                },
                5
        );
        res = findFisrtIndexLargerThan(new int[]{
                        2,2,3,3,
                },
                5
        );
        res = findFisrtIndexLargerThan(new int[]{
                        2,2,3,3,4
                },
                1
        );
        res = findFisrtIndexLargerThan(new int[]{
                        2,2,3,3,
                },
                1
        );
        res = findFisrtIndexLargerThan(new int[]{
                        2,2,3,3,
                },
                2
        );
        res = findFisrtIndexLargerThan(new int[]{
                        2,2,2,3,3,
                },
                2
        );
    }

}