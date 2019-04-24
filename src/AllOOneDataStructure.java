import java.util.HashMap;
public class AllOOneDataStructure {
    HashMap<String, Node> map;
    Node head, tail;
    /** Initialize your data structure here. */
    class Node{
        Node last,next;
        int val;
        String s;
        Node(int v, String str){val=v;s=str;}
    }
    public AllOOneDataStructure() {
        map = new HashMap<String, Node>();
        head=null;
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if(map.containsKey(key))
        {
            Node cur = map.get(key);
            cur.val++;
            while(cur.last!=null && cur.last.val<cur.val)
            {
                Node tmp = cur.last;
                cur.last.next=cur.next;
                cur.last=cur.last.last;
                if(cur.next!=null) cur.next.last=tmp;
                if(tmp.last!=null) tmp.last.next=cur;
                tmp.last=cur;cur.next=tmp;
                if(tail==cur) tail=tmp;
                if(head==tmp) head=cur;
            }
        }
        else{
            Node nn = new Node(1,key);
            map.put(key, nn);
            if(tail!=null){
                tail.next=nn;
                nn.last=tail;
                tail = nn;
            }else{
                head=nn;tail=nn;
            }
        }
    }
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if(map.containsKey(key))
        {
            Node cur = map.get(key);
            cur.val--;
            if(cur.val==0){
                if(cur.last!=null) cur.last.next=cur.next;
                if(cur.next!=null) cur.next.last=cur.last;
                if(cur==head) head=cur.next;
                if(cur==tail) tail=cur.last;
            }
            else{
                while(cur.next!=null && cur.next.val>cur.val)
                {
                    Node tmp = cur.next;
                    cur.next.last=cur.last;
                    cur.next=cur.next.next;
                    if(cur.last!=null) cur.last.next=tmp;
                    if(tmp.next!=null) tmp.next.last=cur;
                    tmp.next=cur;cur.last=tmp;
                    if(head==cur) head=tmp;
                    if(tail==tmp) tail=cur;
                }
            }
        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if(head==null) return "";
        return head.s;
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if(tail==null) return "";
        return tail.s;
    }
/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
    public static void main(String[] args){
       AllOOneDataStructure o = new AllOOneDataStructure();
       o.inc("a");
       o.inc("c");
       o.inc("c");
       o.inc("c");
       o.dec("a");
       String res = o.getMinKey();
       long l = Integer.MAX_VALUE;
       int i = (int)l;
    }
}


