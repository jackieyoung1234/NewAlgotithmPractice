import java.util.ArrayList;
import java.util.List;

public class NumberofIslandsII {
    private class UF{
        int[] pa;
        int[] rank;
        //id 0 is not used
        public UF(int size){
            this.pa = new int[size+1];
            this.rank = new int[size+1];
        }
        //  not initialzing parent array in constructor
        //  because addLand operation may be sparse
        //  so that there are lots of water grids
        //  which don't need parent id, the parent for water is 0
        //when invoking this method,
        //use original index+1 as id
        public void setPid(int id){
            if(pa[id]==0){
                pa[id] = id;
                rank[id]++;
            }
        }
        //when invoking this method,
        //use origianl index+1 as id
        public int findParent(int id){
            int pid = pa[id];
            while(pid!=id){
                pa[id] = pa[pid];
                id = pid;
                pid = pa[pid];
            }
            return id;
        }
        public boolean connect(int id1, int id2){
            int pa2 = findParent(id2);
            if(pa2==0) return false;
            int pa1 = findParent(id1);
            if(pa1==pa2){ return false;}
            else{
                int comp = Integer.compare(rank[pa1],rank[pa2]);
                if(comp<=0){
                    rank[pa2] += rank[pa1];
                    pa[pa1] = pa2;
                }else{
                    rank[pa1] += rank[pa2];
                    pa[pa2] = pa1;
                }
            }
            return true;
        }
    }
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>(positions.length);
        UF uf = new UF(m*n+1);
        int id = 0,islcnt=0;
        for(int[] pos:positions){
            id = pos[0]*n + pos[1]+1;
            uf.setPid(id);
            islcnt++;
            if(pos[0]>0&&uf.connect(id,id-n)){
                islcnt--;
            }
            if(pos[0]<m-1&&uf.connect(id,id+n)){
                islcnt--;
            }
            if(pos[1]>0&&uf.connect(id,id-1)){
                islcnt--;
            }
            if(pos[1]<n-1&&uf.connect(id,id+1)){
                islcnt--;
            }
            res.add(islcnt);
        }
        return res;
    }
    public static void main(String[] args){
        //List<Integer> res = new NumberofIslandsII().numIslands2(
        //       3,3,
        //       new int[][]{{0,0},{0,1},{1,2},{2,1}}
        //);
        List<Integer> res = new NumberofIslandsII().numIslands2(
                3,3,
                new int[][]{{0,1},{1,2},{2,1},{1,0},{0,2},{0,0},{1,1},{1,1}}
        );
    }
}
