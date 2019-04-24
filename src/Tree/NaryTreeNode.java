package Tree;

import java.util.LinkedList;
import java.util.List;

public class NaryTreeNode {
    public int val;
    public List<NaryTreeNode> ch;
    public NaryTreeNode(int v){
       this.val = v;
       this.ch = new LinkedList<>();
    }
    public void addNei(NaryTreeNode nn){
        this.ch.add(nn);
    }

}
