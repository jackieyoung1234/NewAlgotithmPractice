import graph.Digraph;
import graph.DirectedDFS;

import java.util.Stack;
import edu.princeton.cs.algs4.Bag;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegexMatch {
    private char[] re;
    private Digraph G;
    //private final int m;
    //public RegexMatch(String regexp) {
    //    re = regexp.toCharArray();
    //    m = regexp.length();
    //    Stack<Integer> ops = new Stack<Integer>();
    //    G = new Digraph(m+1);
    //    for (int i = 0; i < m; i++) {
    //        int lp = i;
    //        if (regexp.charAt(i) == '(' || regexp.charAt(i) == '|')
    //            ops.push(i);
    //        else if (regexp.charAt(i) == ')') {
    //            int or = ops.pop();

    //            // 2-way or operator
    //            if (regexp.charAt(or) == '|') {
    //                lp = ops.pop();
    //                G.addEdge(lp, or+1);
    //                G.addEdge(or, i);
    //            }
    //            else if (regexp.charAt(or) == '(')
    //                lp = or;
    //            else assert false;
    //        }

    //        // closure operator (uses 1-character lookahead)
    //        if (i < m-1 && regexp.charAt(i+1) == '*') {
    //            G.addEdge(lp, i+1);
    //            G.addEdge(i+1, lp);
    //        }
    //        if (regexp.charAt(i) == '(' || regexp.charAt(i) == '*' || regexp.charAt(i) == ')')
    //            G.addEdge(i, i+1);
    //    }
    //    if (ops.size() != 0)
    //        throw new IllegalArgumentException("Invalid regular expression");
    //}
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
    public boolean match(String text){
        DirectedDFS DiGDFS = new DirectedDFS(G,0);
        Bag<Integer> stBag = new Bag<>();
        Bag<Integer> matchBag = new Bag<>();
        for(int t = 0; t < G.V(); t ++){
            if(DiGDFS.marked(t))
                 stBag.add(t);
        }
        for (int i = 0; i < text.length(); i ++) {
            matchBag = new Bag<>();
            for (int t : stBag) {
                if(t < re.length ) {
                    if (re[t] == text.charAt(i) || re[t] == '.')
                        matchBag.add(t + 1);
                }
            }
            DiGDFS = new DirectedDFS(G, matchBag);
            stBag = new Bag<>();
            for (int t = 0; t < G.V(); t++) {
                if (DiGDFS.marked(t))
                    stBag.add(t);
            }
        }
        for (int i : stBag)
            if(i == re.length)return true;
        return false;
    }
    public static void main(String [] args){
        RegexMatch r1 = new RegexMatch("((ac|b*c)d)");
        assertEquals(r1.match("cd"),true);
        assertEquals(r1.match("bbbcd"), true);
        assertEquals(r1.match("babcd"),false);
        assertEquals(r1.match("acd")  ,true);
        assertEquals(r1.match("acbd") ,false);
        assertEquals(r1.match("accd") ,false);
        assertEquals(r1.match("bbccd"),false);
        assertEquals(r1.match("bbd")  ,false);
        assertEquals(r1.match("bbcd") ,true);
        RegexMatch r2= new RegexMatch("((ac|b*c*)d)");
        assertEquals(r2.match("cd"),true);
        assertEquals(r2.match("bbbcd"), true);
        assertEquals(r2.match("babcd"),false);
        assertEquals(r2.match("acd")  ,true);
        assertEquals(r2.match("acbd") ,false);
        assertEquals(r2.match("accd") ,false);
        assertEquals(r2.match("bbccd"),true);
        assertEquals(r2.match("bbd")  ,true);
        assertEquals(r2.match("bbbcccccd") ,true);
        assertEquals(r2.match("cccccd") ,true);
        RegexMatch r3= new RegexMatch("((ab|b*c|ac|(ab)*d*)d)");
        assertEquals(r3.match("d"),true);
        assertEquals(r3.match("dd"),true);
        assertEquals(r3.match("dad"),false);
        assertEquals(r3.match("dabd"),false);
        assertEquals(r3.match("dabd"),false);
        assertEquals(r3.match("ababd"), true);
        assertEquals(r3.match("abdddd"), true);
        assertEquals(r3.match("ababdddd"), true);
        assertEquals(r3.match("acd"),true);
        assertEquals(r3.match("cd")  ,true);
        assertEquals(r3.match("ccccd")  ,false);
        assertEquals(r3.match("bbbbcd")  ,true);
        assertEquals(r3.match("bcd")  ,true);
        assertEquals(r3.match("acbd") ,false);
        assertEquals(r3.match("abd") ,true);
        assertEquals(r3.match("bbccd"),false);
        assertEquals(r3.match("bbd")  ,false);
        assertEquals(r3.match("bbbcd") ,true);
        assertEquals(r3.match("cccccd") ,false);
        RegexMatch r4= new RegexMatch("((ab|b*c|a.c|(a.b)*.d*)d)");
        assertEquals(r4.match("ababdddd"),false);
        assertEquals(r4.match("aacd"),true);
        assertEquals(r4.match("d"),false);
        assertEquals(r4.match("dd"),true);
        assertEquals(r4.match("dad"),false);
        assertEquals(r4.match("add"),true);
        assertEquals(r4.match("aadd"),false);
        assertEquals(r4.match("addd"),true);
        assertEquals(r4.match("zddd"),true);
        assertEquals(r4.match("dabd"),false);
        assertEquals(r4.match("azbd"),false);
        assertEquals(r4.match("add"),true);
        assertEquals(r4.match("azbzd"),true);
        assertEquals(r4.match("acbd"),false);
        assertEquals(r4.match("acbddd"),true);
        assertEquals(r4.match("acbdd"),true);
        assertEquals(r4.match("accbd"),false);
        assertEquals(r4.match("abcd"),true);
        assertEquals(r4.match("adbabbacdd"),false);
        assertEquals(r4.match("adbabbacbdd"),true);
        assertEquals(r4.match("adbabbacbadbdd"),true);
        assertEquals(r4.match("adbabbacbadbd"),false);
        assertEquals(r4.match("abdddd"), false);
        assertEquals(r4.match("abbdddd"), true);
        assertEquals(r4.match("acd"),false);
        assertEquals(r4.match("cd")  ,true);
        assertEquals(r4.match("ccccd")  ,false);
        assertEquals(r4.match("ababd"),false);
        assertEquals(r4.match("bbbbcd")  ,true);
        assertEquals(r4.match("bcd")  ,true);
        assertEquals(r4.match("acbd") ,false);
        assertEquals(r4.match("abd") ,true);
        assertEquals(r4.match("bbccd"),false);
        assertEquals(r4.match("bbd")  ,false);
        assertEquals(r4.match("bbbcd") ,true);
        assertEquals(r4.match("cccccd") ,false);
    }
}
