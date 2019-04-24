import Tree.TreeNode;

public class buildTreePreAndIn {
    int len=0;
    int[] preorder;
    int[] inorder;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        len = inorder.length;
        //corner case no node
        if(len==0) return null;
        preorder = preorder;
        inorder  = inorder;
        return helper(0,0,len-1);
    }
    private TreeNode helper(int curIndexInPreOrder, int stIn, int endIn){
        if(stIn>endIn|| curIndexInPreOrder >len-1) return null;
        int rootVal = preorder[curIndexInPreOrder];
        TreeNode root = new TreeNode(rootVal);
        int cnt = 0,i= stIn;
        while(inorder[i++]!=rootVal){
            cnt++;
        }
        root.left = helper(curIndexInPreOrder+1,stIn,i-2);
        root.right= helper(curIndexInPreOrder+cnt+1,i,endIn);
        return root;
    }
    public static void main(String[] args){
        TreeNode res = new buildTreePreAndIn().buildTree(
            new int[]{3,9,20,15,7},
            new int[]{9,3,15,20,7}
        );
    }
}
