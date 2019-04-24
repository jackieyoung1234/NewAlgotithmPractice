package graph;

public class DirectedDFS {
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
