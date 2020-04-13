import java.util.*;

public class CriticalConnectionsInANetwork {

        public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
            return null;
        }



        public static void main(String[] args){
            CriticalConnectionsInANetwork c = new CriticalConnectionsInANetwork();
            List<List<Integer>> res;
            res = c.criticalConnections(4, Arrays.asList(
                    Arrays.asList(0,1),
                    Arrays.asList(1,2),
                    Arrays.asList(2,0),
                    Arrays.asList(1,3)
            ));
        }
}
