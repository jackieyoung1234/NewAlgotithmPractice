import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class QuickSort {
    public void sort(int[] a){
        Random r = new Random();
        helper(a,0,a.length-1,new Random());
    }
    private void swap(int[] a, int l, int r){
       int t = a[l];
       a[l] = a[r];
       a[r] = t;
    }
    private int partition(int[] a, int l , int r){
       int i = l, j = r+1,target = a[l];
       while(true){
          while(a[++i]<=target){
              if(i==r)break;
          }
          while(a[--j]>=target){
              if(j==l) break;

          }
          if(i>=j)break;
          swap(a,i,j);
       }
       swap(a,l,j);
       return j;
    }
    private void helper(int[] a, int l, int r, Random ran){
       if(l>=r) return;
       swap(a,l,l+ran.nextInt(r-l+1));
       int j = partition(a,l,r);
       helper(a,l,j-1,ran);
       helper(a,j+1,r,ran);
    }
    private boolean check(int[] a){
       for(int i=0;i<a.length-1;i++){
          if(a[i]>a[i+1]) return false;
       }
       return true;
    }
    public static void main(String[] args){
        StringBuilder sb1 = new StringBuilder("paa");
        StringBuilder sb2 = new StringBuilder("apa");
        StringBuilder[] aa = new StringBuilder[]{sb1,sb2};
        Arrays.sort(aa);
        Random r = new Random();
        QuickSort q = new QuickSort();
        for(int i=0;i<10;i++){
           int[] a = new int[200];
           for(int ii=0;ii<200;ii++){
              a[ii] = r.nextInt(1000);
           }
           q.sort(a);
           System.out.println(q.check(a));
        }
    }
}
