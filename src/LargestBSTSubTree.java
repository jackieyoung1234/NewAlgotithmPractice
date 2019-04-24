import Tree.TreeNode;

public class LargestBSTSubTree {
    int MAX = 0;
    int RES = 0;
    public int largestBSTSubtree(TreeNode root) {
        helper(root,new int[]{0,0,0});
        return RES;
    }
    public int largestBSTSubtree2(TreeNode root) {
          helper2(root);
          return MAX;
    }
    private boolean helper(TreeNode r,int[] minmaxsize){
        if(r==null){
            minmaxsize[2] =  0;
            return true;
        }
        int[] lminmaxsize = new int[]{0,0,0};
        int[] rminmaxsize = new int[]{0,0,0};
        boolean lb = helper(r.left,lminmaxsize);
        boolean rb = helper(r.right,rminmaxsize);
        if(lb&&rb){
            if(
                    (lminmaxsize[2]==0||r.val>lminmaxsize[1])
                            &&
                            (rminmaxsize[2]==0||r.val<rminmaxsize[0])
            ){
                minmaxsize[2] = 1+lminmaxsize[2]+rminmaxsize[2];
                minmaxsize[1] = rminmaxsize[2]==0?r.val:rminmaxsize[1];
                minmaxsize[0] = lminmaxsize[2]==0?r.val:lminmaxsize[0];
                RES = Math.max(RES,minmaxsize[2]);
                return true;
            }
        }
        return false;

    }
    //return int[2]
    //[0] 0- bst, 1-not bst
    //[1] size if [0]==0
    //[2] min value in this bst if [0]==0
    //[3] max value in this bst if [0]==0
    private int[] helper2(TreeNode n){
       if(n==null) return new int[]{0,0,0,0};
       int[] resl = helper2(n.left);
       int[] resr = helper2(n.right);
       int isbst = 1;
       int size = 0;
       int min = 0, max = 0;
       if(resl[0]==0&&resr[0]==0&&
          ((n.left==null||n.val>resl[3])&&(n.right==null||n.val<resr[2]))
         ){
         size = resl[1]+resr[1]+1;
         MAX  = Math.max(MAX,size);
         min = n.left==null?n.val:resl[2];
         max = n.right==null?n.val:resr[3];
         isbst = 0;
       }
       return new int[]{isbst,size,min,max};
    }
    public static void main(String[] args){
        LargestBSTSubTree t = new LargestBSTSubTree();
        //case 1
        TreeNode t1 = new TreeNode(1);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(8);
        TreeNode t10 = new TreeNode(10);
        TreeNode t15 = new TreeNode(15);
        t10.left = t6;
        t10.right = t15;
        t6.left = t1;
        t6.right = t8;
        t15.right = t7;
        int res = t.largestBSTSubtree(t10);
        //case 2
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t0 = new TreeNode(0);
        TreeNode t5 = new TreeNode(5);
        t2.left=t0;
        t2.right=t3;
        t0.right=t5;
        t3.right=t4;
        res = t.largestBSTSubtree(t2);
    }
}
