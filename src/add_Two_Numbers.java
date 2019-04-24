import java.util.Objects;

public class add_Two_Numbers {
 // Definition for singly-linked list.
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
       return add1Node(0,l1,l2);
    }
    public int safeGetValue(ListNode E){
        try {
            return E.val;
        }catch(NullPointerException ex){
            return 0;
        }
    }
    public boolean checkNextNull(ListNode N1){
        try {
            return N1.next != null;
        }catch(NullPointerException ex){
            return false;
        }
    }
    public ListNode safeNext(ListNode N1){
        try {
            return N1.next;
        }catch(NullPointerException ex){
            return null;
        }
    }
    public ListNode add1Node(int carry,ListNode N1,ListNode N2){
        if(N1 == null && N2 == null)
            return new ListNode(carry);
        int k = safeGetValue(N1) + safeGetValue(N2);
        ListNode cur = new ListNode((carry + k)%10 );
        if(checkNextNull(N1)|| checkNextNull(N2) || ((k + carry)/10)==1)
            cur.next = add1Node((k+carry)/10,safeNext(N1),safeNext(N2));
        return cur;
    }
    public static void main(String [] args){
        ListNode le = new ListNode(3);
        le.next = new ListNode(8);
        le.next.next = new ListNode(2);
        ListNode ri = new ListNode(5);
        ri.next = new ListNode(3);
        ri.next.next = new ListNode(7);
        ListNode ne = new add_Two_Numbers().addTwoNumbers(le,ri);
        int i =0;
        le = new ListNode(9);
        le.next = new ListNode(9);
        ri = new ListNode(1);
        ne = new add_Two_Numbers().addTwoNumbers(le,ri);
        i = Character.getNumericValue('2');
        i = Character.getNumericValue('u');
        i = Character.getNumericValue('3');
        i = Character.getNumericValue('9');
        i = Character.getNumericValue('#');
        int[][] exp = new int[2][2];
        exp[0][0] = 1;
        exp[0][1] = 2;
        exp[1][0] = 3;
        exp[1][1] = 4;
        int[] kk = exp[0];
    }
}
