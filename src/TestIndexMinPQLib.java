import edu.princeton.cs.algs4.IndexMinPQ;

public class TestIndexMinPQLib {
    public static void main(String[] args){
       IndexMinPQ<Integer>  p = new IndexMinPQ<>(4);
       p.insert(2,1);
        p.insert(1,2);
        p.insert(3,3);
        p.delete(1);
    }
}
