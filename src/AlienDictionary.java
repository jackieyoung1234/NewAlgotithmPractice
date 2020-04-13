import java.util.LinkedList;

public class AlienDictionary {
    public String alienOrder(String[] words){
        if(words.length==0) return "";
        if(words.length==1) return words[1];
        LinkedList<Character>[] graph = new LinkedList[26];
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[26];
        boolean[] onstack = new boolean[26];
        boolean[] used    = new boolean[26];
        for(int i=0;i<26;i++){
            graph[i] = new LinkedList<>();
        }
        for(int i=0;i<words.length-1;i++){
            build(words[i],words[i+1],graph,used);
        }
        for(int i=0;i<26;i++){
            if(used[i]&&!visited[i]){
                if(!dfs(i,graph,visited,onstack,sb)) return "";
            }
        }
        return sb.toString();
    }
    private boolean dfs(int v, LinkedList<Character>[] graph, boolean[] visited, boolean[] onstack,StringBuilder sb){
        visited[v] = true;
        onstack[v] = true;
        int ichi;
        for(char chi : graph[v]){
            ichi = chi-'a';
            if(!visited[ichi])
                if(!dfs(ichi,graph,visited,onstack,sb)) return false;
                else if(onstack[ichi]) return false;
        }
        onstack[v] = false;
        sb.insert(0,(char)('a'+v));
        return true;
    }
    private void build(String s1, String s2,LinkedList<Character>[] graph,boolean[] used){
        int ptr = 0, len1 = s1.length(), len2 = s2.length();
        int minLen = Math.min(len1,len2);
        int c1,c2;
        char cc2;
        for(;ptr<minLen;ptr++){
            c1 = s1.charAt(ptr)-'a';
            cc2 = s2.charAt(ptr);
            c2 = cc2-'a';
            used[c1] = true;
            if(c1!=c2){
                used[c2] = true;
                graph[c1].add(cc2);
                break;
            }
        }
        int ptrbk = ptr;
        while(ptr<len1){
            used[s1.charAt(ptr++)-'a'] = true;
        }
        while(ptrbk<len2){
            used[s2.charAt(ptrbk++)-'a'] = true;
        }
    }
    public static void main(String[] args){
        int[][] aa = new int[][]{{1,2},{3,4}};
        int[][] copy = new int[2][2];
        for(int i=0;i<aa.length;i++){
            copy[i] = aa[i].clone();
        }
        aa[0][0] = 2;
       AlienDictionary a = new AlienDictionary();
       String res = a.alienOrder(new String[]{"x","y","x"});
    }
}
