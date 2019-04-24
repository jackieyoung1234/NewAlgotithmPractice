import Tree.NaryTreeNode;
import Tree.TreeNode;

public class DiameterOfTree {
    public int diameter(NaryTreeNode n) {
        return helper(n)[1];
    }

    public int diameter(TreeNode n) {
        return helper(n)[1];
    }

    //[0] height, [1] dia
    public int[] helper(NaryTreeNode n) {
        //[0] 1st height of ch, [1] 2nd height
        int[] onestand2ndhei = new int[]{0, 0};
        if (n == null) return onestand2ndhei;
        if(n.ch!=null) {
            for (NaryTreeNode ch : n.ch) {
                int[] cinfo = helper(ch);
                if (cinfo[0] > onestand2ndhei[0]) {
                    onestand2ndhei[0] = cinfo[0];
                } else if (cinfo[0] > onestand2ndhei[1]) {
                    onestand2ndhei[1] = cinfo[0];
                }
            }
        }
        int newhei = onestand2ndhei[0] + 1;
        return new int[]{newhei, newhei + onestand2ndhei[1]};
    }

    //[0]height, [1]dia
    public int[] helper(TreeNode n) {
        if (n == null) return new int[]{0, 0};
        int[] leftinfo = helper(n.left);
        int[] rightinfo = helper(n.right);
        int thisdiameter = leftinfo[0] + rightinfo[0] + 1;
        int thisheight = Math.max(leftinfo[0], rightinfo[0]) + 1;
        int nextdiameter = Math.max(Math.max(leftinfo[1], rightinfo[1]), thisdiameter);

        return new int[]{thisheight, nextdiameter};
    }

    public static void main(String[] args) {
        DiameterOfTree d = new DiameterOfTree();
        TreeNode t0 = new TreeNode(0);
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(8);
        TreeNode t9 = new TreeNode(9);
        t0.left = t1;
        t0.right = t2;
        t1.left = t3;
        t1.right = t4;
        t4.left = t6;
        t4.right = t7;
        t2.right = t5;
        t5.left = t8;
        t5.right = t9;
        int res = d.diameter(t0);
        t0 = new TreeNode(0);
        t1 = new TreeNode(1);
        t2 = new TreeNode(2);
        t3 = new TreeNode(3);
        t4 = new TreeNode(4);
        t5 = new TreeNode(5);
        t6 = new TreeNode(6);
        t7 = new TreeNode(7);
        t8 = new TreeNode(8);
        t9 = new TreeNode(9);
        TreeNode t10 = new TreeNode(10);
        t0.left = t1;
        t0.right = t2;
        t1.left = t3;
        t1.right = t4;
        t3.right = t5;
        t5.left = t7;
        t7.left = t9;
        t4.right = t6;
        t6.left = t8;
        t8.left = t10;
        res = d.diameter(t0);


        // test for NaryTreeNode
        NaryTreeNode i0 = new NaryTreeNode(0);
        NaryTreeNode i1 = new NaryTreeNode(1);
        NaryTreeNode i2 = new NaryTreeNode(2);
        NaryTreeNode i3 = new NaryTreeNode(3);
        NaryTreeNode i4 = new NaryTreeNode(4);
        NaryTreeNode i5 = new NaryTreeNode(5);
        NaryTreeNode i6 = new NaryTreeNode(6);
        NaryTreeNode i7 = new NaryTreeNode(7);
        NaryTreeNode i8 = new NaryTreeNode(8);
        NaryTreeNode i9 = new NaryTreeNode(9);
        NaryTreeNode i10 = new NaryTreeNode(10);
        NaryTreeNode i11 = new NaryTreeNode(11);
        NaryTreeNode i12 = new NaryTreeNode(12);
        i0.ch.add(i1);
        i0.ch.add(i2);
        i0.ch.add(i3);
        i1.ch.add(i4);
        i1.ch.add(i5);
        i1.ch.add(i6);
        i3.ch.add(i7);
        i3.ch.add(i8);
        i6.ch.add(i9);
        i6.ch.add(i10);
        i9.ch.add(i12);
        i7.ch.add(i11);
        int dd = d.diameter(i0);
    }
}