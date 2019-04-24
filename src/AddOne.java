public class AddOne {
    public ListNode addOne(ListNode n){
        ListNode sentinal = new ListNode(0);
        sentinal.next = n;
        ListNode pre = null, cur = sentinal;
        ListNode b9=null,f9=null,l9=null;
        while(cur!=null){
            if(cur.val == 9){
                b9=pre;
                f9=cur;
                //l9=cur;
                while(cur.next!=null&&cur.next.val==9){
                    cur=cur.next;
                }
                l9=cur;
            }
            pre = cur;
            cur = cur.next;
        }
        if(pre.val==9){
            b9.val++;
            f9.val=0;
            while(f9!=l9){
                f9=f9.next;
                f9.val=0;
            }
            l9.val=0;
        }else{
           pre.val++;
        }
        return sentinal.val==1?sentinal:sentinal.next;
    }
    protected static ListNode construct(int num){
        if(num==0) return new ListNode(0);
        ListNode pre = null, cur = null;
        while(num>0){
            int mod = num%10;
            num-=mod;
            num/=10;
            cur = new ListNode(mod);
            cur.next = pre;
            pre = cur;
        }
        return pre;
    }
    public static void main(String[] args){
        AddOne ao = new AddOne();
        //799
        ListNode n799 = ao.construct(799);
        ListNode res = ao.addOne(n799);
        //99
        ListNode n99 = ao.construct(99);
        res = ao.addOne(n99);
        //123
        ListNode n123 = ao.construct(123);
        res = ao.addOne(n123);
        //199299
        ListNode n19929 = ao.construct(19929);
        res = ao.addOne(n19929);
        //null
        //ListNode res = ao.addOne();
    }
}
