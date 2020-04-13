public class MyHashMap {

    //M = 8391401;
    private static final int M = 1000001;
    DLL[] buckets;
    /** Initialize your data structure here. */
    public class Node{
        int[] kv;
        Node prev;
        Node next;
        public Node(){
        }
        public Node(int[] kv){
            this.kv = kv;
        }
    }
    private class DLL{
        Node first;
        Node tail;
        int size;
        public DLL(){
            first = new Node();
            tail = new Node();
            first.next = tail;
            tail.prev = first;
            size = 0;
        }

        //add()
        public void add(int[] kv){
            Node newn = new Node(kv);
            first.next.prev = newn;
            newn.next = first.next;
            first.next = newn;
            newn.prev = first;
            size++;
        }

        //todo
        //iterator


        public Node find(int k){
            Node cur = first.next;
            while(cur!=tail){
                if(cur.kv[0]==k) return cur;
                cur = cur.next;
            }
            return null;
        }
        //todo
        public void findAndRemove(int k){
            Node cur=first.next;
            while(cur!=tail){
                if(cur.kv[0]==k){
                    cur.prev.next = cur.next;
                    cur.next.prev = cur.prev;
                    size--;
                    break;
                }
                cur = cur.next;
            }
        }

        //todo
        public int size(){
            return size;
        }
    }
    public MyHashMap() {
        //prime number which is bigger than 1000000
        //M = new BigInteger(20,20,new Random()).intValue();
        //M = 8391401;
        buckets = new DLL[M];
        //todo ini bucket element when using
        //for(ArrayList<int[]> b : buckets){
        //
        //}
        //todo cache hash value
    }

    //private int hash(int key){
    //   return (key%M);
    //}
    /** value will always be non-negative. */
    public void put(int key, int value) {
        //int keyHash = hash(key);
        DLL l;
        Node kv;
        //if((l = buckets[keyHash])==null){
        if((l = buckets[key])==null){
            //l = (buckets[keyHash] = new ArrayList<int[]>());
            l = (buckets[key] = new DLL());
            l.add(new int[]{key,value});
        }else{
            if((kv=l.find(key))!=null){
                kv.kv[1] = value;
            }else{
                l.add(new int[]{key,value});
            }
        }
    }
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        //ArrayList<int[]> l = buckets[hash(key)];
        DLL l = buckets[key];
        if(l==null||l.size()==0) return -1;
        Node kv;// = l.find(key);
        if((kv=l.find(key))==null) return -1;
        return kv.kv[1];
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        //ArrayList<int[]> l = buckets[hash(key)];
        DLL l = buckets[key];
        int size;
        if(l==null||l.size()==0) return;
        l.findAndRemove(key);
    }
    public static void main(String[] args){
       MyHashMap m = new MyHashMap();
       m.put(1,1);
       m.put(2,2);
        m.get(1);
        m.get(3);
        m.put(2,1);
        m.get(2);
        m.remove(2);
        m.get(2);
    }
}
