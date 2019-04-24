package graph;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import java.util.NoSuchElementException;

public class Digraph {
    //private final int V;
    private static final String NEWLINE = System.lineSeparator();
    private int V;
    private int E;
    private Bag<Integer>[] adj;

    public Digraph(int V){
        if(V < 0) throw new IllegalArgumentException("vertex number could not be negative");
        this.V = V;
        E = 0;
        adj = new Bag[V];
        for(int temp = 0; temp < V ; temp ++){
            adj[temp] = new Bag<Integer>();
        }
    }
    public Digraph(In in){
        try{
            this.V = in.readInt();
            if(V < 0) throw new IllegalArgumentException("vertex number could not be negative");
            adj = new Bag[V];
            for(int temp = 0; temp < V ; temp ++){
                adj[temp] = new Bag<Integer>();
            }
            this.E = in.readInt();
            if(E < 0 ) throw new IllegalArgumentException("Edge number could not be negative");
            for(int t = 0; t < E; t++){
                addEdge(in.readInt(),in.readInt());
            }
        }catch(NoSuchElementException e){
            throw new IllegalArgumentException("input format invalid");
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

    public Digraph reverse(){
       Digraph reverseG = new Digraph(V);
       for(int temp = 0; temp < V ; V ++)
           for(int w : adj(temp))
               reverseG.addEdge(w,temp);
       return reverseG;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges" + NEWLINE);
        for(int t = 0; t < V; t ++){
           s.append(String.format("%d: ",t));
           for(int w: adj(t))
                s.append(w + " ");
           s.append(NEWLINE);
        }
        return s.toString();
    }
}
