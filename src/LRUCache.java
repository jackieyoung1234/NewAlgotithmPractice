import java.util.HashMap;

public class LRUCache {
    private class Node{
        Node prev;
        Node next;
        int val;
        int key;
        public Node(int v,int k){
            this.val = v;
            this.key = k;
            prev = null;
            next = null;
        }
        public void addToFirst(Node fh){
            this.next = fh.next;
            this.prev = fh;
            fh.next = this;
        }
        public void remove(){
            this.prev.next = this.next;
            this.next.prev = this.prev;
            this.next = null;
            this.prev =null;
        }
        public void addBehind(Node n){
            this.next = n.next;
            this.prev = n;
            n.next.prev =this;
            n.next = this;
        }
    }
    Node fh;
    Node last;
    //Map key - node
    HashMap<Integer, Node> hm;
    int curs;
    int cap;
    public LRUCache(int capacity) {
        this.curs = 0;
        this.fh = new Node(-2,0);
        this.last = new Node(-3,0);
        this.fh.next = last;
        this.last.prev = fh;
        this.hm = new HashMap<Integer,Node>();
        this.cap = capacity;
    }

    public int get(int key) {
        Node res = hm.get(key);
        if(res==null) return -1;
        res.remove();
        res.addBehind(fh);
        return res.val;
    }

    public void put(int key, int value) {
        Node dup = hm.get(key);
        //if contains key
        //node set to new value;
        //node remove
        //node add to first
        if(dup!=null){
            dup.val= value;
            dup.remove();
            dup.addBehind(fh);
        }else{
            //else
            dup = new Node(value,key);
            //add new node to hm
            //cap reach
            if(curs==cap){
                if(curs>0){
                    hm.put(key,dup);
                    //remove last from list
                    hm.remove(last.prev.key);
                    //remove from hm
                    last.prev.remove();
                    //add new to first
                    dup.addBehind(fh);
                }
                //else
                //    do nothing
            }else{
                //not cap reach
                hm.put(key,dup);
                curs++;
                //add new to first
                dup.addBehind(fh);
            }
        }
    }
}
