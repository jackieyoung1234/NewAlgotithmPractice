import java.util.Arrays;
import java.util.PriorityQueue;
public class MazeII {
    private class P{
        public int x;
        public int y;
        public P(int x, int y){
            this.x = x;
            this.y = y;
        }
        @Override
        public int hashCode(){
            return 100*x+y;
        }
        @Override
        public boolean equals(Object o2){
            P p2 = (P)o2;
            return p2.x==this.x && p2.y == this.y;
        }
    }
    public int shortestDistance(int[][] maze, int[] start, int[] dest){
        int m=maze.length, n = maze[0].length;
        int[][] dist2 = new int[m][n];
        for(int[] row: dist2){
            Arrays.fill(row,Integer.MAX_VALUE);
        }
        boolean[][] marked = new boolean[m][n];
        PriorityQueue<P> virtexQ = new PriorityQueue<>(
                (p1,p2)->Integer.compare(dist2[p1.x][p1.y],dist2[p2.x][p2.y])
        );
        P nei=null,cur=null;
        dist2[start[0]][start[1]] = 0;
        virtexQ.offer(new P(start[0],start[1]));
        int x = 0, y = 0, d = 0;
        while(!virtexQ.isEmpty()){
            cur = virtexQ.poll();
            if(cur.x==dest[0]&&cur.y==dest[1]){
                return dist2[dest[0]][dest[1]];
            }
            marked[x][y] = true;
            //down
            x = cur.x;
            y = cur.y;
            while(x<m-1&&maze[x+1][y]!=1){
                x++;
                d++;
            }
            if(!marked[x][y]){
                if(dist2[x][y]>dist2[cur.x][cur.y]+d){
                    nei = new P(x,y);
                    dist2[x][y]=dist2[cur.x][cur.y]+d;
                    if(virtexQ.contains(nei)){
                        virtexQ.remove(nei);
                    }
                    virtexQ.offer(nei);
                }
            }
            //up
            d=0;
            x = cur.x;
            y = cur.y;
            while(x>0&&maze[x-1][y]!=1){
                x--;
                d++;
            }
            if(!marked[x][y]){
                if(dist2[x][y]>dist2[cur.x][cur.y]+d){
                    nei = new P(x,y);
                    dist2[x][y]=dist2[cur.x][cur.y]+d;
                    if(virtexQ.contains(nei)){
                        virtexQ.remove(nei);
                    }
                    virtexQ.offer(nei);
                }
            }
            //left
            d=0;
            x = cur.x;
            y = cur.y;
            while(y>0&&maze[x][y-1]!=1){
                y--;
                d++;
            }
            if(!marked[x][y]){
                if(dist2[x][y]>dist2[cur.x][cur.y]+d){
                    nei = new P(x,y);
                    dist2[x][y]=dist2[cur.x][cur.y]+d;
                    if(virtexQ.contains(nei)){
                        virtexQ.remove(nei);
                    }
                    virtexQ.offer(nei);
                }
            }
            //right
            d=0;
            x = cur.x;
            y = cur.y;
            while(y<n-1&&maze[x][y+1]!=1){
                y++;
                d++;
            }
            if(!marked[x][y]){
                if(dist2[x][y]>dist2[cur.x][cur.y]+d){
                    nei = new P(x,y);
                    dist2[x][y]=dist2[cur.x][cur.y]+d;
                    if(virtexQ.contains(nei)){
                        virtexQ.remove(nei);
                    }
                    virtexQ.offer(nei);
                }
            }

        }
        return -1;
    }
    public static void main(String[] args){
        MazeII m = new MazeII();
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
