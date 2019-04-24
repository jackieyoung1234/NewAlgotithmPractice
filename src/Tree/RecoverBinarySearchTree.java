package Tree;

public class RecoverBinarySearchTree {
    TreeNode target1;
    TreeNode target2;
    TreeNode pre;
    public void recoverTree(TreeNode root) {
        if(root==null) return;
        f(root);
        TreeNode temp = target1;
        target1 = target2;
        target2 = temp;
    }
    private void f(TreeNode n){
        if(n==null)  return;
        f(n.left);
        //
        if(pre!=null&&target1==null&&pre.val>n.val) target1=pre;
        else if(pre!=null&&target1!=null&&pre.val>n.val) target2=n;
        pre=n;
        f(n.right);
    }
    public static void main(String[] args){
       TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        t1.left = t3;
        t3.right = t2;
        RecoverBinarySearchTree rb = new RecoverBinarySearchTree();
        rb.recoverTree(t1);
    }
}
