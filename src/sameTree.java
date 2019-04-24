import Tree.TreeNode;

import java.util.Stack;

public class sameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        //??pre order corner case first node is null
        if(p==null || q==null) return p==q;
        Stack<TreeNode> sK = new Stack<>();
        Stack<TreeNode> ssK = new Stack<>();
        sK.push(p);
        ssK.push(q);
        while(!sK.empty()&&!ssK.empty()){
            TreeNode n = sK.pop();
            TreeNode n2 = ssK.pop();
            if(n.val != n2.val) return false;
            if(n.right!=null&&n2.right!=null){
                sK.push(n.right);
                ssK.push(n2.right);
            }else if(n.right != n2.right){
                return false;
            }
            if(n.left!=null&&n2.left!=null){
                sK.push(n.left);
                ssK.push(n2.left);
            }else if(n.left != n2.left){
                return false;
            }
        }
        return true;
    }
}
