import Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class treeTraverse {

    public List<Integer> postOrderIterative(TreeNode N){
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> sk = new Stack<>();
        TreeNode lastVisit = new TreeNode(0);
        while(N!=null || !sk.isEmpty()){
            if(N!=null){
                sk.push(N);
                N = N.left;
            }
            else{
               TreeNode peek = sk.peek();
               if(peek.right != null && peek.right != lastVisit) N = peek.right;
               else{
                   lastVisit = peek;
                   result.add(sk.pop().val);
               }
            }
        }
        return result;
    }
    public static void main(String[] args){
        List<TreeNode> ll = new ArrayList<>();
        ll.add(null);
        for(TreeNode l : ll){
            System.out.print("get 1");
        }
        boolean yy = (null == new TreeNode(0));
        yy = (null == null);
//        TreeNode n1 = new TreeNode(1);
//        TreeNode n2 = new TreeNode(2);
//        TreeNode n3 = new TreeNode(3);
//        TreeNode n4 = new TreeNode(4);
//        n2.left = n1;
//        n3.left = n2;
//        new treeTraverse().postOrderIterative(n3);
//        n1 = new TreeNode(1);
//        n2 = new TreeNode(2);
//        n3 = new TreeNode(3);
//        n4 = new TreeNode(4);
//        n3.left = n1;
//        n3.right = n2;
//        new treeTraverse().postOrderIterative(n3);
//        n1 = new TreeNode(1);
//        n2 = new TreeNode(2);
//        n3 = new TreeNode(3);
//        n4 = new TreeNode(4);
//        n3.left = n1;
//        n3.right = n2;
//        n4.left = n3;
//        new treeTraverse().postOrderIterative(n4);
//        n1 = new TreeNode(1);
//        n2 = new TreeNode(2);
//        n3 = new TreeNode(3);
//        n4 = new TreeNode(4);
//        n2.right = n1;
//        n3.left = n2;
//        new treeTraverse().postOrderIterative(n3);
//        n1 = new TreeNode(1);
//        n2 = new TreeNode(2);
//        n3 = new TreeNode(3);
//        n4 = new TreeNode(4);
//        n2.left = n1;
//        n3.right = n2;
//        new treeTraverse().postOrderIterative(n3);
//        n1 = new TreeNode(1);
//        n2 = new TreeNode(2);
//        n3 = new TreeNode(3);
//        n4 = new TreeNode(4);
//        n2.right = n1;
//        n3.right = n2;
//        new treeTraverse().postOrderIterative(n3);
    }
}
