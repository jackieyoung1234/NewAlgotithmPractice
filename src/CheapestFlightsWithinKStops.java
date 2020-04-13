import java.util.LinkedList;
import java.util.PriorityQueue;

public class CheapestFlightsWithinKStops {
    private class City implements Comparable<City>{
        int dis;
        //if cur city stop <= K, then next city can be added to pq
        //src city with stop = 0;
        int stop;
        int index;
        public City(int i, int dis, int stop){
            this.index = i;
            this.dis = dis;
            this.stop = stop;
        }
        @Override
        public int compareTo(City c2){
            int comp = Integer.compare(this.dis,c2.dis);
            if(comp!=0) return comp;
            return Integer.compare(this.stop,c2.stop);
        }
    }
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        PriorityQueue<City> pq = new PriorityQueue<>();
        LinkedList[] adjList = new LinkedList[n];
        pq.offer(new City(src,0,0));
        int[] dis2 = new int[n];
        int[] stop = new int[n];
        for(int i=0;i<n;i++){
            dis2[i] = Integer.MAX_VALUE;
            stop[i] = Integer.MAX_VALUE;
            adjList[i] = new LinkedList<int[]>();
        }
        for(int[] f: flights){
            adjList[f[0]].add(new int[]{f[1],f[2]});
        }
        dis2[src] = 0;
        stop[src] = 0;
        City cur = null, next = null;
        int newdis = 0, neibourIndex = 0;
        while(!pq.isEmpty()){
            cur = pq.poll();
            if(cur.index==dst) return cur.dis;
            //todo build adj list first
            for(int[] info: (LinkedList<int[]>)adjList[cur.index]){
                neibourIndex = info[0];
                newdis =  cur.dis+info[1];
                if(cur.stop<=K&&
                        (newdis<dis2[neibourIndex]  ||
                        cur.stop+1<stop[neibourIndex]
                        )
                ){
                    dis2[neibourIndex] = newdis;
                    stop[neibourIndex] = cur.stop+1;
                    pq.offer(new City(neibourIndex,dis2[neibourIndex],stop[neibourIndex]));
                }
            }
        }
        return -1;
    }
}
