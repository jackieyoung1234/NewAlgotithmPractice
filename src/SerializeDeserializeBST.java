import Tree.TreeNode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class SerializeDeserializeBST {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return helper(root).toString();
    }
    //preorder morris
    //no delimiter, i.e., UTF-8 4 byte fixed length per value en/decode
    private StringBuilder helper(TreeNode root){
        StringBuilder res = new StringBuilder();
        TreeNode tt = null;
        int nodeNumCnt = 0;
        while(root!=null){
            if(root.left==null){
                res.append(encode(root));
                nodeNumCnt++;
                root = root.right;
            }else{
                tt = root.left;
                while(tt.right!=null&&tt.right!=root){
                    tt = tt.right;
                }
                if(tt.right==null){
                    res.append(encode(root));
                    nodeNumCnt++;
                    tt.right = root;
                    root = root.left;
                }else{
                    tt.right = null;
                    root = root.right;
                }
            }
        }
        return res;
    }
    private String encode(TreeNode r){
        return new String(encodeHelper(r.val));
        //try{
        //    return new String(encodeHelper(r.val),"UTF-8");
        //}catch(Exception e){
        //    return null;
        //}
    }
    private char[] encodeHelper(int val){
        return new char[]{
                (char)(val>>16),
                (char)(val),
        };
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.length()==0) return null;
        //todo: int[] data = getData(data.getBytes("UTF-8"));
        return deSHelper(getData(data),new int[1],Integer.MIN_VALUE,Integer.MAX_VALUE);
        //try{
        //    return deSHelper(getData(data.getBytes("UTF-8")),new int[1],Integer.MIN_VALUE,Integer.MAX_VALUE);
        //}catch(Exception e){
        //    return null;
        //}
    }
    private TreeNode deSHelper(int[] data,int[] ptr,int min, int max){
        if(ptr[0]>=data.length) return null;
        int value = data[ptr[0]];
        if(value<min||value>max) return null;
        TreeNode cur = new TreeNode(value);
        ptr[0]++;
        cur.left = deSHelper(data,ptr,min,value);
        cur.right = deSHelper(data,ptr,value,max);
        return cur;
    }

    private int[] getData(String bd){
        int[] res = new int[bd.length()>>1];
        for(int i=0;i<bd.length();){//i+=4){
            //res[i>>1] = (int)(bd[i++]<<24)+(int)(bd[i++]<<16)+ (int)(bd[i++]<<8) + (int)(bd[i++]);
            res[i>>1] = (int)(bd.charAt(i++)<<16)+(int)(bd.charAt(i++));
        }
        return res;
    }
    //ptr ini to 1
    private static TreeNode helper(Scanner s,int[] cnt){
        TreeNode root = process(s);
        if(root==null) return root;
        Queue<TreeNode> sk = new LinkedList<>();
        sk.offer(root);
        cnt[0]=0;
        TreeNode cur = null;
        while(!sk.isEmpty()){
           cur = sk.poll();
           cnt[0]++;
           cur.left = process(s);
           cur.right = process(s);
            if(cur.left!=null) {
                sk.offer(cur.left);
            };
            if(cur.right!=null) {
                sk.offer(cur.right);
            }
        }
        return root;
    }
    private static TreeNode process(Scanner s){
        if(!s.hasNext()) return null;
        String ss = s.next();
        if(ss.equals("null")){
           return null;
        }
        int res = 0,ptr =0;
        while(ptr<ss.length()){
            res*=10;
            res+=(ss.charAt(ptr++)-'0');
        }
        return new TreeNode(res);
    }
    private static void writeNode(TreeNode res) throws Exception{
        BufferedWriter wr = new BufferedWriter(new FileWriter(new File("output/SerializeDesirializeBST.txt"),false));
        wr.write("[");
        if(res==null) {
            wr.write("null");
        }else {
            TreeNode tt;
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(res);
            wr.write(""+res.val);
            while(!q.isEmpty()){
               tt = q.poll();
               if(tt.left==null){
                  wr.write(",null");
               }else{
                   wr.write(","+tt.left.val);
                   q.offer(tt.left);
               }
                if(tt.right==null){
                    wr.write(",null");
                }else{
                    wr.write(","+tt.right.val);
                    q.offer(tt.right);
                }
            }
        }
        wr.write("]");
        wr.flush();
        wr.close();
    }
    public static void main(String[] args) throws Exception{
       SerializeDeserializeBST sd = new SerializeDeserializeBST();
       //TreeNode t10 = new TreeNode(10);
       // TreeNode t5 = new TreeNode(5);
       // TreeNode t3 = new TreeNode(3);
       // TreeNode t7 = new TreeNode(7);
       // TreeNode t15 = new TreeNode(15);
       // t10.left = t5;
       // t5.left = t3;
       // t5.right = t7;
       // t10.right = t15;
       // String s = sd.serialize(t10);
       // TreeNode t10d = sd.deserialize(s);
        Scanner failedCase = new Scanner(new File("input/SerializeDesirializeBST.txt"));
        failedCase.useDelimiter("\\s|,|\\[|]");
        int[] nodenumcnt = new int[1];
        TreeNode failedNode = helper(failedCase,nodenumcnt);
        TreeNode myDeNode = sd.deserialize(sd.serialize(failedNode));
        writeNode(myDeNode);
    }
}
