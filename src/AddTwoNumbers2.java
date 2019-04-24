import java.util.Stack;

public class AddTwoNumbers2 {
 //imutable input lists
 public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
  //len1   len2
  int len1=1,len2=1;
  ListNode l1backup = l1, l2backup = l2;
  ListNode b9 = null, f9 = null, l9 = null,pre =null,cur=null;//
  while(l1backup.next!=null){
   len1++;
   l1backup = l1backup.next;
  }
  while(l2backup.next!=null){
   len2++;
   l2backup = l2backup.next;
  }
  if(len1==len2){
   int[] cc = new int[]{0};
   ListNode ll = addEqualen( l1, l2, cc);
    if(cc[0]==1){
     ListNode llf = new ListNode(1);
     llf.next = ll;
     return llf;
    }
    return ll;
   }
   //ex if len1<len2
   if(len1<len2){
    int t = len1;
    len1 = len2;
    len2 = t;
    ListNode tt = l1;
    l1 = l2;
    l2 = tt;
   }
   l1backup = l1;
   ListNode newf = new ListNode(l1.val);
   ListNode newfbackup = newf;
   int ttt = len1-len2;
   while(--ttt>0){
     l1backup = l1backup.next;
     newf.next = new ListNode(l1backup.val);
     newf = newf.next;
   }
   //l1...l2 l3....l4 list 1
   //        n3....n4
   //copy l1..l1backup
   // as  newfbackup....... newf
   //get b9,f9,l9
   pre = null;
   cur = newfbackup;
   while(pre!=newf){
     if(cur.val==9){
       b9 = pre;
       f9 = cur;
       l9 = cur;
       pre = cur;
       cur = cur.next;
       while(pre!=newf&&cur.val==9){
         l9 = cur;
         pre = cur;
         cur = cur.next;
       }
     }else{
       pre = cur;
       cur = cur.next;
     }
   }
   int[] carry = new int[]{0};
   ListNode n = addEqualen(l1backup.next,l2,carry);
   newf.next = n;
   if(carry[0]!=0){
    if (newf.val != 9) {
     newf.val++;
    } else {
     f9.val = 0;
     while (f9 != l9) {
      f9 = f9.next;
      f9.val = 0;
     }
     if (b9 != null) {
      b9.val++;
     } else {
      ListNode tem = new ListNode(1);
      tem.next = newfbackup;
      return tem;
     }
    }
   }
  return newfbackup;
 }
 private ListNode addEqualen(ListNode n1, ListNode n2, int[] c){
  if(n1==null){
   return null;
  }
  ListNode res = new ListNode(n1.val+n2.val);
  int[] cthislevel = new int[]{0};
  res.next = addEqualen(n1.next,n2.next,cthislevel);
  res.val+=cthislevel[0];
  if(res.val>=10){
   res.val-=10;
   c[0] =1;
  }
  return res;
 }
 public ListNode addTwoNumbers_2(ListNode l1, ListNode l2) {
  ListNode rl1 = reverse(l1);
  ListNode rl2 = reverse(l2);
  ListNode resbackup = new ListNode(0);
  ListNode res = resbackup;
  int carry = 0, sum = 0;
  while(rl1!=null||rl2!=null){
   sum = carry;
   if(rl1!=null){
    sum+=rl1.val;
    rl1 = rl1.next;
   }
   if(rl2!=null){
    sum+=rl2.val;
    rl2 = rl2.next;
   }
   if(sum>=10){
    sum-=10;
    carry = 1;
   }else{
    carry = 0;
   }
   res.next = new ListNode(sum);
   res = res.next;
  }
  if(carry==1){
   res.next = new ListNode(1);
  }
  res = resbackup.next;
  resbackup.next = null;
  return reverse(res);
 }
 private ListNode reverse(ListNode n){
  ListNode pre = n,cur = n.next;
  ListNode nex;
  pre.next = null;
  while(cur!=null){
   nex = cur.next;
   cur.next = pre;
   pre = cur;
   cur = nex;
  }
  return pre;
 }
 public static void main(String[] args){
        AddTwoNumbers2 atn2 = new AddTwoNumbers2();
        ListNode res = atn2.addTwoNumbers(AddOne.construct(98),AddOne.construct(2));
        res = atn2.addTwoNumbers(AddOne.construct(9),AddOne.construct(2));
        res = atn2.addTwoNumbers(AddOne.construct(909988),AddOne.construct(12));
        res = atn2.addTwoNumbers(AddOne.construct(899),AddOne.construct(2));
    }
}
