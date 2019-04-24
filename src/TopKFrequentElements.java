import java.util.*;

public class TopKFrequentElements {
    private class Entry {
       int index;
       int cnt;
       public Entry(int val, int times){
          index = val;
          cnt = times;
       }
       public void incre(){
           cnt++;
       }
    }
    public List<Integer> topKFrequent(int[] nums, int k) {
       HashMap<Integer,Entry> val2index = new HashMap<>();
       Random rand = new Random();
       for(int i=0;i<nums.length;i++) {
           if(!val2index.containsKey(nums[i]))
               val2index.put(nums[i],new Entry(nums[i],1));
           else
               val2index.get(nums[i]).incre();
       }
       Entry[] ear = new Entry[val2index.size()];
       int i = 0;
       for(Entry e:val2index.values()){
           ear[i++]=e;
       }
       quickSortGet(ear,ear.length,k-1,rand);
       List<Integer> res = new LinkedList<>();
       for(i=0;i<k;i++){
          res.add(ear[i].index);
       }
       return res;
    }

    private void quickSortGet(Entry[] array, int arraylen, int k, Random rand){
       int lo =0, hi = arraylen-1;
       int mid = 0;
       while(lo<hi){
          mid = partition(array,lo,hi,rand);
          int comp = Integer.compare(mid,k);
          if(comp==0) break;
          else if(comp>0) hi=mid-1;
          else lo = mid+1;
       }
    }
    private int partition(Entry[] array, int lo, int hi,Random rand){
       int i = lo, j = hi+1;
       int target = shuffle(array,lo,hi,rand);
       while(true){
          while(array[++i].cnt>target)
              if(i==hi) break;
          while(array[--j].cnt<target)
              if(j==lo) break;
          if(i>=j) break;
          exch(array,i,j);
       }
       exch(array,lo,j);
       return j;
    }
    private int shuffle(Entry[] array, int lo, int hi, Random rand){
       int randindex = rand.nextInt(hi-lo+1)+lo;
       exch(array,lo,randindex);
       return array[lo].cnt;
    }
    private void exch(Entry[] array, int i, int j){
       Entry temp = array[i];
       array[i] = array[j];
       array[j] = temp;
    }
    public static void main(String[] args){
       TopKFrequentElements tffe = new TopKFrequentElements();
       List<Integer> res = tffe.topKFrequent(
               new int[]{1,1,1,2,2,3}, 2
       );
       char[] ca = new char[]{'a','b','c','d'};
       String s = new String(ca,1,3);
    }
}
