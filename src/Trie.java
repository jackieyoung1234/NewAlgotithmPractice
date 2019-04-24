import java.util.ArrayList;

public class Trie<VALUE> {
    private static final int R = 26;//character set
    private Node root;
    private static class Node{
        private Node[] next = new Node[R];
        private Object val;
    }
    public void insert(String key, VALUE v){
        root = insert(root,key,v,0);
    }
    private Node insert(Node n , String k, VALUE v, int level){
        if(n==null) n = new Node();
        if(level == k.length()){
            n.val = v;
            return n;
        }
        int index = k.charAt(level)-'a';
        n.next[index] = insert(n.next[index],k,v,level+1);
        return n;
    }
    // if no such key or key is not associated with a value,return null
    public VALUE search(String key){
        if(key==null) return null;
        return search(root,key,0);
    }
    public VALUE search(Node n, String k, int level){
        if(n==null) return null;
        if(level==k.length()) return (VALUE)n.val;
        int index = k.charAt(level)-'a';
        return search(n.next[index],k,level+1);
    }
    public static void main(String[] args){
           Trie<Integer> test = new Trie<>();
            test.insert("aa",1);
            test.insert("abc",1);
            Integer res = test.search("ab");

    }
}
