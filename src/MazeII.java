import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
public class MazeII {
    public class IndexPQ{
        int[] k2v;
        int[] k2i;
        int[] i2k;
        int ptr;
        public IndexPQ(){
            // x 0-99, y 0-99
            //hei, wid <=100, index =x*100+wid, max index number  = 100 * 100 = 10000
            //max = 99*100 + 99 = 100*100
            k2v = new int[10001];
            Arrays.fill(k2v,Integer.MAX_VALUE);
            k2i = new int[10001];
            i2k = new int[10001];
            ptr = 1;//cur write index
        }
        private boolean smallerThan(int i, int j){
            return k2v[i2k[i]]<k2v[i2k[j]];
        }
        private void exch(int i, int j){
            int temp = i2k[i];
            i2k[i] = i2k[j];
            i2k[j] = temp;
            k2i[temp] = j;
            k2i[i2k[i]] = i;
        }
        private void swim(int i){
            int parent;
            while((parent=(i>>1))>0){
                if(smallerThan(i,parent)){
                    exch(i,parent);
                    i = parent;
                }else{
                    break;
                }
            }
        }
        private void sink(int i){
            int child;
            while((child=(i<<1))<ptr){
                if(child+1<ptr&&smallerThan(child+1,child)) child++;
                if(smallerThan(child,i)){
                    exch(child,i);
                    i = child;
                }else{
                    break;
                }
            }
        }
        public boolean isEmpty(){
            return ptr==1;
        }
        // key is x*100+wid
        public void put(int key, int val){
            k2v[++key]=val;
            i2k[ptr]=key;
            k2i[key]=ptr;
            swim(ptr++);
        }
        public void change(int key, int val){
            k2v[++key] = val;
            swim(k2i[key]);
            sink(k2i[key]);
        }
        public boolean contains(int key){
            return k2i[++key]!=0;
        }
        //pop the [key,val] with the minimum value
        public int[] poll(){
            int reskey = i2k[1];
            int[] xy = convert2xy(reskey);
            exch(1,--ptr);
            sink(1);
            k2i[reskey]=0;
            return new int[]{xy[0],xy[1],k2v[reskey]};
        }
        private int[] convert2xy(int index){
            return new int[]{(--index)/100,index%100};
        }
    }
    private int convert2i(int x, int y){
        return x*100+y;
    }
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int m = maze.length, n = maze[0].length;
        boolean[][] visited = new boolean[m][n];
        IndexPQ pq = new IndexPQ();
        pq.put(convert2i(start[0],start[1]),0);
        int[] cur;
        while(!pq.isEmpty()){
            cur = pq.poll();
            if(cur[0]==destination[0] && cur[1]== destination[1]) return cur[2];
            helper(maze,m,n,cur,visited,pq);
        }
        return -1;
    }
    private void helper(int[][] maze,int m, int n,int[] v,boolean[][] visited, IndexPQ pq){
        int x = v[0], y=v[1];
        int nx, ny,step,neikey;
        visited[x][y] = true;
        //up
        nx = x;
        ny = y;
        step = 0;
        while(nx>0&&maze[nx-1][ny]!=1){
            nx--;
            step++;
        }
        neikey = convert2i(nx,ny);
        if(!visited[nx][ny]&&step+v[2]<pq.k2v[neikey+1]){
            if(pq.contains(neikey)){
                pq.change(neikey,step+v[2]);
            }else{
                pq.put(neikey,step+v[2]);
            }
        }
        //right
        nx = x;
        ny = y;
        step = 0;
        while(ny<n-1&&maze[nx][ny+1]!=1){
            ny++;
            step++;
        }
        neikey = convert2i(nx,ny);
        if(!visited[nx][ny]&&step+v[2]<pq.k2v[neikey+1]){
            if(pq.contains(neikey)){
                pq.change(neikey,step+v[2]);
            }else{
                pq.put(neikey,step+v[2]);
            }
        }
        //down
        nx = x;
        ny = y;
        step = 0;
        while(nx<m-1&&maze[nx+1][ny]!=1){
            nx++;
            step++;
        }
        neikey = convert2i(nx,ny);
        if(!visited[nx][ny]&&step+v[2]<pq.k2v[neikey+1]){
            if(pq.contains(neikey)){
                pq.change(neikey,step+v[2]);
            }else{
                pq.put(neikey,step+v[2]);
            }
        }
        //left
        nx = x;
        ny = y;
        step = 0;
        while(ny>0&&maze[nx][ny-1]!=1){
            ny--;
            step++;
        }
        neikey = convert2i(nx,ny);
        if(!visited[nx][ny]&&step+v[2]<pq.k2v[neikey+1]){
            if(pq.contains(neikey)){
                pq.change(neikey,step+v[2]);
            }else{
                pq.put(neikey,step+v[2]);
            }
        }
    }
    public static void main(String[] args){
        MazeII m = new MazeII();
        LinkedList<Integer> l = new LinkedList<>();
        l.add(2);
        l.add(1);
        l.add(4);
        System.out.println(l);
        l.remove(Integer.valueOf(1));
        System.out.println(l);
        for(int n : l){
            System.out.println(n);
        }
        int res = m.shortestDistance(
               new int[][]{
                       {0,0,1,0,0},
                       {0,0,0,0,0},
                       {0,0,0,1,0},
                       {1,1,0,1,1},
                       {0,0,0,0,0}
               },
               new int[]{0,4},
               new int[]{4,4}
        );
    }
}
