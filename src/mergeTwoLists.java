public class mergeTwoLists {
    public ListNode mergeTwoLists_recursiveVersion(ListNode L1, ListNode L2){
        if(L1 == null) return L2;
        if(L2 == null) return L1;
        if(L1.val <= L2.val) {
            L1.next = mergeTwoLists(L1.next,L2);
            return L1;
        }else{
            L2.next = mergeTwoLists(L1,L2.next);
            return L2;
        }
    }
    public ListNode mergeTwoLists(ListNode L1, ListNode L2){
        ListNode res = new ListNode(0);
        ListNode st = res;
        while(L1 != null || L2 != null){
            if(L1 == null )      {res.next = L2;break;}
            else if(L2 == null) {res.next = L1; break;}
            else if(L1.val <= L2.val) {res.next = L1; L1 = L1.next;}
            else {res.next = L2; L2 = L2.next;}
            res = res.next;
        }
        return st.next;
    }
    public static void main(String [] args){
       ListNode L1 = new ListNode(1);
       L1.next = new ListNode(2);
       L1.next.next = new ListNode(4);
       ListNode L2 = new ListNode(1);
       L2.next = new ListNode(3);
       L2.next.next = new ListNode(4);
       ListNode r = new mergeTwoLists().mergeTwoLists(L1,L2);
       int i = 0;
    }
}
