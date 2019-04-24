import java.util.*;

public class ReconstructItinerary {
    public List<String> findItinerary(String[][] tickets) {
        //v, neighbours in lexial order
        HashMap<String, PriorityQueue<String>> digraph = new HashMap<>();
        //construct graph
        for(String[] e: tickets){
            if(!digraph.containsKey(e[0])){
                PriorityQueue<String> pq = new PriorityQueue<>();
                pq.offer(e[1]);
                digraph.put(e[0],pq);
            }else{
                digraph.get(e[0]).offer(e[1]);
            }
        }
        Stack<String> iter = new Stack<>();
        Stack<String> n2reverse = new Stack<>();
        iter.push("JFK");
        while(!iter.isEmpty()){
            String cur = iter.peek();
            if(!digraph.containsKey(cur)||digraph.get(cur).isEmpty()) {
                n2reverse.push(cur);
                iter.pop();
            }else{
                iter.push(digraph.get(cur).poll());
            }
        }
        Collections.reverse(n2reverse);
        return n2reverse;
    }
    public static void main(String[] args){
       ReconstructItinerary re = new ReconstructItinerary();
       int[] test = new int[0];
       List<String> res = re.findItinerary(
               new String[][]{
                       {"MUC", "LHR"},
                       {"JFK", "MUC"},
                       {"SFO", "SJC"},
                       {"LHR", "SFO"}
               }
       );
       int[] aa = new int[]{1,2};
       aa[0] ^= aa[1];
       aa[1] ^= aa[0];
       aa[0] ^= aa[1];
       aa = new int[]{1,2};
       aa[1] ^= aa[0];
       aa[0] ^= aa[1];
       aa[1] ^= aa[0];
    }
}
