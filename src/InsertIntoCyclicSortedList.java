public class InsertIntoCyclicSortedList {
    public ListNode insert(ListNode head, int insertVal) {
        ListNode newn = new ListNode(insertVal);
        newn.next = newn;
        if(head==null){
            return newn;
        }
        ListNode n = head;
        int nval = n.val, nnextval = n.next.val;
        while(!(
                (nval<=insertVal&&nnextval>=insertVal)||
                        (
                                nval>nnextval&&
                                        (
                                                insertVal<nnextval
                                                        ||
                                                        insertVal>nval
                                        )
                        )
        )
        &&n.next!=head){
            n = n.next;
            nval = n.val;
            nnextval = n.next.val;
        }
        newn.next = n.next;
        n.next = newn;
        return head;
    }
    public static void main(String[] args){
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n1;
        InsertIntoCyclicSortedList i = new InsertIntoCyclicSortedList();
        ListNode res = i.insert(n2,2);
        n1 = new ListNode(1);
        n2 = new ListNode(3);
        n3 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n1;
        res = i.insert(n2,5);
        n1 = new ListNode(1);
        n2 = new ListNode(1);
        n1.next = n2;
        n2.next = n1;
        res = i.insert(n1,5);
    }
}
