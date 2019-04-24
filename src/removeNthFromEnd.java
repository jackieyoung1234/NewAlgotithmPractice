import java.util.ArrayList;

public class removeNthFromEnd {
    public ListNode removeNthFromEnd_OldVersion(ListNode head, int n) {
        ArrayList<ListNode> tempList = new ArrayList<>();
        ListNode cur = head;
        while(cur != null){
            tempList.add(cur);
            cur = cur.next;
        }
        int size = tempList.size();

        if(n == size){
            return head.next;
        }
        int index = size - n;
        if(n == 1)
            tempList.get(index-1).next = null;
        else
            tempList.get(index -1).next = tempList.get(index + 1);
        return head;
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode sentinal = new ListNode(0);
        sentinal.next = head;
        ListNode end = sentinal, start = sentinal;
        for(int i = 0; i < n+1; i++)
            end = end.next;
        while(end != null){
            end = end.next;
            start = start.next;
        }
        start.next = start.next.next;
        return sentinal.next;
    }
    public static void main(String[] args){
        removeNthFromEnd my = new removeNthFromEnd();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head = new removeNthFromEnd().removeNthFromEnd(head,2);
        int i = 0;
    }
}

