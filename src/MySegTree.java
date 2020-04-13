import static org.junit.Assert.*;

public class MySegTree {
    Node[] narray;
    private class Node{
        Integer lazy;
        int sum;
        int from;
        int to;
        public Node(int from, int to, int sum){
            this.sum = sum;
            this.from = from;
            this.to = to;
        }
        public Node(int from, int to){
            this.from = from;
            this.to = to;
        }
    }
    public MySegTree(int[] a){
        int len = a.length;
        int size = 2*(int)Math.pow(2,Math.floor(Math.log(len)/Math.log(2)+1));
        narray = new Node[size];
        build(1,a,0,len-1);
    }
    private int build(int i, int[] a, int from, int to){
        narray[i] = new Node(from,to);
        if(from==to){
            return narray[i].sum=a[from];
        }
        int mid = from + (to-from)/2;
        return narray[i].sum = build(2*i,a,from,mid)+build(2*i+1,a,mid+1,to);
    }
    public void update(int from, int to, int v){
        update(1,from,to,v);
    }
    private void change(int i, int v){
        Node n = narray[i];
        n.sum += (n.to-n.from+1)*v;
        if(n.lazy==null) n.lazy = v;
        else n.lazy+=v;
    }
    private void propogate(int i){
        Node n = narray[i];
        if(n.lazy!=null&&n.lazy!=0){
            change(2*i,n.lazy);
            change(2*i+1,n.lazy);
            n.lazy=null;
        }
    }
    private void update(int i, int from, int to, int v){
        int thisfrom = narray[i].from;
        int thisto = narray[i].to;
        if(contains(from,to,thisfrom,thisto)){
            change(i,v);
            return;
        }
        if(intersect(from,to,thisfrom,thisto)){
            propogate(i);
            update(2*i,from,to,v);
            update(2*i+1,from,to,v);
            narray[i].sum = narray[2*i].sum+narray[2*i+1].sum;
        }
    }
    private int getSum(int from, int to){
        return getSum(1,from,to);
    }
    private int getSum(int i, int from, int to){
        Node ni = narray[i];
        int thisfrom = ni.from;
        int thisto = ni.to;
        if(contains(from, to, thisfrom, thisto)){
            return ni.sum;
        }
        if(intersect(from,to,thisfrom,thisto)){
            propogate(i);
            return getSum(2*i,from,to)+getSum(2*i+1,from,to);
        }
        return 0;
    }
    private boolean contains(int f, int t, int f2, int t2){
       return f2>=f&&t2<=t;
    }
    private boolean intersect(int f, int t, int f2, int t2){
        return !(t<f2||t2<f);
    }
    public static void main(String[] args){
        MySegTree m = new MySegTree(new int[]{1,1,1,1,1});
        assertEquals(m.getSum(1,3),3);
        assertEquals(m.getSum(2,4),3);
        assertEquals(m.getSum(3,4),2);
        m.update(1,3,2);
        assertEquals(m.getSum(1,3),9);
        assertEquals(m.getSum(2,4),7);
        assertEquals(m.getSum(3,4),4);
        m.update(1,3,-2);
        assertEquals(m.getSum(1,3),3);
        assertEquals(m.getSum(2,4),3);
        assertEquals(m.getSum(3,4),2);
        m.update(0,1,2);
        m.update(2,4,2);
        assertEquals(m.getSum(1,3),9);
        assertEquals(m.getSum(2,4),9);
        assertEquals(m.getSum(3,4),6);

    }
}
