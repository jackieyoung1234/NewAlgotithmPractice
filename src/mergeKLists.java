import java.util.Comparator;
import java.util.PriorityQueue;

public class mergeKLists {
    private class nodeComparator implements Comparator<ListNode>{
        public int compare(ListNode l1, ListNode l2){
            return Integer.compare(l1.val,l2.val);
        }
    }
    public ListNode mergeKLists_prioQueueVersion(ListNode[] lists){
        if(lists.length == 0)
            return null;
        int size = lists.length;
        ListNode res = new ListNode(0),st = res;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(size,new nodeComparator());
        for(ListNode l : lists){
            if(l != null)
                pq.add(l);
        }
        while(!pq.isEmpty()){
            ListNode temp = pq.poll();
            res.next = temp;
            res = res.next;
            if(temp.next != null) pq.add(temp.next);
        }
        return st.next;
    }
    private ListNode merge2List(ListNode l1, ListNode l2){
        ListNode st = new ListNode(0);
        ListNode res = st;
        while(l1 != null || l2 != null){
            if(l1 == null) {res.next = l2; break;}
            else if(l2 == null) {res.next = l1; break;}
            else if(l1.val <= l2.val){res.next = l1; l1 = l1.next;}
            else{res.next = l2; l2 = l2.next;}
            res = res.next;
        }
        return st.next;
    }

    private ListNode mergeKList(ListNode[] lists, int lo, int hi){
        if(lo == hi) return lists[lo];
        int mid = lo + (hi - lo)/2;
        ListNode l = mergeKList(lists, lo, mid);
        ListNode r = mergeKList(lists, mid+1, hi);
        return merge2List(l,r);
    }
    public ListNode mergeKLists(ListNode[] lists){
        if(lists.length == 0)
            return null;
        int size = lists.length;
        return mergeKList(lists,0,size-1);
    }
    public static void main(String[] args){
        int[] k = new int[]{0,1,2,3,4,5};
        int s = 3;
        k[s++] = k[s];
        k = new int[]{0,1,2,3,4,5};
        System.arraycopy(k,2,k,4,2);
        ListNode L1 = new ListNode(1);
        L1.next = new ListNode(2);
        L1.next.next = new ListNode(4);
        ListNode L2 = new ListNode(1);
        L2.next = new ListNode(3);
        L2.next.next = new ListNode(4);
        L2.next.next.next = new ListNode(7);
        ListNode[] lA = new ListNode[]{L1,L2};
        ListNode r = new mergeKLists().mergeKLists(lA);
        int i  = 0;
    }
}
