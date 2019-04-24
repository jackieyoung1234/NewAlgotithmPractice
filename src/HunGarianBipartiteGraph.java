import graph.Digraph;

import java.util.*;

public class HunGarianBipartiteGraph {
    Queue<Integer> q;
    int[] prev;
    int[] match;
    int[] check;
    HashMap<Integer, List<Integer>> g;
    int leftNodeNum;
    public HunGarianBipartiteGraph(HashMap<Integer,List<Integer>> g, int l){
        this.g = g;
        this.leftNodeNum = 3;
        q = new LinkedList<>();
        prev = new int[g.size()];
        match = new int[g.size()];
        check = new int[g.size()];
        Arrays.fill(prev,-1);
        Arrays.fill(match,-1);
        Arrays.fill(check,-1);
    }
    public int DFSBipatite(){
        int res = 0;
        for(int u=0;u<leftNodeNum;u++){
            if(match[u]==-1){
                if(dfsHelper(u))
                    res++;
                Arrays.fill(check,-1);
            }
        }
        return res;
    }
    private boolean dfsHelper(int u){
        for(int v: g.get(u)){
            if(check[v]==-1){
               check[v]=0;
               if(match[v]==-1||dfsHelper(match[v])){
                   match[v]= u;
                   match[u]= v;
                   return true;
               }
            }
        }
        return false;
    }
    public int BFSBipatite(){
        Queue<Integer> q = new LinkedList<>();
        int res = 0;
        for(int i=0;i<leftNodeNum;i++){
            while(!q.isEmpty()) q.clear();
            q.offer(i);
            //don't forget
            prev[i]= -1;
            boolean flag = false;
            while(!q.isEmpty()&&!flag){
                int u = q.peek();
                for(int v : g.get(u)){
                    if(check[v]!=i){
                       check[v] = i;
                       q.offer(match[v]);
                       if(match[v]>=0){
                            prev[match[v]] = u;
                       }else{
                           flag = true;
                           int t1 = u, t2 = v;
                           while(t1!=-1){
                               int temp = match[t1];
                               match[t1] = t2;
                               match[t2] = t1;
                               t1 = prev[t1];
                               t2 = temp;
                           }
                       }
                    }
                    if(flag) break;
                }
                q.poll();
            }
            if(match[i]!=-1)res++;
        }
        return res;
    }
    public void resetRes(){
        Arrays.fill(prev,-1);
        Arrays.fill(match,-1);
        Arrays.fill(check,-1);
    }
    public static void main(String[] args){
        HashMap<Integer,List<Integer>> g = new HashMap(6);
        g.computeIfAbsent(0,list->new LinkedList<>()).add(3);
        g.computeIfAbsent(3,list->new LinkedList<>()).add(0);
        g.computeIfAbsent(0,list->new LinkedList<>()).add(5);
        g.computeIfAbsent(5,list->new LinkedList<>()).add(0);
        g.computeIfAbsent(1,list->new LinkedList<>()).add(3);
        g.computeIfAbsent(3,list->new LinkedList<>()).add(1);
        g.computeIfAbsent(1,list->new LinkedList<>()).add(4);
        g.computeIfAbsent(4,list->new LinkedList<>()).add(1);
        g.computeIfAbsent(2,list->new LinkedList<>()).add(3);
        g.computeIfAbsent(3,list->new LinkedList<>()).add(2);
        HunGarianBipartiteGraph hbg = new HunGarianBipartiteGraph(g,3);
        int res = hbg.BFSBipatite();
        hbg.resetRes();
        res = hbg.DFSBipatite();
    }
}
