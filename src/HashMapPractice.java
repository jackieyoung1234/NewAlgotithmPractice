public class HashMapPractice<K,V> {
    KeyValuePairList[] chains;
    private class KeyValuePairList{
        private class Node{
            K key;
            V value;
            Node next;
            public Node(){

            }
            public Node(K key, V value){
                this.key = key;
                this.value = value;
            }
        }
        Node root;
        public KeyValuePairList(){
            root = new Node();
        }
        public void add(K key, V value){
           Node n = new Node(key,value);
           n.next = root.next;
           root.next = n;
        }
        public boolean contains(K key){
           Node cur = root.next;
           while(cur!=null){
               if(cur.key == key) return true;
              cur = cur.next;
           }
           return false;
        }
        public void remove(K key){
           Node cur = root, next;
           while((next=cur.next)!=null){
              if(next.key == key) cur.next = next.next;
              cur = next;
           }
        }
    }
    public HashMapPractice(int m){
        chains = new HashMapPractice.KeyValuePairList[4];
        for(int i=0;i<4;i++){
            chains[i] = new KeyValuePairList();
        }
    }
    private KeyValuePairList get(int i){
       return chains[i];
    }
    public void put(int i, K k, V v){
       get(i).add(k,v);
    }
    public boolean contains(int i, K k){
       return get(i).contains(k);
    }
    public void remove(int i, K k){
       get(i).remove(k);
    }
    public static void main(String[] args){
       HashMapPractice<String,String>  hm = new HashMapPractice<>(4);
       hm.put(1,"1","1");
       System.out.println(hm.contains(1,"1"));
       hm.put(1,"2","1");
        System.out.println(hm.contains(1,"2"));
        hm.remove(1,"1");
        System.out.println(hm.contains(1,"1"));
        System.out.println(hm.contains(1,"2"));
    }
}
