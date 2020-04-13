import Tree.TreeNode;
import org.junit.Assert;

import java.util.LinkedList;
import java.util.Queue;

public class GetAvgFromReverselyKthLevel {
    private static int get(TreeNode root, int k){
        Queue<TreeNode> q = new LinkedList<>();
        int[] avg = new int[k];
        int ptr = 0,kcopy = k;
        if(root==null) return -1;
        q.offer(root);
        int size,copysize,sum,i;
        TreeNode cur;
        while(kcopy>0&&(size = q.size())>0){
            copysize = size;
            sum = 0;
            while(size-->0){
                sum+=(cur = q.poll()).val;
                if(cur.left!=null) q.offer(cur.left);
                if(cur.right!=null) q.offer(cur.right);
            }
            avg[ptr++] = sum/copysize;
            ptr = (ptr%k);
            kcopy--;
        }
        if(kcopy>0) return -1;
        while((size=q.size())>0){
            copysize = size;
            sum = 0;
            while(size-->0){
                sum+=(cur = q.poll()).val;
                if(cur.left!=null) q.offer(cur.left);
                if(cur.right!=null) q.offer(cur.right);
            }
            avg[ptr++] = sum/copysize;
            ptr = (ptr%k);
        }
        return avg[ptr];
    }
    public static void main(String[] args){
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(8);
        TreeNode t9 = new TreeNode(9);
        TreeNode t10 = new TreeNode(10);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t3.right = t5;
        t4.right = t6;
        t5.left = t7;
        t5.right = t8;
        t6.left = t9;
        t7.right = t10;
        Assert.assertEquals(-1,get(t1,6));
        Assert.assertEquals(1,get(t1,5));
        Assert.assertEquals(2,get(t1,4));
        Assert.assertEquals(4,get(t1,3));
        Assert.assertEquals(7,get(t1,2));
        Assert.assertEquals(9,get(t1,1));
    }
}
