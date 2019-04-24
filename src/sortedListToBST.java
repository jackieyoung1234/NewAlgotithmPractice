import Tree.TreeNode;

public class sortedListToBST {
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;
        TreeNode root = new TreeNode(head.val);
        TreeNode rightMost = root.right;
        boolean odd = true;
        head = head.next;
        while(head!=null){
            if(odd){
                rightMost.val = head.val;
            }else{//even
                rightMost.right = new TreeNode(head.val);
                rightMost = rightMost.right;
                TreeNode temp = root.right;
                root.right = null;
                temp.left = root;
                root = temp;
            }
            odd = !odd;
            head = head.next;
        }
        return root;
    }
    public static void main(String[] args){
       ListNode n1 = new ListNode(1);
       ListNode n2 = new ListNode(2);
       ListNode n3 = new ListNode(3);
       ListNode n4 = new ListNode(4);
       ListNode n5 = new ListNode(5);
       n1.next = n2;
       n2.next = n3;
       n3.next = n4;
       n4.next = n5;
       TreeNode res =  new sortedListToBST().sortedListToBST(n1);
    }
}
