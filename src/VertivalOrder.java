import Tree.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class VertivalOrder {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if(root==null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        TraverseNode roo = new TraverseNode();
        Queue<TraverseNode> q2 = new LinkedList<>();
        q2.offer(roo);
        while(!q.isEmpty()){
            TreeNode tt = q.poll();
            TraverseNode rr = q2.poll();
            //op here
            rr.l.add(tt.val);
            if(tt.left!=null) {
                rr.createPre();
                q.offer(tt.left);
                q2.offer(rr.pre);
            }
            if(tt.right!=null){
                rr.createNext();
                q.offer(tt.right);
                q2.offer(rr.next);
            }
        }
        while(roo.pre!=null) roo = roo.pre;
        while(roo!=null){
            res.add(roo.l);
            roo=roo.next;
        }
        return res;
    }
    private class TraverseNode{
        LinkedList l;
        TraverseNode pre;
        TraverseNode next; 
        public TraverseNode(){
            this.l = new LinkedList<>();
            this.pre = null;
            this.next = null;
        }
        public void createPre(){
           if(this.pre!=null) return;
           this.pre = new TraverseNode();
           this.pre.next = this;
        }
        public void createNext(){
            if(this.next!=null) return;
            this.next = new TraverseNode();
            this.next.pre = this;
        }
    }
    private void test(TreeNode t){
        if(t==null) t = new TreeNode(2);
    }
    public static void main(String[] args){
        System.out.println(new StringBuilder().toString());

        TreeNode t3 = new TreeNode(3);
        TreeNode t9 = new TreeNode(9);
        TreeNode t20 = new TreeNode(20);
        TreeNode t15 = new TreeNode(15);
        TreeNode t7 = new TreeNode(7);
        t3.left = t9;
        t3.right = t20;
        t20.left = t15;
        t20.right = t7;
        VertivalOrder v = new VertivalOrder();
        List<List<Integer>> res = v.verticalOrder(t3);

        //
        TreeNode a = new TreeNode(1);
        a.left = new TreeNode(3);
        v.test(a.left);
    }
}
