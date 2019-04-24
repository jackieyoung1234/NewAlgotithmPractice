import java.lang.reflect.Array;
import java.util.Queue;

public class TrieST<Value> {
    private static int R = 256;//extended ascii
    private int n;//size
    private Node root;
    private class Node{
        private Object Value;
        private Node[] next = (Node[])new TrieST<?>.Node[R];
    }
    public int size(){
        return n;
    }
    public TrieST(){
       n = 0;
       root = new Node();
    }
    public Value get(String key){
        if(key == null) throw new IllegalArgumentException("argument to get() is null");
        Node resultNode = get(key,root,0);
        return (resultNode == null)? null:(Value) resultNode.Value;
    }
    private Node get(String key, Node x, int d){
        if(x == null) return null;
        if(d == key.length()) return x;
        return get(key,x.next[key.charAt(d)],++d);
    }
    public void put(String key, Value val){
        if(key == null) throw new IllegalArgumentException("argument to put() is null");
        if(val == null) {
            delete(key);
            return;
        }
        root = put(key,root,val,0);
    }
    private Node put(String key, Node x, Value val, int d){
        if(x == null){
            x = new  TrieST.Node();
            n++;
        }
        if(d == key.length()){
            x.Value = val;
            return x;
        }
        x.next[key.charAt(d)] = put(key,x.next[key.charAt(d)],val,++d);
        return x;
    }
    public void delete(String key){
        //need to recursively delete and modify root node
    }
    public Iterable<String> keys(){
       return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String prefix){
        return null;
    }
    private void collect(Node x, String pre, Queue<String> q){

    }
    public static void main(String [] args){
        TrieST<Integer> my_TST = new TrieST<>();
        System.out.println("size is "+my_TST.size());
        System.out.println("put by");
        my_TST.put("by",4);
        System.out.println("size is "+my_TST.size());
        System.out.println("value of sea is "+my_TST.get("sea"));
        System.out.println("put sea");
        my_TST.put("sea",6);
        System.out.println("size is "+my_TST.size());
        System.out.println("value of by is "+my_TST.get("by"));
        System.out.println("put sells");
        my_TST.put("sells",1);
        System.out.println("size is "+my_TST.size());
        System.out.println("value of sells is "+my_TST.get("sells"));
        System.out.println("put the");
        my_TST.put("the",5);
        System.out.println("size is "+my_TST.size());
        System.out.println("value of sea is "+my_TST.get("sea"));
        System.out.println("put shells");
        my_TST.put("shells",3);
        System.out.println("size is "+my_TST.size());
        System.out.println("put shore");
        my_TST.put("shore",7);
        System.out.println("size is "+my_TST.size());
        System.out.println("value of shores is "+my_TST.get("shores"));
    }
}
