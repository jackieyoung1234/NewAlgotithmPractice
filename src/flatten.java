import Tree.TreeNode;

import java.util.Stack;

public class flatten {
   public void flatten(TreeNode root) {
      Stack<TreeNode> sk = new Stack<>();
      TreeNode prev = null;
      while(!sk.isEmpty()){
         TreeNode n = sk.pop();
         if(prev!=null){
            prev.left = null;
            prev.right = n;
         }
         if(n.right!=null) sk.push(n.right);
         if(n.left!=null) sk.push(n.left);
         prev = n;
      }
   }
    public static void main(String[] args){
       TreeNode n1 = new TreeNode(1);
       TreeNode n2 = new TreeNode(2);
       TreeNode n3 = new TreeNode(3);
       TreeNode n4 = new TreeNode(4);
       TreeNode n5 = new TreeNode(5);
       TreeNode n6 = new TreeNode(6);
       n1.left = n2;
       n2.left = n3;
       n2.right = n4;
       n1.right = n5;
       n5.right = n6;
       new flatten().flatten(n1);
       Stack<TreeNode> sk = new Stack<>();
       sk.push(n1.left);
       n1.left = null;
       //boolean yy = "" =="ab".substring(0,0);
       String yyy = "ab".substring(0,0);
       String yyyy = yyy + 'a';
       boolean yy = yyy.equals("");
    }
}
