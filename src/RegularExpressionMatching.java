import java.util.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegularExpressionMatching {
    private class Bag<Item> implements Iterable<Item>{
            private Node<Item> first;

            private class Node<Item> {
                private Item item;
                private Node<Item> next;
            }
            public Bag() {
                first = null;
            }

            public void add(Item item) {
                Node<Item> oldfirst = first;
                first = new Node<Item>();
                first.item = item;
                first.next = oldfirst;
            }


            public Iterator<Item> iterator()  {
                return new ListIterator<Item>(first);
            }

            private class ListIterator<Item> implements Iterator<Item> {
                private Node<Item> current;

                public ListIterator(Node<Item> first) {
                    current = first;
                }

                public boolean hasNext()  { return current != null;}

                public Item next() {
                    if (!hasNext()) throw new NoSuchElementException();
                    Item item = current.item;
                    current = current.next;
                    return item;
                }
            }
    }
    private class Digraph {
        private int V;
        private int E;
        private Bag<Integer>[] adj;

        public Digraph(int V){
            this.V = V;
            E = 0;
            adj = new Bag[V];
            for(int temp = 0; temp < V ; temp ++){
                adj[temp] = new Bag<Integer>();
            }
        }
        public void addEdge(int V, int W){
            adj[V].add(W);
            E++;
        }
        public int V(){
            return V;
        }
        public int E(){
            return E;
        }
        public Iterable<Integer> adj(int V){
           return adj[V];
        }
    }
    private class DirectedDFS {
        boolean[] marked;
        public DirectedDFS(Digraph G, int s){
            marked = new boolean[G.V()];
            dfs(G,s);
        }
       public DirectedDFS(Digraph G, Iterable<Integer> s){
            marked = new boolean[G.V()];
            for(int t : s)
                if(!marked[t]) dfs(G,t);
       }
       private void dfs(Digraph G,int s){
            marked[s] = true;
            for(int t : G.adj(s)){
                if(marked[t] == false)
                    dfs(G,t);
            }
       }
       public boolean marked(int V){
          return marked[V];
       }
    }
    private class RegexMatch{
        private char[] re;
        private Digraph G;
        public RegexMatch(String regex){
            G = new Digraph(regex.length()+1);
            //stack for parenthesis
            re = regex.toCharArray();
            Stack<Integer> s1 = new Stack<>();
            //stack for or
            Stack<Integer> s2 = new Stack<>();
            for(int i = 0; i < regex.length(); i++){
                char temp = regex.charAt(i);
                if (
                        temp != '|'
                            &&
                        !Character.isLetter(temp)
                            &&
                        temp != '.'
                ) {
                    if (i <= regex.length())
                        G.addEdge(i, i + 1);
                }
                if(temp == '*') {
                    char tempBefore = regex.charAt(i-1);
                    if (Character.isLetter(tempBefore)||tempBefore == '.') {
                        G.addEdge(i, i - 1);
                        G.addEdge(i - 1, i);
                    }
                }
                if(temp == '(')
                    s1.push(i);
                if(temp == ')') {
                    if(i < regex.length() - 1){
                        if (regex.charAt(i + 1) == '*') {
                            G.addEdge(i + 1, s1.peek());
                            G.addEdge(s1.peek(), i + 1);
                        }
                    }
                    while(!s2.isEmpty() && s2.peek()>s1.peek())
                        G.addEdge(s2.pop(),i);
                    s1.pop();
                }
                if(temp == '|'){
                    s2.add(i);
                    G.addEdge(s1.peek(),i+1);
                }
                if(
                        !Character.isLetter(temp)
                        &&
                        temp != '|'
                        &&
                        temp != '*'
                        &&
                        temp != '('
                        &&
                        temp != ')'
                        &&
                        temp != '.'
                )
                    throw new UnsupportedOperationException(String.format("OP is not supported, index %d",i));
            }
            if(!s1.isEmpty() || !s2.isEmpty())
                throw new IllegalArgumentException("parenthesis or | not match ");
        }
    }
    public boolean isMatch(String s, String p) {
        RegexMatch newR = new RegexMatch(p);
        DirectedDFS DiGDFS = new DirectedDFS(newR.G,0);
        Bag<Integer> stBag = new Bag<>();
        Bag<Integer> matchBag = new Bag<>();
        for(int t = 0; t < newR.G.V(); t ++){
            if(DiGDFS.marked(t))
                 stBag.add(t);
        }
        for (int i = 0; i < s.length(); i ++) {
            matchBag = new Bag<>();
            for (int t : stBag) {
                if(t < newR.re.length ) {
                    if (newR.re[t] == s.charAt(i) || newR.re[t] == '.')
                        matchBag.add(t + 1);
                }
            }
            DiGDFS = new DirectedDFS(newR.G, matchBag);
            stBag = new Bag<>();
            for (int t = 0; t < newR.G.V(); t++) {
                if (DiGDFS.marked(t))
                    stBag.add(t);
            }
        }
        for (int i : stBag)
            if(i == newR.re.length)return true;
        return false;
    }
    public static void main(String[] args){
       RegularExpressionMatching r = new RegularExpressionMatching();
       assertEquals(r.isMatch("cd","((ac|b*c)d)"),true);
       assertEquals(r.isMatch("bbbcd","((ac|b*c)d)"), true);
       assertEquals(r.isMatch("babcd","((ac|b*c)d)"),false);
       assertEquals(r.isMatch("acd","((ac|b*c)d)")  ,true);
       assertEquals(r.isMatch("acbd","((ac|b*c)d)") ,false);
       assertEquals(r.isMatch("accd","((ac|b*c)d)") ,false);
       assertEquals(r.isMatch("bbccd","((ac|b*c)d)"),false);
       assertEquals(r.isMatch("bbd","((ac|b*c)d)")  ,false);
       assertEquals(r.isMatch("bbcd","((ac|b*c)d)") ,true);
       assertEquals(r.isMatch("cd","((ac|b*c*)d)"),true);
       assertEquals(r.isMatch("bbbcd","((ac|b*c*)d)"), true);
       assertEquals(r.isMatch("abcd","((ac|b*c*)d)"),false);
       assertEquals(r.isMatch("cd","((ac|b*c*)d)")  ,true);
       assertEquals(r.isMatch("cbd","((ac|b*c*)d)") ,false);
       assertEquals(r.isMatch("ccd","((ac|b*c*)d)") ,true);
       assertEquals(r.isMatch("bccd","((ac|b*c*)d)"),true);
       assertEquals(r.isMatch("bd","((ac|b*c*)d)")  ,true);
       assertEquals(r.isMatch("bbcccccd","((ac|b*c*)d)") ,true);
       //org.junit.Assert.assertEquals(r.isMatch("((ac|b*c*)d)","ccccd") ,true);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|ac|(ab)*d*)d)","d"),true);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|ac|(ab)*d*)d)","dd"),true);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|ac|(ab)*d*)d)","dad"),false);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|ac|(ab)*d*)d)","dabd"),false);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|ac|(ab)*d*)d)","dabd"),false);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|ac|(ab)*d*)d)","ababd"), true);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|ac|(ab)*d*)d)","abdddd"), true);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|ac|(ab)*d*)d)","ababdddd"), true);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|ac|(ab)*d*)d)","acd"),true);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|ac|(ab)*d*)d)","cd")  ,true);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|ac|(ab)*d*)d)","ccccd")  ,false);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|ac|(ab)*d*)d)","bbbbcd")  ,true);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|ac|(ab)*d*)d)","bcd")  ,true);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|ac|(ab)*d*)d)","acbd") ,false);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|ac|(ab)*d*)d)","abd") ,true);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|ac|(ab)*d*)d)","bbccd"),false);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|ac|(ab)*d*)d)","bbd")  ,false);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|ac|(ab)*d*)d)","bbbcd") ,true);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|ac|(ab)*d*)d)","cccccd") ,false);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|a.c|(a.b)*.d*)d)","ababdddd"),false);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|a.c|(a.b)*.d*)d)","aacd"),true);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|a.c|(a.b)*.d*)d)","d"),false);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|a.c|(a.b)*.d*)d)","dd"),true);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|a.c|(a.b)*.d*)d)","dad"),false);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|a.c|(a.b)*.d*)d)","add"),true);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|a.c|(a.b)*.d*)d)","aadd"),false);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|a.c|(a.b)*.d*)d)","addd"),true);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|a.c|(a.b)*.d*)d)","zddd"),true);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|a.c|(a.b)*.d*)d)","dabd"),false);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|a.c|(a.b)*.d*)d)","azbd"),false);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|a.c|(a.b)*.d*)d)","add"),true);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|a.c|(a.b)*.d*)d)","azbzd"),true);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|a.c|(a.b)*.d*)d)","acbd"),false);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|a.c|(a.b)*.d*)d)","acbddd"),true);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|a.c|(a.b)*.d*)d)","acbdd"),true);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|a.c|(a.b)*.d*)d)","accbd"),false);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|a.c|(a.b)*.d*)d)","abcd"),true);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|a.c|(a.b)*.d*)d)","adbabbacdd"),false);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|a.c|(a.b)*.d*)d)","adbabbacbdd"),true);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|a.c|(a.b)*.d*)d)","adbabbacbadbdd"),true);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|a.c|(a.b)*.d*)d)","adbabbacbadbd"),false);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|a.c|(a.b)*.d*)d)","abdddd"), false);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|a.c|(a.b)*.d*)d)","abbdddd"), true);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|a.c|(a.b)*.d*)d)","acd"),false);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|a.c|(a.b)*.d*)d)","cd")  ,true);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|a.c|(a.b)*.d*)d)","ccccd")  ,false);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|a.c|(a.b)*.d*)d)","ababd"),false);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|a.c|(a.b)*.d*)d)","bbbbcd")  ,true);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|a.c|(a.b)*.d*)d)","bcd")  ,true);
       //org.junit.Assert.assertEquals(r.isMatch("((ab|b*c|a.c|(a.b)*.d*)d)","acbd") ,false);
       assertEquals(r.isMatch("abd","((ab|b*c|a.c|(a.b)*.d*)d)") ,true);
       assertEquals(r.isMatch("bbccd","((ab|b*c|a.c|(a.b)*.d*)d)"),false);
       assertEquals(r.isMatch("bbd","((ab|b*c|a.c|(a.b)*.d*)d)")  ,false);
       assertEquals(r.isMatch("bbbcd","((ab|b*c|a.c|(a.b)*.d*)d)") ,true);
       assertEquals(r.isMatch("cccccd","((ab|b*c|a.c|(a.b)*.d*)d)") ,false);
    }
}

