import java.util.LinkedList;

public class AlienDictionary {
    public String alienOrder(String[] words) {
        if(words.length==0) return "";
        if(words.length==1) return words[0];
        LinkedList<Character>[] g = new LinkedList[26];
        build(words,g);
        boolean[] visited = new boolean[26];
        boolean[] onstack = new boolean[26];
        StringBuilder sb = new StringBuilder();
        for(int c = 0; c<26;c++){
            if(!visited[c]&&g[c]!=null){
                if(!dfs(c,g,sb,visited,onstack)) return "";
            }
        }
        return sb.reverse().toString();
    }
    private void build(String[] words, LinkedList<Character>[] g){
        String s1 = null, s2 = null;
        int len = 0;
        int c1 = 0,c2=0;
        for(int i=0;i<words.length-1;i++){
            s1 = words[i];
            s2 = words[i+1];
            len = Math.min(s1.length(),s2.length());
            int j=0;
            for(;j<len;j++){
                c1 = s1.charAt(j)-'a';
                c2 = s2.charAt(j)-'a';
                if(g[c1]==null)
                    g[c1] = new LinkedList<>();
                if(g[c2]==null)
                    g[c2] = new LinkedList<>();
                if(c1!=c2){
                    g[c1].add((char)('a'+c2));
                    break;
                }
            }
            int j2 = j;
            while(j<s1.length()){
                c1 = s1.charAt(j++)-'a';
                if(g[c1]==null)
                    g[c1] = new LinkedList<>();
            }
            while(j2<s2.length()){
                c2 = s2.charAt(j2++)-'a';
                if(g[c2]==null)
                    g[c2] = new LinkedList<>();
            }
        }
    }
    private boolean dfs(int c, LinkedList<Character>[] g, StringBuilder sb, boolean[] v, boolean[] onstack){
        v[c]=true;
        onstack[c]=true;
        int neic = 0;
        for(char nei:g[c]){
            neic = nei-'a';
            if(onstack[neic]) return false;//onstack cycle
            else if(!v[neic]){
                if(!dfs(neic,g,sb,v,onstack))
                    return false;

            }
        }
        onstack[c]=false;
        sb.append((char)('a'+c)); //post reverse order
        return true;
    }
}
