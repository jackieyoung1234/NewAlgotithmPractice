import Interval.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class EmployeeFreeTime {
    //todo sort by length then merge
    //todo bottom up binary merge, no implicit call stack
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        int sl = schedule.size();
        List<Interval> totalWorking = merge(schedule,0,sl-1);
        int lenm1 = totalWorking.size()-1;
        List<Interval> res = new ArrayList<>(lenm1);
        for(int i=0;i<lenm1;){
            res.add(new Interval(totalWorking.get(i).end,totalWorking.get(++i).start));
        }
        return res;

    }
    private List<Interval> merge(List<List<Interval>> s,int l, int r){
        if(l==r) return s.get(l);
        int mid = l+(r-l)/2;
        List<Interval> li = merge(s,l,mid);
        List<Interval> ri = merge(s,mid+1,r);
        return merge2(li,ri);
    }
    private List<Interval> merge2(List<Interval> l,List<Interval> r){
        List<Interval> res = new ArrayList<>();
        int pl = 0, pr =0, llen=l.size(), rlen = r.size();
        Interval ls = l.get(0),rs = r.get(0);
        Interval pre;
        if(ls.start<=rs.start){
            pre = ls;
            ls = ++pl>=llen?null:l.get(pl);
        }else{
            pre = rs;
            rs = ++pr>=rlen?null:r.get(pr);
        }
        res.add(pre);
        while(pl<llen&&pr<rlen){
            if(ls.start<=rs.start){
                if(ls.start<=pre.end){
                    pre.end=Math.max(pre.end,ls.end);
                }else{
                    //todo make sure no need to clone
                    pre = ls;
                    res.add(pre);
                }
                //todo priority after : remove redundant pointer boundary check
                if(++pl>=llen) break;
                ls = l.get(pl);
            }else{
                if(rs.start<=pre.end){
                    pre.end=Math.max(pre.end,rs.end);
                }else{
                    //todo make sure no need to clone
                    pre = rs;
                    res.add(pre);
                }
                //todo priority after : remove redundant pointer boundary check
                if(++pr>=rlen) break;
                rs = r.get(pr);
            }
        }
        while(pl<llen){
            if(ls.start<=pre.end){
                pre.end=Math.max(pre.end,ls.end);
            }else{
                //todo make sure no need to clone
                pre = ls;
                res.add(pre);
            }
            if(++pl>=llen) break;
            ls =l.get(pl);
        }
        while(pr<rlen){
            if(rs.start<=pre.end){
                pre.end=Math.max(pre.end,rs.end);
            }else{
                //todo make sure no need to clone
                pre = rs;
                res.add(pre);
            }
            if(++pr>=rlen) break;
            rs =r.get(pr);
        }
        return res;
    }
    public void f(HashSet<Character>[] ha){

    }
    public static void main(String[] args){
        HashSet<Character>[] ha = new HashSet[9];
       EmployeeFreeTime e = new EmployeeFreeTime();
       List<Interval> res = e.employeeFreeTime(
               Arrays.asList(
                      Arrays.asList(new Interval(1,2),new Interval(5,6)),
                      Arrays.asList(new Interval(1,3)),
                      Arrays.asList(new Interval(4,10))
               )
       );
    }
}
