import Tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class preorderTraversal {
    //morris
    public List<Integer> preorderTraversal(TreeNode root) {
        //??corner case root = null
        TreeNode bk = root;
        List<Integer> result = new LinkedList<>();
        while(bk!=null)
            if(bk.left==null){
                //OP
                result.add(bk.val);
                bk = bk.right;
            }else{//bk.left !=null
                TreeNode c = bk.left;
                while(c.right!=null && c.right!=bk){
                    c = c.right;
                }
                if(c.right==null){
                    //op
                    result.add(bk.val);
                    c.right = bk;
                    bk =  bk.left;
                }
                else{
                    c.right = null;
                    bk = bk.right;
                }
            }
        return result;
    }
    public static void main(String[] args){
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        t1.right = t2;
        t2.left = t3;
        List<Integer> r = new preorderTraversal().preorderTraversal(t1);
    }
}
