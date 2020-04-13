import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeMap;

public class MaxStack {
    private class Node<V>{
        Node prev;
        Node next;
        V val;
        public Node(){
        }
        public Node(V val){
            this.val = val;
        }
    }
    private class DLL<V>{
        Node<V> front;
        Node<V> tail;
        public DLL(){
            front = new Node<>();
            tail = new Node<>();
            front.next = tail;
            tail.prev = front;
        }
        public void addFirst(Node<V> n){
            n.next = front.next;
            front.next.prev = n;
            front.next = n;
            n.prev = front;
        }
        public Node<V> removeFirstNode(){
            Node<V> res = front.next;
            front.next = res.next;
            res.next.prev = front;
            return res;
        }
        public V removeFirst(){
            return removeFirstNode().val;
        }
        public void remove(Node n){
            n.prev.next = n.next;
            n.next.prev = n.prev;
        }
        private Node<V> getFirstNode(){
           return front.next;
        }
        public V getFirst(){
            return getFirstNode().val;
        }
        public boolean isEmpty(){
            return front.next == tail;
        }

    }
    DLL<Integer> dll;
    TreeMap<Integer, DLL<Node<Integer>>> tm;
    /** initialize your data structure here. */
    public MaxStack() {
        dll = new DLL<>();
        tm = new TreeMap<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer i1, Integer i2){
                return Integer.compare(i2,i1);
            }
        });
    }

    public void push(int x) {
        Node newNode = new Node(x);
        dll.addFirst(newNode);
        //tm.computeIfAbsent(x,x->new LinkedList<Node>()).add(newNode);
        DLL<Node<Integer>> l;
        if((l=tm.get(x))==null){
            l = new DLL();
            l.addFirst(new Node<Node<Integer>>(newNode));//add to the end
            tm.put(x,l);
        }else{
            l.addFirst(new Node<Node<Integer>>(newNode));
        }
    }

    public int pop() {
        int val = dll.removeFirst();
        //??? not removing the correct object
        DLL<Node<Integer>> l = tm.get(val);
        l.removeFirst();
        if(l.isEmpty()) tm.remove(val);
        return val;
    }

    public int top() {
        //todo node.val
        return dll.getFirst();
    }

    public int peekMax() {
        return tm.firstKey();
    }

    public int popMax() {
        int max = tm.firstKey();
        DLL<Node<Integer>> l = tm.get(max);
        Node<Node<Integer>> mn = l.removeFirstNode();
        if(l.isEmpty()) tm.remove(max);
        dll.remove(mn.val);
        return max;
    }
    public static void main(String[] args){
        MaxStack m = new MaxStack();
        m.push(-2);
        m.popMax();
        m.push(-45);
        m.push(-82);
        m.push(29);
        m.pop();
        m.peekMax();
        m.push(40);
        m.pop();
        m.pop();
        m.push(66);
        m.peekMax();
        m.peekMax();
        m.push(-61);
        m.peekMax();
        m.push(98);
        m.peekMax();
    }
}
