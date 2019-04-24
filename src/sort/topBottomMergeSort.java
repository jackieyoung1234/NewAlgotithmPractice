package sort;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.arraycopy;

public class topBottomMergeSort {

    public static void mergeSortUB(int[] target){
        mergeSortUBHelper(target,0,target.length-1);
    }
    private static void mergeSortUBHelper(int[] target, int lo, int hi){
        // error handling??
        if(lo > hi) throw new IllegalArgumentException("target size illegal");
        if(lo == hi) return;
        int mid = (hi - lo)/2 + lo;
        sort(target,lo,mid);
        sort(target,mid + 1,hi);
        merge(target, lo, mid, hi);
    }
    private static void sort(int[] target, int st, int end){
        if(st >= end) return;
        int mid = (end - st)/2 + st;
        sort(target,st,mid);
        sort(target,mid + 1,end);
        merge(target,st,mid,end);
    }
    private static void merge(int[] target, int lo, int mid, int hi){
        int[] backUp = new int[hi - lo + 1];
        int i = lo,j = mid+1, index = 0;
        while(index < (hi-lo+1)){
           if(i >mid) backUp[index++] = target[j++];
           else if(j >hi) backUp[index++] = target[i++];
           else {
                int comp = Integer.compare(target[i],target[j]);
                if(comp <=0) backUp[index++] = target[i++];
                else         backUp[index++] = target[j++];
           }
        }
        arraycopy(backUp,0,target,lo,hi - lo + 1);
    }
    public static void main(String[] args){
        int[] ss = new int[]{-1,0,1,2};
        ArrayList<Integer> ll = new ArrayList<>();
        ll.add(1);
        Integer ii =  ll.get(0);
        ii = ii +  2;
        new topBottomMergeSort().mergeSortUB(ss);
        ss = new int[]{-1,0,1,2,-1,-4};
        new topBottomMergeSort().mergeSortUB(ss);
        int i = 1;
    }


}
