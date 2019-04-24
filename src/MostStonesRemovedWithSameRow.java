import java.util.HashMap;

public class MostStonesRemovedWithSameRow {
    private class UF{
        //for point[x,y],id = x + 10000*y
        //xaxis  -- id
        HashMap<Integer,Integer> xm;// = new HashMap<>();
        //yaxia  -- id
        HashMap<Integer,Integer> ym;// = new HashMap<>();
        int groupcnt;// = 0;
        public UF(){
            xm = new HashMap<>();
            ym = new HashMap<>();
            groupcnt = 0;
        }
        public void connect(int x, int y){
            int xp = getxp(x);
            int yp = getyp(y);
            int idc = x+10000*y;
            if(xp==-1&&yp==-1){
                xm.put(x,idc);
                ym.put(y,idc);
                groupcnt++;
            }else if(yp==-1){
                ym.put(y,xp);
            }else if(xp==-1){
                xm.put(x,xp);
            }else if(xp!=yp){
                ym.put(y,xp);
                groupcnt--;
            }
        }
        public int  getxp(int x){
            return xm.getOrDefault(x,-1);
        }
        public int  getyp(int y){
            return ym.getOrDefault(y,-1);

        }
    }
    public int removeStones(int[][] stones) {
        UF uf = new UF();
        for(int[] st: stones){
            int x = st[0];
            int y = st[1];
            uf.connect(x,y);
        }
        return uf.groupcnt;
    }
    public static void main(String[] args){
        MostStonesRemovedWithSameRow m = new MostStonesRemovedWithSameRow();
        int res = m.removeStones(
            new int[][]{{0,1},{1,0},{1,1}}
        );
        res = m.removeStones(
                new int[][]{
                        {0, 0},{0, 1},{1, 0},{1, 2},{2, 1},{2, 2}
                }
        );
    }
}
