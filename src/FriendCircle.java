import java.util.ArrayList;
import java.util.HashMap;

public class FriendCircle {
    //uf solution, not quickest
    private class UF{
        int[] rank;
        int[] parent;
        int size;
        public UF(int s){
            size = s;
            rank = new int[size];
            parent = new int[size];
            for(int i=0;i<size;i++){
                parent[i] = i;
            }
        }
        public void connect(int i, int j){
            int ip = findp(i);
            int jp = findp(j);
            if(ip==jp) return;
            if(rank[ip]==rank[jp]){
                rank[ip]++;
                parent[jp] = ip;
            }else if(rank[ip]>rank[jp]){
                parent[jp]=ip;
            }else{
                parent[ip]=jp;
            }
        }
        private int findp(int i){
            if(i!=parent[i]){
                parent[i] = findp(parent[i]);
            }
            return parent[i];
        }
        public int groupcnt(){
            int res = 0;
            for(int i=0;i<size;i++){
                if(i==parent[i]) res++;
            }
            return res;
        }
    }
    public int findCircleNum(int[][] M) {
        int len = M.length;
        //todo, validate edge case where len == 0
        UF u = new UF(len);
        for(int i=0;i<len;i++){
            for(int j=i+1;j<len;j++){
                if(M[i][j]==1) u.connect(i,j);
            }
        }
        return u.groupcnt();
    }
    public static void main(String[] args){
        FriendCircle f = new FriendCircle();
        ArrayList<Integer> l = new ArrayList<>();
        l.add(2);
        l.add(4);
        Integer ii = 1;
        boolean iii = ii == 1;
        l.remove(1);
        HashMap<Integer,Integer> hm  = new HashMap<>();
        hm.put(1,1);
        Integer rest = hm.computeIfPresent(1,(key,val)->{
           return null;
        });
        rest = hm.computeIfPresent(2,(key,val)->{
            return null;
        });
        hm.put(1,1);
        hm.put(1,null);
        Integer i = hm.get(1);
        i = 1;
        int i2 = (i++) -1;
        int res = f.findCircleNum(
            new int[][]{
               {1,0,0,1},
               {0,1,1,0},
               {0,1,1,1},
               {1,0,1,1},
            }
        );
    }
}
