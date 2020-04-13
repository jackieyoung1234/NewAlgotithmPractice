import static org.junit.Assert.assertEquals;

import java.util.Arrays;

public class IndexPQ {

    public static class ListNode{
       int val;
       ListNode next;
       public ListNode(int x){
           this.val = x;
       }
        public ListNode(int x,ListNode n){
            this.val = x;
            this.next = n;
        }
    }
    int[] ki2ai;
    int[] ai2ki;
    ListNode[] ki2v;
    int ptr;
    public IndexPQ(ListNode[] l){
        int s = l.length;
        ki2ai = new int[s+1];
        ai2ki = new int[s+1];
        ki2v = new ListNode[s+1];
        this.ptr = 1;
        for(ListNode n : l){
            if(n!=null) this.add(n);
        }
    }
    public void add(ListNode r){
        ki2v[ptr] = r;
        ki2ai[ptr] = ptr;
        ai2ki[ptr] = ptr;
        swim(ptr++);
    }
    //this is guaranteed to be called only when pq is not empty
    //if min.next!=null replace
    //else remove size--
    public int removeMin(){
        int res = ki2v[ai2ki[1]].val;
        //change(1);
        change();
        return res;
    }
    private boolean smaller(int i, int j){
        return ki2v[ai2ki[i]].val < ki2v[ai2ki[j]].val;
    }
    private void exch(int i, int j){
        int temp = ai2ki[i];//2
        ai2ki[i] = ai2ki[j];
        ai2ki[j] = temp;
        ki2ai[temp] = j;
        ki2ai[ai2ki[i]] = i;
    }
    private void swim(int i){
        int pa = 0;
        while(i>1){
            if(smaller(i,pa=(i>>>1))){
                exch(i,pa);
            }
            i = pa;
        }
    }
    private void sink(int i){
        int chi;// = (i<<1);
        while((chi=(i<<1))<this.ptr){
            if(chi+1<this.ptr&&smaller(chi+1,chi)) chi++;
            if(smaller(chi,i)){
                exch(chi,i);
                i = chi;
            }else break;
        }
    }
    public boolean isEmpty(){
        return this.ptr ==1;
    }
    public void change(){
        int minki = ai2ki[1];
        ListNode minNode = ki2v[minki];
        if(minNode.next!=null){
            ki2v[minki] = minNode.next;
            sink(1);
        }else{
            int lastki =  ai2ki[--this.ptr];
            ai2ki[1] = lastki;
            ki2ai[lastki] = 1;
            sink(1);
        }
    }
        public static void main(String[] args){
            IndexPQ i = new IndexPQ(new ListNode[]{
                 new ListNode(4,new ListNode(2)),
                 null,
                 new ListNode(1,new ListNode(4)),
                 new ListNode(3, new ListNode(1)),
            });
            assertEquals(1,i.removeMin());
            assertEquals(3,i.removeMin());
            assertEquals(1,i.removeMin());
            assertEquals(4,i.removeMin());
            assertEquals(4,i.removeMin());
            assertEquals(2,i.removeMin());
            assertEquals(true,i.isEmpty());
        }
}
