import java.util.HashMap;

public class HashAndEqualTest {
    private class Class1{
       int i;
       int j;
       public Class1(int i, int j){
          this.i = i;
          this.j = j;
       }
       //@Override
       //public boolean equals(Object o2){
       //     Class1 h2 = (Class1)o2;
       //     return this.i == h2.i && this.j==h2.j;
       //}
       @Override
       public int hashCode(){
          Long temp =((long) j<<32)+i;
          return temp.hashCode();
       }
    }
    public boolean compare(){
        Class1 c = new Class1(1,1);
        Class1 cc = new Class1(1,1);
        return c==cc;
    }
    public boolean compareEqual(){
        Class1 c = new Class1(1,1);
        Class1 cc = new Class1(1,1);
        return c.equals(cc);
    }
    public int compareHashMap(){
        Class1 c = new Class1(1,1);
        Class1 cc = new Class1(1,1);
        HashMap<Class1, Integer> hm = new HashMap<>();
        hm.put(c,1);
        hm.put(cc,1);
        return hm.size();
    }
    public static void main(String[] args){
        HashAndEqualTest hae = new HashAndEqualTest();
        System.out.println(hae.compare());
        System.out.println(hae.compareEqual());
        System.out.println(hae.compareHashMap());
    }
}
