import java.util.Arrays;
import java.util.List;

public class WordBreak {
    public class Node{
        boolean isEnd;
        Node[] ch;
        public Node(){
            isEnd = false;
            ch = new Node[128];
        }
    }
    public class Trie{
        Node root;
        public Trie(){
            root = new Node();
        }
        public void add(String s){
            root = add(root,s,0);
        }
        private Node add(Node root, String s, int i){
            if(root==null){
                root = new Node();
            }
            if(i==s.length()){
                root.isEnd = true;
                return root;
            }
            int index = s.charAt(i);
            root.ch[index]= add(root.ch[index],s,i+1);
            return root;
        }
        public boolean search(String s){
            return search(root,s,0);
        }
        private boolean search(Node n, String s, int i){
            if(i==s.length()){
                if(n==null||!n.isEnd) return false;
                else return true;
            }
            if(n == null) return false;
            if(n.isEnd && search(root,s,i)) return true;
            return search(n.ch[s.charAt(i)],s,i+1);
        }
    }
    public boolean wordBreak(String s, List<String> wordDict) {
        Trie t = new Trie();
        for(String w : wordDict){
            t.add(w);
        }
        return t.search(s);
    }
    public static void main(String[] args){
        WordBreak t = new WordBreak();
        boolean res = t.wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab"
                , Arrays.asList("a","aa"));
        System.out.println(res);
    }

}
