import java.util.Arrays;
import java.util.PriorityQueue;

public class MazeIII {
    private class P implements Comparable<P>{
        public int x;
        public int y;
        public String path;
        //todo modify P instansiate
        public int d;

        public P(int x, int y,int d,String p){
            this.x = x;
            this.y = y;
            this.d = d;
            this.path = p;
        }
        @Override
        public int hashCode(){
            return 30*x+y;
        }
        @Override
        public boolean equals(Object o2){
            P p2 = (P) o2;
            return p2.x==this.x&&p2.y==this.y;
        }
        @Override
        public int compareTo(P p2){
            int comp = Integer.compare(this.d,p2.d);
            return comp!=0?comp:this.path.compareTo(p2.path);
        }
    }
    public String findShortestWay(int[][] maze, int[] ball, int[] hole){
        int m = maze.length, n = maze[0].length;
        P[][] dist2 = new P[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                dist2[i][j] = new P(i,j,Integer.MAX_VALUE,"");
            }
        }
        //virtex to a point
        //represent the shortest edge in the spt
        //for p[x][y], id is x*n+y;
        PriorityQueue<P> virtexQ = new PriorityQueue<>(
        );
        dist2[ball[0]][ball[1]] = new P(ball[0],ball[1],0,"");
        virtexQ.offer(dist2[ball[0]][ball[1]]);
        P cur=null, next = null;
        int x = 0, y = 0, d =0;
        while(!virtexQ.isEmpty()){
            cur = virtexQ.poll();
            if(cur.x==hole[0]&&cur.y==hole[1]) return cur.path;
            //down left right up neibour
            //down
            d=0;
            x=cur.x;
            y=cur.y;
            while(((x!=hole[0])||(y!=hole[1]))&&x<m-1&&maze[x+1][y]==0){
                d++;
                x++;
            }
            next = new P(x,y,dist2[cur.x][cur.y].d+d,cur.path+"d");
            if(next.compareTo(dist2[x][y])<0) {
                dist2[x][y] = next ;
                if(virtexQ.contains(next))
                    virtexQ.remove(next);
                virtexQ.offer(next);
            }
            //left
            d=0;
            x=cur.x;
            y=cur.y;
            while(((x!=hole[0])||(y!=hole[1]))&&y>0&&maze[x][y-1]==0){
                d++;
                y--;
            }
            next = new P(x,y,dist2[cur.x][cur.y].d+d,cur.path+"l");
            if(next.compareTo(dist2[x][y])<0) {
                dist2[x][y] = next ;
                if(virtexQ.contains(next))
                    virtexQ.remove(next);
                virtexQ.offer(next);
            }
            //right
            d=0;
            x=cur.x;
            y=cur.y;
            while(((x!=hole[0])||(y!=hole[1]))&&y<n-1&&maze[x][y+1]==0){
                d++;
                y++;
            }
            next = new P(x,y,dist2[cur.x][cur.y].d+d,cur.path+"r");
            if(next.compareTo(dist2[x][y])<0) {
                dist2[x][y] = next ;
                if(virtexQ.contains(next))
                    virtexQ.remove(next);
                virtexQ.offer(next);
            }
            //up
            d=0;
            x=cur.x;
            y=cur.y;
            while(((x!=hole[0])||(y!=hole[1]))&&x>0&&maze[x-1][y]==0){
                d++;
                x--;
            }
            next = new P(x,y,dist2[cur.x][cur.y].d+d,cur.path+"u");
            if(next.compareTo(dist2[x][y])<0) {
                dist2[x][y] = next ;
                if(virtexQ.contains(next))
                    virtexQ.remove(next);
                virtexQ.offer(next);
            }
        }
        return "impossible";
    }
    public static void main(String[] args){
       MazeIII m = new MazeIII();
       String res = m.findShortestWay(
               new int[][]{{0,0,0,0,0},{1,1,0,0,1},{0,0,0,0,0},{0,1,0,0,1},{0,1,0,0,0}},
               new int[]{4,3},
               new int[]{0,1}
       );
    }
}
