import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import Interval.Interval;

public class mergeInteval {
    private class myComp implements Comparator<Interval>{
       @Override
       public int compare(Interval i1,Interval i2){
          return i1.start-i2.start;
       }
    }
    public List<Interval> merge(List<Interval> intervals) {
        return null;
    }
    public List<Interval> merge_badVersion_notWorking(List<Interval> intervals) {
        ArrayList<Interval> result = new ArrayList<>();
        for(Interval k : intervals){
           int lo = 0, hi = result.size()-1,mid = 0;
           Interval toComp = new Interval();
           while(lo <= hi) {
               mid = lo + (hi-lo)/2;
               toComp = result.get(mid);
               int compR = Integer.compare(toComp.end,k.start);
               int compL = Integer.compare(toComp.start,k.end);
               if(compR<0){
                  lo = mid + 1;
               }else if(compL>0){
                  hi = mid - 1;
               }else{
                  break;
               }
           }
           if(lo > result.size()-1) result.add(k);
           else if(hi < 0) result.add(0,k);
           else{
               Interval New = new Interval(Math.min(toComp.start,k.start),Math.max(toComp.end,k.end));
               result.remove(mid);
               result.add(mid,New);
               //try merge
               helper(result,mid,New);
           }
        }
        return result;
    }
    private void helper(ArrayList<Interval> result, int inde, Interval New){
       int size = result.size();
       int newInde = -1;
       if(size==1) return;
       if(inde == 0) {
           Interval i1 = result.get(1);
           if(i1.start > New.end)
               return;
           result.remove(1);
           result.remove(0);
           result.add(0,new Interval(Math.min(i1.start,New.start),Math.max(i1.end,New.end)));
           newInde = 0;
       }
       else if(inde == size-1){
           Interval i1 = result.get(inde-1);
           if(i1.end < New.start)
               return;
           result.remove(inde);
           result.remove(inde-1);
           result.add(inde-1,new Interval(Math.min(i1.start,New.start),Math.max(i1.end,New.end)));
           newInde = inde-1;
       }else{
           Interval l = result.get(inde-1);
           Interval r = result.get(inde+1);
           if(r.start <= New.end ){
               result.remove(inde+1);
               result.remove(inde);
               result.add(inde,new Interval(Math.min(r.start,New.start),Math.max(r.end,New.end)));
               newInde = inde;
           }
           if(l.end >= New.start){
                New = result.remove(inde);
                result.remove(inde-1);
                result.add(inde-1,new Interval(Math.min(l.start,New.start),Math.max(l.end,New.end)));
                newInde = inde-1;
           }
       }
       if(newInde>=0)
            helper(result,newInde,result.get(newInde));
    }
    public static void main(String[] args){
       Interval i1 = new Interval(2,3);
       Interval i2 = new Interval(4,5);
       Interval i3 = new Interval(6,7);
       Interval i4 = new Interval(8,9);
       Interval i5 = new Interval(1,10);
       List<Interval> res =  new mergeInteval().merge( Arrays.asList(new Interval[]{i1,i2,i3,i4,i5})) ;
        i1 = new Interval(4,5);
        i2 = new Interval(2,4);
        i3 = new Interval(4,6);
        i4 = new Interval(3,4);
        i5 = new Interval(0,0);
        Interval i6 = new Interval(1,1);
        Interval i7 = new Interval(3,5);
        Interval i8 = new Interval(2,2);
        res =  new mergeInteval().merge( Arrays.asList(new Interval[]{i1,i2,i3,i4,i5,i6,i7,i8})) ;
    }
}
