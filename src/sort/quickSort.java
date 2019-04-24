package sort;

import java.util.Arrays;
import java.util.LinkedList;

public class quickSort {
    private static void exch(int[] a, int i, int j){
       a[i] ^= a[j];
       a[j] ^= a[i];
       a[i] ^= a[j];
    }
    public static void main(String[] args){
       exch(new int[]{0,1,2,3,4},1,4);
       String[] tt = ",".split("\\,",0);
       LinkedList<String> ll = new LinkedList<String>(Arrays.asList(tt));
       double ii = 3 - 7/(double)2;
    }
}
