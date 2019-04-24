import java.util.ArrayList;
import java.util.HashMap;

public class Map_Consumer_BIFunction {
    private class Pair{
        String name;
        public Pair(String n){
           this.name = n;
        }
        @Override
        public String toString(){
           return name;
        }
    }
    public void test(){
        HashMap<Double, ArrayList<Pair>> m= new HashMap<>();
        m.computeIfAbsent(1.0,list->new ArrayList<Pair>()).add(new Pair("a"));
        m.computeIfAbsent(2.0,list->new ArrayList<Pair>()).add(new Pair("b"));
        m.forEach(
                (tan,plist)->{
                    System.out.println("tan "+tan+" has members: "+plist.toString());
                }
        );
        m.computeIfAbsent(2.0,list->new ArrayList<Pair>()).add(new Pair("c"));
        m.forEach((tan, plist)->{
            System.out.println("tan "+tan+" has members: "+plist.toString());
        });
        m.computeIfAbsent(3.0,list->new ArrayList<Pair>()).add(new Pair("c"));
        m.forEach((tan,plist)->{
           System.out.println("tan "+ tan + " has members "+plist.toString());
        });
        m.computeIfPresent(
                3.0, (key,list) ->
                    list.removeIf(e->
                       e.name.equals("c")
                    ) &&
                    list.isEmpty()?
                            null:list

        );
        m.forEach((tan,list)->{
           System.out.println(
                  "tan " + tan + " has members "+ list.toString()
           );
        });
        System.out.println("---------------------------");
        m.computeIfAbsent(3.0,list -> new ArrayList<Pair>()).add(new Pair("c"));
        m.forEach(
                (tan,list) ->{
                    System.out.println(
                            "tan " + tan + " has members "+ list.toString()
                    );

                }
        );
        m.replaceAll((tan,list)-> {
                    list.replaceAll(e -> {
                        if (e.name.equals("c")) e.name = "CC";
                        return e;
                    });
                    return list;
                }
        );
        m.forEach((tan,list)->{
            System.out.println(
                    "tan " + tan + " has members "+ list.toString()
            );
        });
    }
    public double minAreaFreeRect(int[][] points) {
        return 0.0;
    }
    public static void main(String[] args){
        new Map_Consumer_BIFunction().test();
    }}

