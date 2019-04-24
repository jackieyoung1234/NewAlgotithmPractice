import Tree.TreeNode;

public class PathSum1 {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root==null) return false;
        return helper(root,sum);
    }
    private boolean helper(TreeNode root, int sum){
        if(sum<0) return false;
        if(sum>0 && root==null) return false;
        if(sum==0 && root==null) return true;
        return helper(root.left,sum-root.val)||helper(root.right,sum-root.val);
    }
    public static void main(String[] args){
        TreeNode n5 = new TreeNode(5);
        TreeNode n4 = new TreeNode(4);
        TreeNode n8 = new TreeNode(8);
        TreeNode n11 = new TreeNode(11);
        TreeNode n13 = new TreeNode(13);
        TreeNode n4D = new TreeNode(4);
        TreeNode n7 = new TreeNode(7);
        TreeNode n2 = new TreeNode(2);
        TreeNode n1 = new TreeNode(1);
        n5.left = n4;
        n5.right = n8;
        n4.left = n11;
        n11.left = n7;
        n11.right = n2;
        n8.left = n13;
        n8.right = n4D;
        n13.right = n1;
        boolean res = new PathSum1().hasPathSum(n5,22);
    }
}
