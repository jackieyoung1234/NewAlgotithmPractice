import java.util.Comparator;
import java.util.TreeMap;

public class NestedClassAccessTest {
    A a;
    public NestedClassAccessTest(){
        this.build();
    }
    public class B{
        int i;
        public B(int v){
            this.i = v;
        }
    }
    public class A{
        B[] ba;
        public A(B b1, B b2){
            ba = new B[]{b1,b2};
        }
        /**
         * can't not put class B in A
         * or it wouldn't be accessable on the outer class
         */

    }
    public void build(){
        a = new A(new B(12), new B(13));
    }
    public static void main(String[] args){
        NestedClassAccessTest t = new NestedClassAccessTest();
        TreeMap<Integer,Integer> tm = new TreeMap<>(new Comparator<Integer>(){
           @Override
           public int compare(Integer i1, Integer i2){
              return Integer.compare(i2,i1);
           }
        });
    }

}
