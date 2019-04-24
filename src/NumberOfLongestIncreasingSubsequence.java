import java.util.Map;
import java.util.TreeMap;

public class NumberOfLongestIncreasingSubsequence {
    public int findNumberOfLIS(int[] nums) {
            int len = nums.length;
            //todo: edge case len=0
            if(len==0) return 0;
            //int num -> cumulative cnt
            int[] bk = new int[len];
            TreeMap<Integer, Integer>[]  tm = new TreeMap[len];
            tm[0]=new TreeMap<>();
            bk[0]=nums[0];
            tm[0].put(bk[0],1);
            int ptr = 0;
            for(int i=1;i<len;i++){
               tm[i] = new TreeMap<>();
               int cur = nums[i];
               int comp = Integer.compare(bk[ptr],cur);
               if(comp<0){
                   bk[++ptr]=cur;
                   tm[ptr].put(cur,findCumucnt(cur,ptr-1,tm));
               }else if(comp==0){
                  tm[ptr].merge(cur,findCumucnt(cur,ptr-1,tm),(old,newv)->old+newv);
                  continue;
               }else{
                  int index = findLastSmall(bk,ptr,cur);
                  bk[++index] = cur;
                  //todo : change to put to see time
                  tm[index].merge(cur,findCumucnt(cur,index-1,tm),(old, newv)->old+newv);
               }
            }
            int res = 0;
            for(Map.Entry<Integer,Integer> me : tm[ptr].entrySet()){
                res+= me.getValue();
            }
            return res;
        }
        private int findLastSmall(int[] a, int hi, int t){
           int lo =0;
           while(lo<hi){
              int mid = lo+(hi-lo+1)/2;
              int comp = Integer.compare(a[mid],t);
              if(comp>=0) hi=mid-1;
              else lo=mid;
           }
           return a[lo]<t?lo:-1;
        }
        private int findCumucnt(int target, int preindex, TreeMap<Integer,Integer>[] tar){
           if(preindex<0) return 1;
           int res = 0;
           for(Map.Entry<Integer, Integer> me: tar[preindex].headMap(target).entrySet()){
               res+=me.getValue();
           }
           return res;
        }
        public static void main(String[] args){
            NumberOfLongestIncreasingSubsequence nli = new NumberOfLongestIncreasingSubsequence();
            int res = nli.findNumberOfLIS(
                    new int[]{16,5,8,6,1,10,5,2,15,3,2,4,1}
            );
            res = nli.findNumberOfLIS(
                    new int[]{2,2,2,2}
            );
            res = nli.findNumberOfLIS(
                    new int[]{1,1,2,2,3,3}
            );
        }

}
