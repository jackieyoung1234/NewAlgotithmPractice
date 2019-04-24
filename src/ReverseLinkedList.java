public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        return helper(null,head);
    }
    private ListNode helper(ListNode prev, ListNode cur){
        if(cur == null) return prev;
        ListNode nex = cur.next;
        cur.next = prev;
        return helper(cur,nex);
    }
    public static void main(String[] args){
        int i= 1_000_000_000;
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        n1.next = n2;
        int[] ii = new int[]{1,2};
        ListNode nn = new ReverseLinkedList().reverseList(n1);
    }
}
