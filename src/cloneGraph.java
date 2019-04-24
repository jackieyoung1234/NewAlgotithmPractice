import graph.UndirectedGraphNode;

import java.util.HashMap;

public class cloneGraph {
    HashMap<Integer, UndirectedGraphNode> bb;// = new HashMap<>();
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null)  return null;
        this.bb = new HashMap<>();
        return dfs(node,null);
    }

    //r is original node, parent is new node
    private UndirectedGraphNode dfs(UndirectedGraphNode r,UndirectedGraphNode parent){
        //h.add(r.label);
        UndirectedGraphNode rp = new UndirectedGraphNode(r.label);
        if(parent!=null){
            rp.neighbors.add(parent);
            parent.neighbors.add(rp);
        }
        bb.put(r.label,rp);
        for(UndirectedGraphNode n: r.neighbors){
            if(!bb.containsKey(n.label))
                dfs(n,rp);
            else if(n.label==rp.label){
                rp.neighbors.add(rp);
            }
        }
        return rp;
    }
    //private HashMap<Integer, UndirectedGraphNode> map = new HashMap<>();
    //public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    //    return clone(node);
    //}

    //private UndirectedGraphNode clone(UndirectedGraphNode node) {
    //    if (node == null) return null;

    //    if (map.containsKey(node.label)) {
    //        return map.get(node.label);
    //    }
    //    UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
    //    map.put(clone.label, clone);
    //    for (UndirectedGraphNode neighbor : node.neighbors) {
    //        clone.neighbors.add(clone(neighbor));
    //    }
    //    return clone;
    //}
    public static void main(String[] args){
        UndirectedGraphNode n_1 = new UndirectedGraphNode(-1);
        UndirectedGraphNode n1 = new UndirectedGraphNode(1);
        n_1.neighbors.add(n1);
        n1.neighbors.add(n_1);
        UndirectedGraphNode new_n_1 = new cloneGraph().cloneGraph(n_1);
    }
}
