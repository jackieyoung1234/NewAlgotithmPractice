public class MostCommonWord {
    private class Trie{
        private static final int R = 26;//character set
        private Node root;
        private class Node{
            private Node[] next = new Node[R];
            private NV val;
        }
        public void insert(String key, NV v){
            root = insert(root,key,v,0);
        }
        private Node insert(Node n , String k, NV v, int level){
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
        public NV search(String key){
            //if(key==null) return null;
            Node res = search(root,key,0);
            return res==null?null:res.val;
        }
        public Node searchNode(String key){
            return search(root,key,0);
        }
        public Node search(Node n, String k, int level){
            if(n==null) return null;
            if(level==k.length()) return n;
            int index = k.charAt(level)-'a';
            return search(n.next[index],k,level+1);
        }

    }
    //custom node value object
    private class NV{
        int cnt;
        boolean banned;
        public NV(){
            cnt = 0;
            banned = false;
        }
        public NV(boolean b){
            cnt = 0;
            banned = b;
        }
        public NV(int n){
            cnt = n;
            banned = false;
        }
    }
    private int convs(String p, int st,StringBuilder sb){
        char n = p.charAt(st++);
        sb.append(n>'Z'?n:(char)(n-'A'+'a'));
        for(;st<p.length();st++){
            n = p.charAt(st);
            if(n>='a'&&n<='z') sb.append(n);
            else if(n>='A'&&n<='Z') sb.append((char)(n-'A'+'a'));
            else break;
        }
        return st;
    }
    public String mostCommonWord(String paragraph, String[] banned) {
        Trie t = new Trie();
        int st=0,end=0;
        char cur = '\u0000';
        StringBuilder sb;
        int max2n = 0;
        String res = null;
        Trie.Node n = null;
        for(String b : banned){
            t.insert(b,new NV(true));
        }
        for(int i=0;i<paragraph.length();i++){
            cur = paragraph.charAt(i);
            if((cur>='a'&&cur<='z')||(cur>='A'&&cur<='Z')){
                sb = new StringBuilder();
                i = convs(paragraph,i,sb);
                n= t.searchNode(sb.toString());
                if(n==null){
                    t.insert(sb.toString(),new NV(1));
                    if(1>max2n){
                        max2n = 1;
                        res = sb.toString();
                    }
                }
                else if(n.val==null){
                    n.val = new NV(1);
                    if(1>max2n){
                        max2n = 1;
                        res = sb.toString();
                    }

                }else if(n.val.banned==true){
                    continue;
                }
                else{
                    if(++n.val.cnt>max2n){
                        max2n = n.val.cnt;
                        res = sb.toString();
                    }
                }
            }
        }
        return res;
    }
    public static void main(String[] args){
       MostCommonWord m = new MostCommonWord();
       String res = m.mostCommonWord(
               "Bob hit a ball, the hit BALL flew far after it was hit.",
               new String[]{"hit"});
    }
}
